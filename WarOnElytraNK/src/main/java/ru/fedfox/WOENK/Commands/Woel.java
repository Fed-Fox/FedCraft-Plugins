package ru.fedfox.WOENK.Commands;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.level.Location;
import ru.fedfox.WOENK.Base.CmdBase;
import java.util.HashMap;

public class Woel extends Command {

    /*                           Объявление Элементов                           */

    private static HashMap<String, Integer> roomsost;
    private static HashMap<Player, Integer> playersost;
    private static HashMap<Player, Integer> playerinroom;
    private static Player pl;
    private final Location first = new Location(121.50, 51,135.50);
    private final Location second = new Location(135.50, 51,121.50);
    public static void addRM(HashMap<String, Integer> roomsost) {Woel.roomsost = roomsost;}
    public static void addPS(HashMap<Player, Integer> playersost) {Woel.playersost = playersost;}
    public static void addPIR(HashMap<Player, Integer> playerinroom) {Woel.playerinroom = playerinroom;}
    public static void addPl(Player pl) {Woel.pl = pl;}
    public String[] getArgs(){String[] args = new String[2];args[0] = "join";args[1] = "quit";return args;}


    /*                           Установка данных команды                           */

    public Woel() {
        super("woel","§aИгра Битва на Элитрах", "/woel <аргумент> <игрок>", new String[]{"waronelytra"});
        this.setPermission("fc.woel.use");
        commandParameters.clear();
        commandParameters.put("default", new CommandParameter[]{
                CommandParameter.newEnum("args", getArgs()),
                CommandParameter.newType("player", CommandParamType.TARGET)
        });
    }

    /*                           Свойства команды и их выполения                           */

    @Override
    public boolean execute(CommandSender s, String str, String[] args) {
        if(args.length <= 1){
            s.sendMessage("§7§oИспользование /woel <quit/join> <игрок>");
        }else if(args[0].length() <= 1) {
            s.sendMessage("§7§oИспользование /woel <quit/join> <игрок>");
        }else if(args[1].length() <= 1){
            s.sendMessage("§7§oИспользование /woel <quit/join> <игрок>");
        }else{
            pl = Server.getInstance().getPlayer(args[1]);
            switch (args[0]){

                /*                           Субкоманда join                           */

                case "join":
                    if(pl == null){
                        s.sendMessage("\uE10F §8»§f §cНе найдено игрока %play% ,либо он не в сети!".replace("%play%",args[1]));
                    }else{
                        if(roomsost.get("room1") <= 0){
                            if (playersost.get(pl) == 0){
                                CmdBase.playerJoin("room1", 1, 2, "woel1", first, second, 1, pl);
                            }else{
                                s.sendMessage("\uE10F §8»§f §cИгрок %play% сейчас находится в игре".replace("%play%",args[1]));
                            }
                        }else if(roomsost.get("room2") <= 0){
                            if (playersost.get(pl) == 0){
                                CmdBase.playerJoin("room2", 3, 4, "woel2", first, second, 2, pl);
                            }else{
                                s.sendMessage("\uE10F §8»§f §cИгрок %play% сейчас находится в игре".replace("%play%",args[1]));
                            }
                        }else if(roomsost.get("room3") <= 0){
                            if (playersost.get(pl) == 0){
                                CmdBase.playerJoin("room3", 5, 6, "woel3", first, second, 3, pl);
                            }else{
                                s.sendMessage("\uE10F §8»§f §cИгрок %play% сейчас находится в игре".replace("%play%",args[1]));
                            }
                        }else{
                            s.sendMessage("\uE13F §8»§f Не найдено пустых комнат! Подождите чуть-чуть, может скоро комнаты освободятся!");
                            pl.sendMessage("\uE13F §8»§f Не найдено пустых комнат! Подождите чуть-чуть, может скоро комнаты освободятся!");
                        }
                    }
                    break;

                /*                           Субкоманда quit                           */

                case "quit":
                    if(pl == null){
                        s.sendMessage("\uE10F »§f §cНе найдено игрока %play% ,либо он не в сети!".replace("%play%",args[1]));
                    }else{
                        switch (playerinroom.get(pl)){
                            case 1:
                                if(playersost.get(pl) == 1){
                                    CmdBase.playerQuit("room1", 1, pl);
                                }else{
                                    s.sendMessage("\uE10F »§c Игрок %player% не в игре".replace("%player%", pl.getName()));
                                }
                                break;
                            case 2:
                                if(playersost.get(pl) == 1){
                                    CmdBase.playerQuit("room2", 2, pl);
                                }else{
                                    s.sendMessage("\uE10F »§c Игрок %player% не в игре".replace("%player%", pl.getName()));
                                }
                                break;
                            case 3:
                                if(playersost.get(pl) == 1){
                                    CmdBase.playerQuit("room3", 3, pl);
                                }else{
                                    s.sendMessage("\uE10F »§c Игрок %player% не в игре".replace("%player%", pl.getName()));
                                }
                                break;
                        }
                    }
                    break;
                default:
                    s.sendMessage("\uE10F »§f Не найдено аргумента %arg%. Проверьте правильность написания!".replace("%arg%", args[0]));
                    break;
            }
        }
        return false;
    }
}
