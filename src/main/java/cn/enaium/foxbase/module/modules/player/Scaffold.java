package cn.enaium.foxbase.module.modules.player;

import cn.enaium.foxbase.event.EventTarget;
import cn.enaium.foxbase.event.events.EventUpdate;
import cn.enaium.foxbase.module.Category;
import cn.enaium.foxbase.module.Module;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.data.client.model.VariantSettings;
import net.minecraft.entity.Entity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EmptyBlockView;
import org.lwjgl.glfw.GLFW;

public class Scaffold extends Module {
    public Scaffold() {
        super("Scaffold","自动搭路", GLFW.GLFW_KEY_F, Category.PLAYER);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){

        BlockPos belowPlayer = mc.player.getBlockPos().down();

        // check if block is already placed
        if(!mc.world.getBlockState(belowPlayer).getMaterial().isReplaceable())
            return;

        // search blocks in hotbar
        int newSlot = -1;
        for(int i = 0; i < 9; i++)
        {
            // filter out non-block items
            ItemStack stack = mc.player.inventory.getStack(i);
            if(stack.isEmpty() || !(stack.getItem() instanceof BlockItem))
                continue;

            // filter out non-solid blocks
            Block block = Block.getBlockFromItem(stack.getItem());
            BlockState state = block.getDefaultState();
            if(!Block.isShapeFullCube(state
                    .getCullingShape(EmptyBlockView.INSTANCE, BlockPos.ORIGIN)))
                continue;

            // filter out blocks that would fall
            if(block instanceof FallingBlock && FallingBlock
                    .canFallThrough(mc.world.getBlockState(belowPlayer.down())))
                continue;

            newSlot = i;
            break;
        }

        // check if any blocks were found
        if(newSlot == -1)
            return;

        // set slot
        int oldSlot = mc.player.inventory.selectedSlot;
        mc.player.inventory.selectedSlot = newSlot;

        placeBlock(belowPlayer);

        // reset slot
        mc.player.inventory.selectedSlot = oldSlot;
    }

    private boolean placeBlock(BlockPos pos)
    {
        Vec3d eyesPos = new Vec3d(mc.player.getX(),
                mc.player.getY() + mc.player.getEyeHeight(mc.player.getPose()),
                mc.player.getZ());

        for(Direction side : Direction.values())
        {
            BlockPos neighbor = pos.offset(side);
            Direction side2 = side.getOpposite();

            // check if side is visible (facing away from player)
            if(eyesPos
                    .squaredDistanceTo(new Vec3d(pos.getX(),pos.getY(),pos.getZ()).add(0.5, 0.5, 0.5)) >= eyesPos
                    .squaredDistanceTo(new Vec3d(neighbor.getX(),neighbor.getY(),neighbor.getZ()).add(0.5, 0.5, 0.5)))
                continue;

            // check if neighbor can be right clicked
            if(!mc.world.canPlace(mc.world.getBlockState(neighbor),neighbor,ShapeContext.of(mc.player)))
                continue;

            Vec3d hitVec = new Vec3d(neighbor.getX(),neighbor.getY(),neighbor.getZ()).add(0.5, 0.5, 0.5)
                    .add(new Vec3d(side2.getVector().getX(),side2.getVector().getY(),side2.getVector().getZ()).multiply(0.5));

            // check if hitVec is within range (4.25 blocks)
            if(eyesPos.squaredDistanceTo(hitVec) > 18.0625)
                continue;

            // place block
            /*
            Rotation rotation = RotationUtils.getNeededRotations(hitVec);
            PlayerMoveC2SPacket.LookOnly packet =
                    new PlayerMoveC2SPacket.LookOnly(rotation.getYaw(),
                            rotation.getPitch(), MC.player.onGround);
            MC.player.networkHandler.sendPacket(packet);
            IMC.getInteractionManager().rightClickBlock(neighbor, side2,
                    hitVec);
            MC.player.swingHand(Hand.MAIN_HAND);
            IMC.setItemUseCooldown(4);*/
        }

        return false;
    }
}
