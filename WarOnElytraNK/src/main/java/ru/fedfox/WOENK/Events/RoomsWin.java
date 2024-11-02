package ru.fedfox.WOENK.Events;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.player.PlayerDeathEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.level.Location;
import cn.nukkit.plugin.PluginBase;
import ru.fedfox.WOENK.Base.RoomBase;

public class RoomsWin extends PluginBase implements Listener {

    /*                           Объявление Элеметов                           */

    Location center = new Location(128, 20, 128);

    /*                           Вызыввание методов победы при смерти игрока                           */

    @EventHandler
    public void PDE(PlayerDeathEvent e){
        RoomBase.roomWinEvent(e, center, "room1", 1);
        RoomBase.roomWinEvent(e, center, "room2", 2);
        RoomBase.roomWinEvent(e, center, "room3", 3);
    }

    /*                           Вызывание методов отключения урона при их событии                           */

    @EventHandler
    public void PDE(EntityDamageEvent e){
        RoomBase.roomEntityDamage(e, "room1", "woel1");
        RoomBase.roomEntityDamage(e, "room2", "woel2");
        RoomBase.roomEntityDamage(e, "room3", "woel3");
    }

    /*                           Вызвание методов выхода из игры при использовании предмета                           */

    @EventHandler
    public void PIE(PlayerInteractEvent e){
        RoomBase.roomIntEvent(e, "room1", "woel1", 1);
        RoomBase.roomIntEvent(e, "room2", "woel2", 2);
        RoomBase.roomIntEvent(e, "room3", "woel3", 3);
    }

}
