package biomesoplenty.common.handler.loading;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SwapManager 
{
    public static final SwapManager INSTANCE = new SwapManager();
    
    /**Maps old unique ids to new ones**/
    private HashMap<String, String> blockIdRemapMap;
    
    /**A map specifying which state ids map to what replacement function. The first four bits specify metadata, the remaining 12 bits specify block id.**/
    private Function<Integer, Integer>[] metadataReplacementFunctions;
    
    private SwapManager()
    {
        this.blockIdRemapMap = new HashMap<String, String>();
        this.metadataReplacementFunctions = new Function[65535];
    }
    
    /**Remaps an old block id to a new one. A block must already exist with the new id. This is implemented
     * using the FMLMissingMappingsEvent.**/
    public void remapBlockId(String oldId, String newId)
    {
        this.blockIdRemapMap.put("biomesoplenty:" + oldId, newId);
    }
    
    public void swapBlockId()
    {
        
    }
    
    /**Swaps the metadata of a block with a given identifier using the provided function. This identifier should be identifier of the block 
     * after any id substitutions. If no id substitutions have been made, then the existing id should be used. A block must
     * already exist with the new id.**/
    public void swapMetadata(String newId, Function<Integer, Integer> swapFunction)
    {
        Block block = GameRegistry.findBlock(BiomesOPlenty.MOD_ID, newId);
        
        //Block that's being swapped must already exist
        if (block != null)
        {
            //All metadata for this id should use this function
            for (int metadata = 0; metadata < 16; ++metadata)
            {
                IBlockState state = block.getStateFromMeta(metadata);
                int id = Block.BLOCK_STATE_IDS.get(state);
            
                this.metadataReplacementFunctions[id] = swapFunction;
            }
        }
        else
        {
            throw new IllegalArgumentException("No block with id " + newId + " exists.");
        }
    }
    
    /**Swaps the metadata of a block with a given identifier. This identifier should be identifier of the block after 
     * any id substitutions. If no id substitutions have been made, then the existing id should be used. A block must
     * already exist with the new id.**/
    public void swapMetadata(String newId, int oldMetadata, int newMetadata)
    {
        Block block = GameRegistry.findBlock(BiomesOPlenty.MOD_ID, newId);
        
        //Block that's being swapped must already exist
        if (block != null)
        {
            IBlockState state = block.getStateFromMeta(oldMetadata);
            int id = Block.BLOCK_STATE_IDS.get(state);

            this.metadataReplacementFunctions[id] = createMetaSwapFunc(newMetadata);
        }
        else
        {
            throw new IllegalArgumentException("No block with id " + newId + " exists.");
        }
    }
    
    public ImmutableMap<String, String> getBlockIdRemaps()
    {
        return ImmutableMap.copyOf(this.blockIdRemapMap);
    }
    
    public Function<Integer, Integer>[] getMetadataReplacementFunctions()
    {
        return this.metadataReplacementFunctions;
    }
    
    private static Function<Integer, Integer> createMetaSwapFunc(final int newMetadata)
    {
        return new Function<Integer, Integer>()
        {
            @Override
            public Integer apply(Integer input) 
            {
                return newMetadata;
            }
        };
    }
}
