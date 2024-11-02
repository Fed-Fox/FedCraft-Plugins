package ru.fedfox.WOENK;
import cn.nukkit.Player;
import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import ru.fedfox.WOENK.Base.CmdBase;
import ru.fedfox.WOENK.Base.RoomBase;
import ru.fedfox.WOENK.Base.UtilBase;
import ru.fedfox.WOENK.Commands.Woel;
import ru.fedfox.WOENK.Events.PlayersEvents;
import ru.fedfox.WOENK.Events.RoomsWin;
import ru.fedfox.WOENK.Tasks.*;
import java.util.*;


public class WarOnElytra extends PluginBase implements Listener {

    /*                           Объявление Элементов                           */

    public Player pl;
    public HashMap<String, Integer> roomsost = new HashMap<>();
    public HashMap<String, Integer> roomtime = new HashMap<>();
    public HashMap<String, String> roomname = new HashMap<>();
    public HashMap<String, Integer> roomplayers = new HashMap<>();
    public HashMap<Player, Integer> playersost = new HashMap<>();
    public HashMap<Player, Integer> playerinroom = new HashMap<>();
    public HashMap<Integer, Player> playernum = new HashMap<>();
    public HashMap<String, Integer> HMwait = new HashMap<>();
    public HashMap<String, Integer> HMWaitTp = new HashMap<>();
    public HashMap<String, Integer> HMY = new HashMap<>();
    public ArrayList<String> RoomList = new ArrayList<>(Arrays.asList("room1", "room2","room3"));
    public static Config config;

    @Override
    public void onEnable() {

        /*                             Регистрация Классов                           */

        config = getConfig();
        config.save();
        this.getServer().getPluginManager().registerEvents(this, this);
        this.getServer().getPluginManager().registerEvents(new PlayersEvents(), this);
        this.getServer().getPluginManager().registerEvents(new CmdBase(), this);
        this.getServer().getPluginManager().registerEvents(new RoomBase(), this);
        this.getServer().getPluginManager().registerEvents(new UtilBase(), this);
        this.getServer().getPluginManager().registerEvents(new RoomsWin(), this);
        this.getServer().getScheduler().scheduleRepeatingTask(new Rooms(this), 20);
        this.getServer().getScheduler().scheduleRepeatingTask(new RoomsTeleport(this), 20);
        this.getServer().getScheduler().scheduleRepeatingTask(new Barrier(this), 2);
        this.getServer().getCommandMap().register("", new Woel());

        /*                           Отображение в консоли                           */

        this.getServer().getLogger().info(TextFormat.GRAY + "Загрузка Игры...");
        this.getServer().getLogger().info(TextFormat.GREEN + "Успешная загрузка плагина WarOnElytraNK! Удачной игры!");

        /*                           Объявление Элементов в классах                           */

        Woel.addRM(roomsost);
        Woel.addPS(playersost);
        Woel.addPIR(playerinroom);
        Woel.addPl(pl);

        CmdBase.addRP(roomplayers);
        CmdBase.addPS(playersost);
        CmdBase.addPIR(playerinroom);
        CmdBase.addPN(playernum);

        PlayersEvents.addPS(playersost);
        PlayersEvents.addPIR(playerinroom);

        RoomBase.addRS(roomsost);
        RoomBase.addRP(roomplayers);
        RoomBase.addPIR(playerinroom);
        RoomBase.addPS(playersost);
        RoomBase.addPN(playernum);
        RoomBase.addHMW(HMwait);
        RoomBase.addHMWT(HMWaitTp);
        RoomBase.addRT(roomtime);

        Rooms.addHMW(HMwait);
        Rooms.addHMY(HMY);

        RoomsTeleport.addHMWT(HMWaitTp);

        Barrier.addPIR(playerinroom);

        /*                           Ввод значений элементов                           */

        roomname.put("room1","Ель");
        roomname.put("room2","Дуб");
        roomname.put("room3","Ад");
        for (String room: RoomList){
            roomsost.put(room, 0);
            roomplayers.put(room, 0);
            HMY.put(room, 51);
            HMwait.put(room, 16);
            HMWaitTp.put(room, 20);
            roomtime.put(room, 0);
        }
        for (int x = 1; x <= 6; x++){
            playernum.put(x, null);
        }
    }

}
