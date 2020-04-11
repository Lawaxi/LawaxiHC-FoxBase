package cn.enaium.foxbase.mixin;

import cn.enaium.foxbase.FoxBase;
import cn.enaium.foxbase.event.Event;
import cn.enaium.foxbase.event.events.EventMotion;
import cn.enaium.foxbase.event.events.EventUpdate;
import cn.enaium.foxbase.module.modules.movement.HighJump;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {


    @Shadow public World world;

    @Shadow public abstract BlockPos getBlockPos();

    @Shadow protected abstract BlockPos getVelocityAffectingPos();

    @Shadow public abstract EntityType<?> getType();
}
