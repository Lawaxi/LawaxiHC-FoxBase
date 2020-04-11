package cn.enaium.foxbase.mixin;

import cn.enaium.foxbase.FoxBase;
import cn.enaium.foxbase.event.Event;
import cn.enaium.foxbase.event.events.EventMotion;
import cn.enaium.foxbase.event.events.EventUpdate;
import cn.enaium.foxbase.module.modules.movement.HighJump;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin{

    @Inject(at = @At("HEAD"),
            method = "sendChatMessage",
            cancellable = true)
    private void onSendChatMessage(String message, CallbackInfo info) {
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void preTick(CallbackInfo callbackInfo) {
        new EventUpdate().call();
    }

    @Inject(at = {@At("HEAD")}, method = {"sendMovementPackets()V"})

    private void onSendMovementPacketsHEAD(CallbackInfo ci) {

        new EventMotion(Event.Type.PRE);

    }

    @Inject(at = {@At("TAIL")}, method = {"sendMovementPackets()V"})

    private void onSendMovementPacketsTAIL(CallbackInfo ci) {

        new EventMotion(Event.Type.POST);

    }

    /*
    @Overwrite
    protected float getJumpVelocityMultiplier() {

        float f = this.world.getBlockState(this.getBlockPos()).getBlock().getJumpVelocityMultiplier();
        float g = this.world.getBlockState(this.getVelocityAffectingPos()).getBlock().getJumpVelocityMultiplier();

        if(this.getType() == EntityType.PLAYER)
        {
            return ((double)f == 1.0D ? g : f)
                    + ((HighJump)FoxBase.instance.moduleManager.getModule("HighJump")).getAdditionalJumpMotion();
        }
        return ((double)f == 1.0D ? g : f);
    }
*/

}
