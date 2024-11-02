package ru.fedfox.TPMNK.Shedulers;

import cn.nukkit.Player;
import cn.nukkit.level.Sound;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.scheduler.PluginTask;
import ru.fedfox.TPMNK.Bases.Sounds;

import java.util.HashMap;
import java.util.Map;

public class PlayerJoin extends PluginTask {

    private static HashMap<Player, Integer> join;
    public static void joinm(HashMap<Player, Integer> join) {PlayerJoin.join = join;}
    public Plugin plugin;

    public PlayerJoin(Plugin owner) {
        super(owner);
        plugin = owner;
    }

    @Override
    public void onRun(int i) {
        if (join != null){
            for (Map.Entry<Player, Integer> entry : join.entrySet()) {
                if (entry.getValue().equals(1)) {
                    Player pl = entry.getKey();
                    pl.teleport(pl.getServer().getLevelByName("Lobby").getSpawnLocation());
                    Sounds.playSound(pl, Sound.NOTE_BELL, 3.0F);
                    pl.sendTitle("\uFF00", "Добро пожаловать");
                    join.remove(pl);
                }
            }
        }
    }
}
