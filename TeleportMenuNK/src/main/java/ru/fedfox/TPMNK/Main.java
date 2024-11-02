package ru.fedfox.TPMNK;
import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import ru.fedfox.TPMNK.Bases.Forms;
import ru.fedfox.TPMNK.Bases.Sounds;
import ru.fedfox.TPMNK.Commands.fctp;
import ru.fedfox.TPMNK.Commands.lobby;
import ru.fedfox.TPMNK.Commands.provodnik;
import ru.fedfox.TPMNK.Commands.tpl;
import ru.fedfox.TPMNK.Events.Events;
import ru.fedfox.TPMNK.Events.FormRespod;
import ru.fedfox.TPMNK.Events.ItemsInteract;
import ru.fedfox.TPMNK.Shedulers.Items;
import ru.fedfox.TPMNK.Shedulers.PlayerJoin;
import ru.fedfox.TPMNK.Utils.FormsLists;

import java.util.HashMap;

public class Main extends PluginBase implements Listener {

    public static Config config;
    public HashMap<Player, Integer> join = new HashMap<>();

    @EventHandler
    public void PJE(PlayerJoinEvent e){
        join.put(e.getPlayer(), 1);
    }

    @Override
    public void onEnable(){
        this.getServer().getPluginManager().registerEvents(this, this);
        this.getServer().getPluginManager().registerEvents(new Events(), this);
        this.getServer().getPluginManager().registerEvents(new FormRespod(), this);
        this.getServer().getPluginManager().registerEvents(new ItemsInteract(), this);
        this.getServer().getPluginManager().registerEvents(new Forms(), this);
        this.getServer().getPluginManager().registerEvents(new Sounds(), this);
        this.getServer().getPluginManager().registerEvents(new FormsLists(), this);

        this.getServer().getScheduler().scheduleRepeatingTask(new Items(this), 20);
        this.getServer().getScheduler().scheduleRepeatingTask(new PlayerJoin(this), 20);

        this.getServer().getCommandMap().register("", new fctp());
        this.getServer().getCommandMap().register("", new lobby());
        this.getServer().getCommandMap().register("", new provodnik());
        this.getServer().getCommandMap().register("", new tpl());

        PlayerJoin.joinm(join);

        this.getServer().getLogger().info(TextFormat.GREEN + "Плагин TeleportMenuNK включен!");
        config = getConfig();
        saveDefaultConfig();
    }
}