package ch.dams333.multiGames.utils.inventory.teams;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import ch.dams333.inventoryHelper.inventory.ContentManager;
import ch.dams333.inventoryHelper.inventory.InventoryItemStack;
import ch.dams333.inventoryHelper.inventory.SimpleInventory;
import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.core.teams.Team;

public class TeamsSelectorInventory {
    public static void open(Player p){
        ContentManager contentManager = new ContentManager();

        int i = 0;
        for(Team team : MultiGames.INSTANCE.teamsManager.getTeams()){
            List<String> players = new ArrayList<>();
            for(Player pl : team.getPlayers()){
                players.add(ChatColor.GRAY + "- " + ChatColor.WHITE + pl.getName());
            }
            
            contentManager.setDefaultItem(i, new InventoryItemStack(Material.BANNER, team.getBanner(), 1, team.getChatColor() + " Equipe " + team.getTag(), players).setInteractionMethod((player, action) -> {
                MultiGames.INSTANCE.teamsManager.chooseTeam(player, team.getId());
                TeamsSelectorInventory.open(p);
            }));
            i++;
        }

        SimpleInventory inv = SimpleInventory.builder()
                                .id("TeamsJoin")
                                .title(ChatColor.GOLD + "Rejoindre une équipe")
                                .rows(6)
                                .closeable(true)
                                .setContentManager(contentManager)
                                .build();
        inv.open(p);
    }
}
