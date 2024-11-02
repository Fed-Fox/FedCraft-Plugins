package ru.fedfox.TPMNK.Shedulers;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.scheduler.PluginTask;
import ru.fedfox.TPMNK.Main;

import java.util.Map;

public class Items extends PluginTask {

    protected Main plugin;

    public Items(Main instance) {
        super(instance);
        plugin = instance;
    }

    @Override
    public void onRun(int i) {
        for (Map.Entry<Long, Player> entry : plugin.getServer().getLevelByName("Lobby").getPlayers().entrySet()) {
            Player pl = entry.getValue();
            switch (pl.getLocation().getLevel().getName()) {
                case "Lobby":
                    if (!pl.getInventory().getItem(4).getName().equals("§r§eМЕНЮ ТЕЛЕПОРТА") && !pl.getInventory().getItem(8).getName().equals("§r§6ПРОФИЛЬ")) {
                        pl.getInventory().clearAll();
                        pl.getInventory().setItem(4, Item.fromString("compass").setCustomName("§r§eМЕНЮ ТЕЛЕПОРТА"));
                        pl.getInventory().setItem(5, Item.fromString("slime_ball").setCustomName("§r§aГРУППЫ"));
                        pl.getInventory().setItem(6, Item.fromString("amethyst_shard").setCustomName("§r§dДРУЗЬЯ"));
                        pl.getInventory().setItem(7, Item.fromString("gold_ingot").setCustomName("§r§gДОНАТЫ"));
                        pl.getInventory().setItem(8, Item.fromString("honeycomb").setCustomName("§r§6ПРОФИЛЬ"));
                        break;
                    }
                case "survLobby":
                    if (!pl.getInventory().getItem(4).getName().equals("§r§eМЕНЮ ТЕЛЕПОРТА") && !pl.getInventory().getItem(8).getName().equals("§r§6ПРОФИЛЬ")) {
                        pl.getInventory().clearAll();
                        pl.getInventory().setItem(4, Item.fromString("compass").setCustomName("§r§eМЕНЮ ТЕЛЕПОРТА\uF507"));
                        pl.getInventory().setItem(5, Item.fromString("slime_ball").setCustomName("§r§aГРУППЫ"));
                        pl.getInventory().setItem(6, Item.fromString("amethyst_shard").setCustomName("§r§dДРУЗЬЯ"));
                        pl.getInventory().setItem(7, Item.fromString("gold_ingot").setCustomName("§r§gДОНАТЫ"));
                        pl.getInventory().setItem(8, Item.fromString("honeycomb").setCustomName("§r§6ПРОФИЛЬ"));
                        break;
                    }
                case "MGLobby":
                    if (!pl.getInventory().getItem(4).getName().equals("§r§eМЕНЮ ТЕЛЕПОРТА") && !pl.getInventory().getItem(8).getName().equals("§r§6ПРОФИЛЬ")) {
                        pl.getInventory().clearAll();
                        pl.getInventory().setItem(4, Item.fromString("compass").setCustomName("§r§eМЕНЮ ТЕЛЕПОРТА"));
                        pl.getInventory().setItem(5, Item.fromString("slime_ball").setCustomName("§r§aГРУППЫ"));
                        pl.getInventory().setItem(6, Item.fromString("amethyst_shard").setCustomName("§r§dДРУЗЬЯ"));
                        pl.getInventory().setItem(7, Item.fromString("gold_ingot").setCustomName("§r§gДОНАТЫ"));
                        pl.getInventory().setItem(8, Item.fromString("honeycomb").setCustomName("§r§6ПРОФИЛЬ"));
                        break;
                    }
                case "MGsLobbys":
                    if (!pl.getInventory().getItem(4).getName().equals("§r§eМЕНЮ ТЕЛЕПОРТА") && !pl.getInventory().getItem(8).getName().equals("§r§6ПРОФИЛЬ")) {
                        pl.getInventory().clearAll();
                        pl.getInventory().setItem(4, Item.fromString("compass").setCustomName("§r§eМЕНЮ ТЕЛЕПОРТА"));
                        pl.getInventory().setItem(5, Item.fromString("slime_ball").setCustomName("§r§aГРУППЫ"));
                        pl.getInventory().setItem(6, Item.fromString("amethyst_shard").setCustomName("§r§dДРУЗЬЯ"));
                        pl.getInventory().setItem(7, Item.fromString("gold_ingot").setCustomName("§r§gДОНАТЫ"));
                        pl.getInventory().setItem(8, Item.fromString("honeycomb").setCustomName("§r§6ПРОФИЛЬ"));
                        break;
                    }
            }
        }
    }
}
