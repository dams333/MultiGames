package ch.dams333.multiGames.core.teams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.items.ItemCreator;

public class TeamsManager {
    
    private MultiGames main;

    private List<String> tags;
    private Map<ChatColor, Byte> colors;

    private List<Team> teams;

    private int teamsCount;

    public TeamsManager(MultiGames main) {
        this.main = main;
        tags = Arrays.asList("♥", "★", "♫", "☺");
        colors = new HashMap<>();
        colors.put(ChatColor.RED, (byte) 1);
        colors.put(ChatColor.GOLD, (byte) 14);
        colors.put(ChatColor.YELLOW, (byte) 11);
        colors.put(ChatColor.GREEN, (byte) 10);
        colors.put(ChatColor.DARK_GREEN, (byte) 2);
        colors.put(ChatColor.BLUE, (byte) 12);
        colors.put(ChatColor.DARK_BLUE, (byte) 4);
        colors.put(ChatColor.DARK_PURPLE, (byte) 5);
        colors.put(ChatColor.WHITE, (byte) 15);
        colors.put(ChatColor.GRAY, (byte) 7);
        colors.put(ChatColor.LIGHT_PURPLE, (byte) 13);

        teams = new ArrayList<>();
        teamsCount = 1;
    }

    public void generateTeams(){
        this.teams = new ArrayList<>();
        int teamsCount = main.gameVariablesManager.getVariable("teamsNumber").getIntValue();
        this.teamsCount = teamsCount;
        if(teamsCount > 1){
            for(int i = 1; i <= teamsCount; i++){
                String tag = "";
                ChatColor chatColor = null;
                byte banner = 0;

                if(i <= 11){
                    tag = tags.get(0);
                }else if(i <= 22){
                    tag = tags.get(1);
                }else if(i <= 33){
                    tag = tags.get(2);
                }else if(i <= 44){
                    tag = tags.get(3);
                }

                chatColor = (ChatColor) colors.keySet().toArray()[i%11];
                banner = colors.get(chatColor);

                this.teams.add(new Team(i, chatColor, tag, banner));
            }
            for(Player p : Bukkit.getOnlinePlayers()){
                p.getInventory().setItem(8, ItemCreator.create(Material.BANNER, (byte) 15, ChatColor.GOLD + "Choisir une équipe"));
            }
        }else{
            for(Player p : Bukkit.getOnlinePlayers()){
                p.getInventory().setItem(8, null);
            }
        }
    }

    public List<Team> getTeams() {
        return this.teams;
    }

    public boolean activatedTeams(){
        return teamsCount > 1;
    }

    public int getStartingTeamsCount() {
        return teamsCount;
    }

    public int getTeamsCount() {
        int teams = 0;
        for(Team team : this.teams){
            local:
            for(Player p : team.getPlayers()){
                if(main.gameManager.isAlive(p)){
                    teams++;
                    break local;
                }
            }
        }
        return teams;
    }

    public void chooseTeam(Player player, int TeamId) {
        for(Team team : this.teams){
            if(team.getPlayers().contains(player)){
                team.removePlayer(player);
                break;
            }
        }
        for(Team team : this.teams){
            if(team.getId() == TeamId){
                team.addPlayer(player);
                break;
            }
        }
    }

}
