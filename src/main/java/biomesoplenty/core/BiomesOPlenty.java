/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.core;

import java.io.File;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import biomesoplenty.common.command.BOPCommand;
import biomesoplenty.common.handler.loading.SwapManager;
import biomesoplenty.common.init.ModBiomes;
import biomesoplenty.common.init.ModBlockQueries;
import biomesoplenty.common.init.ModBlocks;
import biomesoplenty.common.init.ModChecks;
import biomesoplenty.common.init.ModCompatibility;
import biomesoplenty.common.init.ModConfiguration;
import biomesoplenty.common.init.ModCrafting;
import biomesoplenty.common.init.ModEntities;
import biomesoplenty.common.init.ModGenerators;
import biomesoplenty.common.init.ModHandlers;
import biomesoplenty.common.init.ModItems;
import biomesoplenty.common.init.ModPotions;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLMissingMappingsEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLMissingMappingsEvent.MissingMapping;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = BiomesOPlenty.MOD_ID, name = BiomesOPlenty.MOD_NAME, dependencies = "required-after:Forge@[11.14.3.1468,)")
public class BiomesOPlenty
{
    public static final String MOD_NAME = "Biomes O' Plenty";
    public static final String MOD_ID = "BiomesOPlenty";
     
    @Instance(MOD_ID)
    public static BiomesOPlenty instance;

    @SidedProxy(clientSide = "biomesoplenty.core.ClientProxy", serverSide = "biomesoplenty.core.CommonProxy")
    public static CommonProxy proxy;

    public static Logger logger = LogManager.getLogger(MOD_ID);
    public static File configDirectory;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        configDirectory = new File(event.getModConfigurationDirectory(), "biomesoplenty");

        ModConfiguration.init(configDirectory);
        
        // setup blocks before items, because some items need to reference blocks in their constructors (eg seeds)
        ModBlocks.init();
        ModEntities.init();
        ModItems.init();
        ModPotions.init();
        ModBlockQueries.init();
        
        ModGenerators.init();
        ModBiomes.init();
        ModHandlers.init();
        
        ModCrafting.init();
        ModCompatibility.init();
        
        proxy.registerRenderers();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {}
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        ModChecks.postInit();
    }
    
    @EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new BOPCommand());
    }
    
    @EventHandler
    public void onMissingMapping(FMLMissingMappingsEvent event)
    {
        Map<String, String> idReplacements = SwapManager.INSTANCE.getBlockIdRemaps();
        
        for (MissingMapping mapping : event.getAll())
        {
            if (mapping.type == GameRegistry.Type.BLOCK && idReplacements.containsKey(mapping.name)) //Check if we have a replacement block id
            {
                Block block = GameRegistry.findBlock(BiomesOPlenty.MOD_ID, idReplacements.get(mapping.name));
                
                if (block != null) //Ensure the block we are replacing with actually exists
                {
                    mapping.remap(block);
                }
            }
            else
            {
                BiomesOPlenty.logger.error("Missing replacement id for " + mapping.name);
            }
        }
    }

    public File getConfigDirectory()
    {
        return configDirectory;
    }
}
