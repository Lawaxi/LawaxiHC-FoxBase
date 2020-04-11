package cn.enaium.foxbase.module.modules.movement;

import cn.enaium.foxbase.event.EventTarget;
import cn.enaium.foxbase.event.events.EventUpdate;
import cn.enaium.foxbase.module.Category;
import cn.enaium.foxbase.module.Module;
import cn.enaium.foxbase.setting.Setting;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.lwjgl.glfw.GLFW;

public class NoFall extends Module {

    public NoFall() {
        super("NoFall","无摔落伤害", GLFW.GLFW_KEY_N, Category.MOVEMENT);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){

        ClientPlayerEntity player = mc.player;
        if(player.fallDistance >2){
            player.networkHandler.sendPacket(new PlayerMoveC2SPacket(true));
        }
    }
}
