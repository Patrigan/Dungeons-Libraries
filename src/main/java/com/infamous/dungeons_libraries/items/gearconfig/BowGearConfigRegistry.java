package com.infamous.dungeons_libraries.items.gearconfig;

import com.infamous.dungeons_libraries.DungeonsLibraries;
import com.infamous.dungeons_libraries.data.util.CodecJsonDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.infamous.dungeons_libraries.DungeonsLibraries.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BowGearConfigRegistry {
    public static final ResourceLocation GEAR_CONFIG_BUILTIN_RESOURCELOCATION = new ResourceLocation(MODID, "gear_config");

    public static final CodecJsonDataManager<BowGearConfig> GEAR_CONFIGS = new CodecJsonDataManager<>("gearconfig/bow", BowGearConfig.CODEC, DungeonsLibraries.LOGGER);


    public static BowGearConfig getConfig(ResourceLocation resourceLocation) {
        return GEAR_CONFIGS.data.getOrDefault(resourceLocation, BowGearConfig.DEFAULT);
    }

    public static boolean gearConfigExists(ResourceLocation resourceLocation) {
        return GEAR_CONFIGS.data.containsKey(resourceLocation);
    }

    @SubscribeEvent
    public static void onAddReloadListeners(AddReloadListenerEvent event) {
        event.addListener(GEAR_CONFIGS);
    }
}