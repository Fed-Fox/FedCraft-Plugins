package ru.fedfox.WOENK.Base;
import cn.nukkit.Player;
import cn.nukkit.event.Listener;
import cn.nukkit.item.ItemDye;
import cn.nukkit.level.Location;
import cn.nukkit.plugin.PluginBase;
import java.util.HashMap;
import java.util.Map;

public class CmdBase extends PluginBase implements Listener {

    private static HashMap<String, Integer> roomplayers;
    private static HashMap<Player, Integer> playersost;
    private static HashMap<Player, Integer> playerinroom;
    private static HashMap<Integer, Player> playernum;

    public static void addRP(HashMap<String, Integer> roomplayers) {CmdBase.roomplayers = roomplayers;}
    public static void addPS(HashMap<Player, Integer> playersost) {CmdBase.playersost = playersost;}
    public static void addPIR(HashMap<Player, Integer> playerinroom) {CmdBase.playerinroom = playerinroom;}
    public static void addPN(HashMap<Integer, Player> playernum) {CmdBase.playernum = playernum;}

    public static void playerJoin(String RoomNum, int PlayerNum1, int PlayerNum2, String WorldName, Location First, Location Second, int PIRoom, Player pl){
        roomplayers.put(RoomNum, roomplayers.get(RoomNum) + 1);
        playerinroom.put(pl, PIRoom);
        pl.setGamemode(2);
        pl.teleport(pl.getServer().getLevelByName(WorldName).getSpawnLocation());
        if (roomplayers.get(RoomNum) == 1) {
            pl.teleport(First);
            playernum.put(PlayerNum1, pl);
        }else{
            if (playernum.containsKey(PlayerNum2)){
                if (playernum.get(PlayerNum2) == null){
                    pl.teleport(Second);
                    playernum.put(PlayerNum2, pl);
                }else if (playersost.get(playernum.get(PlayerNum2)) == 1){
                    pl.teleport(First);
                    playernum.put(PlayerNum1, pl);
                }else{
                    pl.teleport(Second);
                    playernum.put(PlayerNum2, pl);
                }
            }
        }
        playersost.put(pl, 1);
        pl.getInventory().clearAll();
        pl.setHealth(20);
        pl.getFoodData().setLevel(20);
        pl.getInventory().setItem(8, new ItemDye(1).setCustomName("§cВыйти"));
        int y = 51;
        while (y != 54){
            pl.getLocation().level.setBlockAt(121,y,134, 20);
            pl.getLocation().level.setBlockAt(122,y,135, 20);
            pl.getLocation().level.setBlockAt(120,y,135, 20);
            pl.getLocation().level.setBlockAt(121,y,136, 20);
            pl.getLocation().level.setBlockAt(134,y,121, 20);
            pl.getLocation().level.setBlockAt(135,y,122, 20);
            pl.getLocation().level.setBlockAt(135,y,120, 20);
            pl.getLocation().level.setBlockAt(136,y,121, 20);
            y++;
        }
        for (Map.Entry<Player, Integer> entry : playerinroom.entrySet()) {
            if (entry.getValue().equals(PIRoom)) {
                Player play = entry.getKey();
                play.sendMessage("\uE19F§f Игрок§a %play%§f присоеденился к вашей игре§8 (§7%players%§8/§72§8)".replace("%play%", pl.getName()).replace("%players%", String.valueOf(roomplayers.get(RoomNum))));
            }
        }
    }

    public static void playerQuit(String RoomNum, int PIRoom, Player pl){
        pl.teleport(pl.getServer().getLevelByName("MGLobby").getSpawnLocation());
        roomplayers.put(RoomNum, roomplayers.get(RoomNum) - 1);
        playersost.put(pl, 0);
        playerinroom.put(pl, 0);
        pl.sendMessage("\uE13F §8»§f Вы вышли из игры!");
        for (Map.Entry<Player, Integer> entry : playerinroom.entrySet()) {
            if (entry.getValue().equals(PIRoom)) {
                Player play = entry.getKey();
                play.sendMessage("\uE18F§f Игрок §c%play%§f вышел из вашей игры §8(§7%players%§8/§72§8)".replace("%play%", pl.getName()).replace("%players%", String.valueOf(roomplayers.get(RoomNum))));
            }
        }
    }
}
