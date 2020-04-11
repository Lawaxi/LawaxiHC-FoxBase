package cn.enaium.foxbase.module.modules.movement;

import cn.enaium.foxbase.event.EventTarget;
import cn.enaium.foxbase.event.events.EventUpdate;
import cn.enaium.foxbase.module.Category;
import cn.enaium.foxbase.module.Module;
import cn.enaium.foxbase.setting.Setting;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.lwjgl.glfw.GLFW;

public class HighJump extends Module {

    private final Setting height = new Setting(this,"Height",(float) 6, (float)1, (float)100);


    public HighJump() {
        super("HighJump","高跳", GLFW.GLFW_KEY_H, Category.MOVEMENT);
        addSetting(height);
    }


    public float getAdditionalJumpMotion()
    {
        return enable ? height.getCurrentValueFloat() * 0.1F : 0;
    }
}
