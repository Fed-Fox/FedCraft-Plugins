package ru.fedfox.TPMNK.Events;
import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.item.Item;
import cn.nukkit.event.inventory.InventoryClickEvent;
import cn.nukkit.event.player.PlayerDropItemEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.event.Listener;

public class Events extends PluginBase implements Listener {

    @EventHandler
    public void droptrac (PlayerDropItemEvent e){
        Item it = e.getItem();
        Player p = e.getPlayer();
        if (p.getLocation().getLevel().getName().equals("Lobby") | p.getLocation().getLevel().getName().equals("survLobby") | p.getLocation().getLevel().getName().equals("MGLobby")){
            if (!p.hasPermission("fc.items.use")){
                e.setCancelled();
            }
        }
    }

    @EventHandler
    public void clicktrac (InventoryClickEvent e) {
        Item it = e.getSourceItem();
        Player p = e.getPlayer();
        if (p.getLocation().getLevel().getName().equals("Lobby") | p.getLocation().getLevel().getName().equals("survLobby") | p.getLocation().getLevel().getName().equals("MGLobby")) {
            if (!p.hasPermission("fc.items.use")){
                e.setCancelled();
            }
        }
    }

}
