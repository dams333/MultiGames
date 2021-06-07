package ch.dams333.multiGames.utils.show;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle;

public class TitleUtils {
    public static void sendTitle(Player player, String title, int ticks) {

        IChatBaseComponent baseTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
        PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, baseTitle);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(titlePacket);

        sendTime(player, ticks);

    }

    public static void sendSubTitle(Player player, String subTitle, int ticks){

        IChatBaseComponent baseSubTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subTitle + "\"}");
        PacketPlayOutTitle subTitlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, baseSubTitle);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(subTitlePacket);

        sendTime(player, ticks);

    }

    public static void sendActionBar(Player player, String actionBar, int ticks){

        IChatBaseComponent baseSubTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + actionBar + "\"}");
        PacketPlayOutTitle subTitlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.ACTIONBAR, baseSubTitle);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(subTitlePacket);

        sendTime(player, ticks);

    }

    public static void sendAllTitle(String title, int ticks){
        for(Player p : Bukkit.getOnlinePlayers()){
            sendTitle(p, title, ticks);
        }
    }

    public static void sendAllSubTitle(String subTitle, int ticks){
        for(Player p : Bukkit.getOnlinePlayers()){
            sendSubTitle(p, subTitle, ticks);
        }
    }

    public static void sendAllActionBar(String actionBar, int ticks){
        for(Player p : Bukkit.getOnlinePlayers()){
            sendActionBar(p, actionBar, ticks);
        }
    }

    public static void sendTime(Player player, int ticks) {

        PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, 20, ticks, 20);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(titlePacket);

    }
}
