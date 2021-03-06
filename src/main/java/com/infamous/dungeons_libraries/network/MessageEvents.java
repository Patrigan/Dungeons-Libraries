package com.infamous.dungeons_libraries.network;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

import static com.infamous.dungeons_libraries.DungeonsLibraries.MODID;
import static com.infamous.dungeons_libraries.capabilities.enchantable.EnchantableHelper.getEnchantableCapabilityLazy;


@Mod.EventBusSubscriber(modid = MODID)
public class MessageEvents {

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        PlayerEntity player = event.getPlayer();
        if (player instanceof ServerPlayerEntity)
            getEnchantableCapabilityLazy(player).ifPresent(cap -> {
                NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new MobEnchantmentMessage(player.getId(), cap.getEnchantments()));
            });
    }

    @SubscribeEvent
    public static void onPlayerStartTracking(PlayerEvent.StartTracking event){
        PlayerEntity player = event.getPlayer();
        Entity target = event.getTarget();
        if (player instanceof ServerPlayerEntity)
            getEnchantableCapabilityLazy(target).ifPresent(cap -> {
                NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new MobEnchantmentMessage(target.getId(), cap.getEnchantments()));
            });
    }
}
