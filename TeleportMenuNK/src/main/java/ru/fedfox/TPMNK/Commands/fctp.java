package ru.fedfox.TPMNK.Commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import ru.fedfox.TPMNK.Main;

import java.util.Map;

public class fctp extends Command {

    public fctp() {
        super("fctp", "§aКастомные телепорты");
        this.setPermission("fc.fctp.use");
    }

    @Override
    public boolean execute(CommandSender s, String str, String[] args) {
        if (args.length <= 1){
            s.sendMessage("§7Использование /fctp <world/point> <название мира/название точки> <ник>");
        }else if (args[0].length() <= 1){
            s.sendMessage("§7Использование /fctp <world/point> <название мира/название точки> <ник>");
        }else if (args[1].length() <= 1){
            s.sendMessage("§7Использование /fctp <world/point> <название мира/название точки> <ник>");
        }else if (args[2].length() <= 1){
            s.sendMessage("§7Использование /fctp <world/point> <название мира/название точки> <ник>");
        }else{
             if (args[0].equals("world")){
                 Player pl = s.getServer().getPlayer(args[2]);
                 if (pl == null){
                     s.sendMessage("\uE10F §8» §cНе найдено игрока " + args[0]);
                 }else {
                     if (s.getServer().getLevelByName(args[1]) == null){
                         s.sendMessage("\uE10F §8» §cНе найдено такого мира, с названием " + args[1]);
                     }else{
                         pl.teleport(s.getServer().getLevelByName(args[1]).getSpawnLocation());
                     }
                 }
             }else if (args[0].equals("point")){
                 Player pl = s.getServer().getPlayer(args[2]);
                 if (pl == null){
                     s.sendMessage("\uE10F §8» §cНе найдено игрока " + args[0]);
                 }else {
                     if (!Main.config.getKeys().contains(args[1])){
                         s.sendMessage("\uE10F §8» §cНе найдено такогй точки, с названием " + args[1]);
                     }else{
                         pl.teleport(s.getServer().getLevelByName(Main.config.getString(args[1]+".levelname")).getSpawnLocation());
                         pl.teleport(new Location(Main.config.getInt(args[1]+".x"), Main.config.getInt(args[1]+".y"),Main.config.getInt(args[1]+".z")));
                     }
                 }
             }else{
                 s.sendMessage("§7Использование /fctp <world/point> <название мира/название точки> <ник>");
             }
        }
        return false;
    }
}
