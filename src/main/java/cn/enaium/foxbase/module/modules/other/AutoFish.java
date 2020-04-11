package cn.enaium.foxbase.module.modules.other;

import cn.enaium.foxbase.event.EventTarget;
import cn.enaium.foxbase.event.events.EventUpdate;
import cn.enaium.foxbase.module.Category;
import cn.enaium.foxbase.module.Module;
import org.lwjgl.glfw.GLFW;

public class AutoFish extends Module {
    public AutoFish() {
        super("AutoFish","自动钓鱼", GLFW.GLFW_KEY_F, Category.OTHER);
    }

    @EventTarget
    public void onUpdate(EventUpdate e) {
    }

}
