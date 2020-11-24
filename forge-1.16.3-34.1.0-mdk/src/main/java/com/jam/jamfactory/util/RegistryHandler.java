package com.jam.jamfactory.util;

import com.jam.jamfactory.JamFactory;
import com.jam.jamfactory.block.BlockItemBase;
import com.jam.jamfactory.block.TungstenBlock;
import com.jam.jamfactory.block.TungstenOre;
import com.jam.jamfactory.item.ItemBase;
import com.jam.jamfactory.world.gen.OreGen;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;


@Mod.EventBusSubscriber
public class RegistryHandler {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, JamFactory.MOD_ID);
	//public static final DeferredRegister<LootTable> LOOT = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, JamFactory.MOD_ID);
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, JamFactory.MOD_ID);
	
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, JamFactory.MOD_ID);
	
	
	//Items
		public static RegistryObject<Item> TUNGSTEN_CRYSTAL = ITEMS.register("tungsten_crystal", ItemBase::new);
		
		//Blocks
		public static RegistryObject<Block> TUNGSTEN_ORE = BLOCKS.register("tungsten_ore", () -> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).func_235861_h_().hardnessAndResistance(1.5F, 6.0F)));
		public static RegistryObject<Block> TUNGSTEN_BLOCK = BLOCKS.register("tungsten_block", TungstenBlock::new);
		//Block Items
		public static RegistryObject<Item> TUNGSTEN_ORE_ITEM = ITEMS.register("tungsten_ore", () -> new BlockItemBase(TUNGSTEN_ORE.get()));
		public static RegistryObject<Item> TUNGSTEN_BLOCK_ITEM = ITEMS.register("tungsten_block", () -> new BlockItemBase(TUNGSTEN_BLOCK.get()));
		

		//Features
	public static void init()
	{
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
		
		
		//TUNGSTEN_ORE = BLOCKS.register("tungsten_ore", TungstenOre::new);
		
		
		
	
		//System.out.println(b);
		//System.exit(0);
		
		//OreGen.registerOres(TUNGSTEN_ORE.get());
		
	}
	
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onRegistryRegisterBlock(RegistryEvent.Register<Block> event) {
	    System.out.println(event.getRegistry());
	    System.exit(0);
	}

	
	

	
}
