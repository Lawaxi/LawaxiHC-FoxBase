package cn.enaium.foxbase.module.modules.movement;

import cn.enaium.foxbase.event.EventTarget;
import cn.enaium.foxbase.event.events.EventUpdate;
import cn.enaium.foxbase.module.Category;
import cn.enaium.foxbase.module.Module;
import net.minecraft.entity.Entity;
import org.lwjgl.glfw.GLFW;

public class BoatFly extends Module {

    public BoatFly() {
        super("BoatFly","飞船", GLFW.GLFW_KEY_B, Category.MOVEMENT);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){

        Entity boat = mc.player.getVehicle();
        if(mc.player.getVehicle()!=null)
        {
            if(mc.options.keyJump.isPressed())
            {
                boat.setVelocity(boat.getVelocity().x,0.3,boat.getVelocity().z);
            }
        }
    }
}
