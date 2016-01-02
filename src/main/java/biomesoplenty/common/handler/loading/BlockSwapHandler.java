package biomesoplenty.common.handler.loading;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.collect.HashMultimap;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.chunk.storage.RegionFileCache;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraft.world.storage.MapStorage;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockSwapHandler 
{ 
    /**This should be updated every time there is a world-breaking change**/
    public static final int CURRENT_LOAD_VERSION = 1;
    
    private HashMap<Integer, BOPSavedData> bopSavedDataMap = new HashMap<Integer, BOPSavedData>();
    private HashMultimap<Integer, ChunkCoordIntPair> saveVersionMap = HashMultimap.create();
    
    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event)
    {
        World world = event.world;
        MapStorage mapStorage = world.getPerWorldStorage();
        BOPSavedData savedData = (BOPSavedData)mapStorage.loadData(BOPSavedData.class, BOPSavedData.DATA_IDENTIFIER);
        int dimensionId = world.provider.getDimensionId();
        
        //Clear the saved chunk map
        //this.finishedChunkMap.get(dimensionId).clear();
        
        //If the saved data file hasn't been created before, create it
        if (savedData == null)
        {
            savedData = new BOPSavedData(BOPSavedData.DATA_IDENTIFIER);
            mapStorage.setData(BOPSavedData.DATA_IDENTIFIER, savedData);
            savedData.markDirty(); //Mark for saving
        }
        
        //Cache saved data object
        this.bopSavedDataMap.put(dimensionId, savedData);
        
        int lastLoadVersion = savedData.lastLoadVersion;
        
        //Check if we can convert this save
        if (lastLoadVersion == CURRENT_LOAD_VERSION - 1)
        {
            savedData.defaultGeneratedVersion = CURRENT_LOAD_VERSION - 1;
        }
        else if (lastLoadVersion < CURRENT_LOAD_VERSION - 1)
        {
            BiomesOPlenty.logger.error("This version of Biomes O' Plenty cannot convert this save. Please try again after converting with an older version");
        }
        else
        {
            //BiomesOPlenty.logger.info("World save is up-to-date, no conversion is necessary");
        }
        
        //Update load version to match current version if it doesn't already
        if (savedData.lastLoadVersion != CURRENT_LOAD_VERSION)
        {
            savedData.lastLoadVersion = CURRENT_LOAD_VERSION; 
            savedData.markDirty(); //Mark for saving
        }
    }
    
    @SubscribeEvent
    public void onChunkLoad(ChunkEvent.Load event)
    {
        Chunk chunk = event.getChunk();
        World world = event.world;

        if (!world.isRemote)
        {
            ChunkProviderServer provider = (ChunkProviderServer)world.getChunkProvider();
            AnvilChunkLoader chunkLoader = (AnvilChunkLoader)provider.chunkLoader;
            NBTTagCompound chunkData = getChunkNBT(chunkLoader, chunk.xPosition, chunk.zPosition);
            boolean updateVersion = false;
            
            int loadVersion;

            //If the chunk already exists, it should have non-null nbt
            if (chunkData != null)
            {
                NBTTagCompound bopNbt = chunkData.getCompoundTag("BiomesOPlenty");

                //Chunk already has last version tag
                if (bopNbt.hasKey("LastLoadVersion"))
                {
                    loadVersion = bopNbt.getInteger("LastLoadVersion");
                    
                    if (loadVersion != CURRENT_LOAD_VERSION)
                    {
                        updateVersion = true;
                    }
                    else
                    {
                        return; //No further checks need to be made, the chunk is up-to-date
                    }
                }
                else //Chunk data exists however it is missing a tag. It may be from an earlier version.
                {
                    loadVersion = this.bopSavedDataMap.get(world.provider.getDimensionId()).defaultGeneratedVersion; //Use the default version for already generated chunks
                    updateVersion = true;
                }
            }
            else
            {
                //Chunk hasn't been generated already, use the current value (the current version)
                loadVersion = CURRENT_LOAD_VERSION;
                updateVersion = true;
            }

            if (loadVersion != CURRENT_LOAD_VERSION) //Chunk is not up-to-date, conversion necessary
            {
                for (ExtendedBlockStorage blockStorage : chunk.getBlockStorageArray())
                {
                    if (blockStorage != null && blockStorage.getData() != null)
                    {
                        char[] data = blockStorage.getData();

                        for (int i = 0; i < data.length; ++i)
                        {
                            Function<Integer, Integer> metaConversionFunc = SwapManager.INSTANCE.getMetadataReplacementFunctions()[data[i]];

                            if (metaConversionFunc != null) //Not all blocks need conversion thus, this may be nullO
                            {
                                int oldMetadata = data[i] & 15;
                                int newMetadata = metaConversionFunc.apply(oldMetadata);

                                data[i] &= -15; //Clears any existing metadata
                                data[i] |= newMetadata; //Replaces the metadata with the new metadata
                            }
                        }
                    }
                }

                //Mark the chunk for saving
                chunk.setChunkModified();
            }

            if (updateVersion)
            {
                chunk.setChunkModified(); //Mark the chunk for saving
                this.saveVersionMap.put(world.provider.getDimensionId(), new ChunkCoordIntPair(chunk.xPosition, chunk.zPosition));
            }
        }
    }

    @SubscribeEvent
    public void onChunkSave(ChunkDataEvent.Save event)
    {
        Chunk chunk = event.getChunk();
        World world = event.world;
        int dimensionId = world.provider.getDimensionId();
        ChunkCoordIntPair chunkCoord = new ChunkCoordIntPair(chunk.xPosition, chunk.zPosition);
        
        if (this.saveVersionMap.get(dimensionId).contains(chunkCoord))
        {
            NBTTagCompound nbt = event.getData();
            NBTTagCompound bopNbt = nbt.getCompoundTag("BiomesOPlenty");
            
            bopNbt.setInteger("LastLoadVersion", CURRENT_LOAD_VERSION); //Mark chunk as updated
            nbt.setTag("BiomesOPlenty", bopNbt); //Update BoP compound within main compound
            
            //Remove the chunk from the list of chunks to save
            this.saveVersionMap.get(dimensionId).remove(chunkCoord);
        }
    }
    
    private static NBTTagCompound getChunkNBT(AnvilChunkLoader chunkLoader, int x, int z)
    {
        ChunkCoordIntPair chunkCoord = new ChunkCoordIntPair(x, z);
        Map<ChunkCoordIntPair, NBTTagCompound> chunksToRemove = ObfuscationReflectionHelper.getPrivateValue(AnvilChunkLoader.class, chunkLoader, "chunksToRemove", "field_75828_a");
        NBTTagCompound nbt = (NBTTagCompound)chunksToRemove.get(chunkCoord);

        if (nbt == null)
        {
            DataInputStream inputStream = RegionFileCache.getChunkInputStream(chunkLoader.chunkSaveLocation, x, z);

            if (inputStream == null)
            {
                return null;
            }

            try 
            {
                nbt = CompressedStreamTools.read(inputStream);
            } 
            catch (IOException e) 
            {
                BiomesOPlenty.logger.error("An error occurred reading NBT for chunk", e);
            }
        }
        
        return nbt;
    }
}
