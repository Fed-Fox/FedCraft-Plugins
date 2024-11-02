package ru.fedfox.WOENK.Tasks;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.scheduler.PluginTask;
import ru.fedfox.WOENK.Base.RoomBase;
import java.util.HashMap;

public class RoomsTeleport extends PluginTask {

    /*                           Объявление Элементов                           */

    private static HashMap<String, Integer> hmWaitTp;
    public static void addHMWT(HashMap<String, Integer> hmWaitTp) {RoomsTeleport.hmWaitTp = hmWaitTp;}

    public RoomsTeleport(Plugin owner) {
        super(owner);
    }

    /*                           Методы для окончания комнаты (20t)                           */

    @Override
    public void onRun(int i) {
        RoomBase.roomTeleport("room1", 1,1,2);
        RoomBase.roomTeleport("room2", 2,3,4);
        RoomBase.roomTeleport("room3", 3,5,6);
    }
}
