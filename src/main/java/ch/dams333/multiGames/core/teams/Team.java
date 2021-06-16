package ch.dams333.multiGames.core.teams;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Team {

    private int id;
    private ChatColor chatColor;
    private String tag;
    private byte banner;
    private List<Player> players;

    public Team(int id, ChatColor chatColor, String tag, byte banner) {
        this.id = id;
        this.chatColor = chatColor;
        this.tag = tag;
        this.banner = banner;
        players = new ArrayList<>();
    }


    public int getId() {
        return this.id;
    }

    public ChatColor getChatColor() {
        return this.chatColor;
    }

    public String getTag() {
        return this.tag;
    }

    public byte getBanner() {
        return this.banner;
    }

    public List<Player> getPlayers() {
        return this.players;
    }


    public void removePlayer(Player player) {
        this.players.remove(player);
    }


    public void addPlayer(Player player) {
        this.players.add(player);
        player.setDisplayName(chatColor + "[" + tag + "] " + player.getName());
        player.setPlayerListName(chatColor + "[" + tag + "] " + player.getName());
    }

    
}
