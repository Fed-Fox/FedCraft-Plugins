package ru.fedfox.WOENK.Tasks;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.scheduler.PluginTask;
import ru.fedfox.WOENK.Base.RoomBase;
import java.util.HashMap;

public class Rooms extends PluginTask {

    /*                           Объявление Элементов                            */

    private static HashMap<String, Integer> hMwait;
    private static HashMap<String, Integer> hmy;
    public static void addHMW(HashMap<String, Integer> hMwait) {Rooms.hMwait = hMwait;}
    public static void addHMY(HashMap<String, Integer> hmy) {Rooms.hmy = hmy;}

    public Rooms(Plugin owner) {
        super(owner);
    }

    /*                           Методы для начала игры (20t)                           */

    @Override
    public void onRun(int i) {
        RoomBase.roomSetUp("room1", 1, hmy.get("room1"));
        RoomBase.roomSetUp("room2", 2, hmy.get("room2"));
        RoomBase.roomSetUp("room3", 3, hmy.get("room3"));
        RoomBase.gameTime("room1", 1);
        RoomBase.gameTime("room2", 2);
        RoomBase.gameTime("room3", 3);
    }
}
