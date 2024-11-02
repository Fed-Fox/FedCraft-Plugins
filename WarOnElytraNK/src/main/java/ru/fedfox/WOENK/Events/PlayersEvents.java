package ru.fedfox.WOENK.Events;
import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.inventory.InventoryClickEvent;
import cn.nukkit.event.player.*;
import cn.nukkit.plugin.PluginBase;
import ru.fedfox.WOENK.Base.RoomBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PlayersEvents extends PluginBase implements Listener {

    private static HashMap<Player, Integer> playersost;
    private static HashMap<Player, Integer> playerinroom;

    public static void addPS(HashMap<Player, Integer> playersost) {PlayersEvents.playersost = playersost;}
    public static void addPIR(HashMap<Player, Integer> playerinroom) {PlayersEvents.playerinroom = playerinroom;}
    public ArrayList<String> WorldsList = new ArrayList<>(Arrays.asList("woel1", "woel2","woel3"));

    @EventHandler
    public void PJE(PlayerJoinEvent e){
        Player pl = e.getPlayer();
        playersost.put(pl, 0);
        playerinroom.put(pl, 0);
    }

    @EventHandler
    public void PCE(PlayerCommandPreprocessEvent e){
        Player pl = e.getPlayer();
        String cmd = e.getMessage();
        for (String world : WorldsList){
            RoomBase.cmdUse(pl, cmd, world, e);
        }
    }

    @EventHandler
    public void droptrac (PlayerDropItemEvent e){
        Player pl = e.getPlayer();
        for (String world : WorldsList){
            RoomBase.dropCanc(pl, e, world);
        }
    }

    @EventHandler
    public void clicktrac (InventoryClickEvent e) {
        Player pl = e.getPlayer();
        for (String world : WorldsList){
            RoomBase.clickTr(pl, e, world);
        }
    }

    @EventHandler
    public void breakblock (BlockBreakEvent e){
        Player pl = e.getPlayer();
        for (String world : WorldsList){
            RoomBase.bbevent(pl, e, world);
        }
    }

    @EventHandler
    public void PLE(PlayerQuitEvent e){
        Player pl = e.getPlayer();
        for (int i = 1; i<=3 ; i++){
            RoomBase.playerQuit(pl, "room"+String.valueOf(i), "woel"+String.valueOf(i), i);
        }
    }
}
