package ru.fedfox.WOENK.Tasks;
import cn.nukkit.Player;
import cn.nukkit.level.Location;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.scheduler.PluginTask;
import ru.fedfox.WOENK.Base.RoomBase;
import java.util.HashMap;
import java.util.Map;

public class Barrier extends PluginTask {

    /*                           Объявление Элементов                           */

    private static HashMap<Player, Integer> playerinroom;
    public static void addPIR(HashMap<Player, Integer> playerinroom) {Barrier.playerinroom = playerinroom;}
    public Location center = new Location(128, 53, 128);

    public Barrier(Plugin owner) {
        super(owner);
    }

    /*                           Метод барьера для Игры                           */

    @Override
    public void onRun(int i) {
        for (Map.Entry<Player, Integer> entry : playerinroom.entrySet()) {
            Player pl = entry.getKey();
            String world = pl.getLocation().getLevel().getName();
            if (world.equals("woel1") | world.equals("woel2") | world.equals("woel3")){
                RoomBase.barrier(pl, center);
            }
        }
    }
}
