package cn.enaium.foxbase.mixin;

import cn.enaium.foxbase.FoxBase;
import net.minecraft.SharedConstants;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Shadow @Final private Window window;

    @Inject(at = @At("RETURN"), method = "updateWindowTitle")
	private void updateWindowTitle(CallbackInfo info) {
		this.window.setTitle(SharedConstants.getGameVersion().getName()+" with "+FoxBase.instance.name+" "+FoxBase.instance.version);
	}

    @Inject(at = @At("HEAD"), method = "run")
    private void run(CallbackInfo info) {
        FoxBase.instance.run();
    }

    @Inject(at = @At("HEAD"), method = "stop")
    private void stop(CallbackInfo info) {
        FoxBase.instance.stop();
    }

}
