package cn.enaium.foxbase.utils;

import cn.enaium.foxbase.FoxBase;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class ChatUtils
{

	public static void message(String message)
	{
		Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(
				ChatFormatting.WHITE + "[" + ChatFormatting.RED + FoxBase.instance.name + ChatFormatting.WHITE + "] " + message));
	}

}
