package com.jam.jamfactory.util;

import java.util.HashMap;

import com.google.common.collect.ImmutableList;
import com.jam.jamfactory.JamFactory;
import com.jam.jamfactory.block.TungstenOre;
import com.jam.jamfactory.world.biome.BiomeTemplate;
import com.mojang.serialization.Codec;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeAmbience.GrassColorModifier;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.ParticleEffectAmbience;
import net.minecraft.world.feature.AbstractFeatureBase;
import net.minecraft.world.feature.BaseFeatureConfig;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpreadConfig;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

@SuppressWarnings("unchecked")
@Mod.EventBusSubscriber
public class RegistryHandler {
	
	public static final HashMap<String, DeferredRegister<?>> registries = new HashMap<>();
	
	static 
	{
		addRegistry("item",ForgeRegistries.ITEMS);
		addRegistry("block",ForgeRegistries.BLOCKS);
		addRegistry("feature",ForgeRegistries.FEATURES);
		addRegistry("biome",ForgeRegistries.BIOMES);
		addRegistry("container",ForgeRegistries.CONTAINERS);
		addRegistry("foliage",ForgeRegistries.FOLIAGE_PLACER_TYPES);
		addRegistry("tile_entity",ForgeRegistries.TILE_ENTITIES);
		addRegistry("surface_builder",ForgeRegistries.SURFACE_BUILDERS);
		addRegistry("tree_decorator",ForgeRegistries.TREE_DECORATOR_TYPES);

	}
	
	public static HashMap<String,RegistryObject<Block>> blocks = new HashMap<>();
	public static HashMap<String,RegistryObject<Item>> items = new HashMap<>();
	public static HashMap<String,RegistryObject<Biome>> biomes = new HashMap<>();
	public static HashMap<String,RegistryObject<Feature<?>>> features = new HashMap<>();
	static 
	{
		addBlock("tungsten_ore", new TungstenOre(), ItemGroup.BUILDING_BLOCKS);
		addBlock("tungsten_block", ToolType.PICKAXE, Material.ROCK, MaterialColor.STONE, 3f, 6f, ItemGroup.BUILDING_BLOCKS);
		
		addItem("tungsten_crystal", ItemGroup.MATERIALS);
		
		addFeature("test");

		
		/*addBiome("caelum_forest", Category.SWAMP, .15f, .8f, BiomeTemplate.DEFAULT_OVERWORLD_SKY_COLOR, BiomeTemplate.DEFAULT_OVERWORLD_FOG_COLOR, BiomeTemplate.DEFAULT_OVERWORLD_WATER_COLOR,
				BiomeTemplate.DEFAULT_OVERWORLD_WATER_FOG_COLOR, color(0,50, 70), color(0, 100, 150), GrassColorModifier.NONE, ParticleTypes.ENCHANT, .01f, 0f, .8f,
				new ConfiguredFeature<?, ?>[] {Features.field_243934_cq, new ConfiguredFeature<AbstractFeatureBase, NoFeatureConfig>(features.get("test").get()) }, new StructureFeature<?,?>[] {StructureFeatures.field_244151_q});
		*/
	}

	public static void init()
	{
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		registries.values().forEach(registry -> registry.register(modEventBus));
	}
	
	public static <T extends IForgeRegistryEntry<T>> void addRegistry(String id, IForgeRegistry<T> registry)
	{
		registries.put(id, DeferredRegister.create(registry, JamFactory.MOD_ID));
	}
	
	
	public static void addBlock(String id, ToolType toolType, Material mat, MaterialColor matColor, float hardness, float resistence,  ItemGroup itemGroup) {
		blocks.put(id, ((DeferredRegister<Block>)registries.get("block")).register(id, () -> new Block(AbstractBlock.Properties.create(mat,matColor).func_235861_h_().harvestTool(toolType).hardnessAndResistance(hardness, resistence))));
		items.put(id, ((DeferredRegister<Item>)registries.get("item")).register(id, () -> new BlockItem(blocks.get(id).get(), new Item.Properties().group(itemGroup))));
	}
	
	public static void addBlock(String id, Block template, ItemGroup itemGroup) {
		blocks.put(id, ((DeferredRegister<Block>)registries.get("block")).register(id, () -> template));
		items.put(id, ((DeferredRegister<Item>)registries.get("item")).register(id, () -> new BlockItem(blocks.get(id).get(), new Item.Properties().group(itemGroup))));
	}
	
	public static void addItem(String id, ItemGroup itemGroup)
	{
		items.put(id, ((DeferredRegister<Item>)registries.get("item")).register(id, () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS))));
	}
	
	public static void addFeature(String id)
	{
		
		features.put(id, ((DeferredRegister<Feature<?>>)registries.get("feature")).register(id, () -> new AbstractFeatureBase(NoFeatureConfig.field_236558_a_)));
	}
	
	public static void addBiome(String id, Biome.Category biomeCategory, float depthModifier, float downfall, int skyColor, int fogColor, int waterColor, int waterFogColor, int grassColor,
			int foliageColor, GrassColorModifier grassColorModifier, BasicParticleType particle, float particleFrequency, float scale, float temp, ConfiguredFeature<?, ?>[] vegetationFeatures,
			StructureFeature<?,?>[] structureFeatures) {
		
		biomes.put(id, ((DeferredRegister<Biome>)registries.get("biome")).register(
				id,
				() -> RegistryHandler.makeBiome(id, biomeCategory, depthModifier, downfall, skyColor, fogColor,
						waterColor, waterFogColor, grassColor, foliageColor, grassColorModifier, particle, particleFrequency, scale, temp, vegetationFeatures, structureFeatures)));
	}
	
	

	
	public static Biome makeBiome(String id, Biome.Category biomeCategory, float depthModifier, float downfall, int skyColor, int fogColor, int waterColor, int waterFogColor, int grassColor,
			int foliageColor, GrassColorModifier grassColorModifier, BasicParticleType particle, float particleFrequency, float scale, float temp, ConfiguredFeature<?, ?>[] vegetationFeatures,
			StructureFeature<?,?>[] structureFeatures)
	{
		final BiomeAmbience.Builder ambianceBuilder = new BiomeAmbience.Builder();
		ambianceBuilder.func_235239_a_(fogColor);
		ambianceBuilder.func_235246_b_(waterColor);
		ambianceBuilder.func_235248_c_(waterFogColor);
		ambianceBuilder.func_242539_d(skyColor);
		ambianceBuilder.func_242540_e(foliageColor);
		ambianceBuilder.func_242541_f(grassColor);
		ambianceBuilder.func_242537_a(grassColorModifier);
		ambianceBuilder.func_235244_a_(new ParticleEffectAmbience(particle, particleFrequency));

		
		
		
		final BiomeGenerationSettingsBuilder genBuilder = new BiomeGenerationSettingsBuilder(BiomeGenerationSettings.field_242480_b);
		genBuilder.func_242517_a(new ConfiguredSurfaceBuilder(SurfaceBuilder.SWAMP, (ISurfaceBuilderConfig)SurfaceBuilder.GRASS_DIRT_SAND_CONFIG));
		
		DefaultBiomeFeatures.func_243733_b(genBuilder);
		genBuilder.func_242516_a(StructureFeatures.field_244131_B);
        DefaultBiomeFeatures.func_243738_d(genBuilder);
        DefaultBiomeFeatures.func_243746_h(genBuilder);
        DefaultBiomeFeatures.func_243748_i(genBuilder);
        DefaultBiomeFeatures.func_243750_j(genBuilder);
        DefaultBiomeFeatures.func_243755_o(genBuilder);
        
        for(ConfiguredFeature<?, ?> feature : vegetationFeatures) genBuilder.func_242513_a(GenerationStage.Decoration.VEGETAL_DECORATION, feature);

        for(StructureFeature<?,?> feature : structureFeatures) genBuilder.func_242516_a(feature);
		
		final MobSpawnInfoBuilder mobSpawnBuilder = new MobSpawnInfoBuilder(MobSpawnInfo.field_242551_b);
		
		final Biome.Builder biomeBuilder = new Biome.Builder();
		biomeBuilder.category(biomeCategory);
		biomeBuilder.depth(depthModifier);
		biomeBuilder.downfall(downfall);
		biomeBuilder.scale(scale);
		biomeBuilder.temperature(temp);
		biomeBuilder.precipitation(RainType.RAIN);
		biomeBuilder.func_235097_a_(ambianceBuilder.func_235238_a_());
		biomeBuilder.func_242457_a(genBuilder.func_242508_a());
		biomeBuilder.func_242458_a(mobSpawnBuilder.func_242577_b());
		
		return biomeBuilder.func_242455_a();
	}
	
	public static int color(int r, int g, int b)
	{
		r = Math.max(Math.min(r,255),0);
		g = Math.max(Math.min(g,255),0);
		b = Math.max(Math.min(b,255),0);
		return (r << 16) + (g << 8) + b;
	}
	
	public static ConfiguredSurfaceBuilder<?> getSurfaceBuilder(final RegistryKey<ConfiguredSurfaceBuilder<?>> key) 
	{
		return WorldGenRegistries.field_243651_c.func_230516_a_(key);
	}
	
	private static RegistryKey<ConfiguredFeature<?, ?>> key(final String name) {
		return RegistryKey.func_240903_a_(Registry.field_243552_au, new ResourceLocation(JamFactory.MOD_ID, name));
	}

	private static void register(final RegistryKey<ConfiguredFeature<?, ?>> key, final ConfiguredFeature<?, ?> configuredFeature) {
		Registry.register(WorldGenRegistries.field_243653_e, key.func_240901_a_(), configuredFeature);
	}

	
	

	
}
