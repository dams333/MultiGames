package ch.dams333.multiGames.listeners.connection;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;

import ch.dams333.multiGames.MultiGames;
import ch.dams333.multiGames.utils.items.ItemCreator;
import ch.dams333.multiGames.utils.state.GameState;
import net.md_5.bungee.api.ChatColor;

public class JoinServer implements Listener{
    
    private MultiGames main;

    public JoinServer(MultiGames main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        e.setJoinMessage("");
        if(main.gameStateManager.isState(GameState.SETUP)){
            //Game setupping
            p.getInventory().clear();
            for(PotionEffect effect : p.getActivePotionEffects()){
                p.removePotionEffect(effect.getType());
            }
            if(p.hasPermission("multigames.admin")){
                p.setGameMode(GameMode.CREATIVE);
                p.getInventory().setItem(4, ItemCreator.create(Material.CHEST, ChatColor.GOLD + "Gérer la partie"));
            }else{
                p.setGameMode(GameMode.ADVENTURE);
            }
            if(main.teamsManager.activatedTeams() && !main.gameVariablesManager.getVariable("randomiseTeams").getBooleanValue()){
                p.getInventory().setItem(8, ItemCreator.create(Material.BANNER, (byte) 15, ChatColor.GOLD + "Choisir une équipe"));
            }
        }else if(main.gameStateManager.isState(GameState.GAME) || main.gameStateManager.isState(GameState.END)){
            //Game
            if(main.gameManager.isDisconnected(p)){
                main.gameManager.reconnect(p);
            }else{
                if(main.gameVariablesManager.getVariable("activateSpec").getBooleanValue()){
                    p.teleport(new Location(main.gameManager.getGameWorld(), 0, 80, 0));
                    p.getInventory().clear();
                    p.setGameMode(GameMode.SPECTATOR);
                }else{
                    p.kickPlayer("Les spectateurs ne sont pas autorisés lors de cette partie");
                }
            }
        }else{
            p.kickPlayer("Veuillez attendre que la partie démarre avant de vous connecter en tant que spectateur !");
        }
        
    }

}
