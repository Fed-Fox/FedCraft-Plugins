package ru.fedfox.WOENK.Base;
import cn.nukkit.Player;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.inventory.InventoryClickEvent;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;
import cn.nukkit.event.player.PlayerDeathEvent;
import cn.nukkit.event.player.PlayerDropItemEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.item.ItemDye;
import cn.nukkit.item.ItemElytra;
import cn.nukkit.item.ItemSwordWood;
import cn.nukkit.level.Location;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.scoreboard.Scoreboard;
import ru.fedfox.WOENK.WarOnElytra;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static cn.nukkit.level.Sound.*;

public class RoomBase extends PluginBase implements Listener {

    private static HashMap<String, Integer> roomsost;
    private static HashMap<String, Integer> roomplayers;
    private static HashMap<Player, Integer> playerinroom;
    private static HashMap<Player, Integer> playersost;
    private static HashMap<Integer, Player> playernum;
    private static HashMap<String, Integer> hMwait;
    private static HashMap<String, Integer> hmWaitTp;
    private static HashMap<String, Integer> roomtime;

    public static void addRS(HashMap<String, Integer> roomsost) {RoomBase.roomsost = roomsost;}
    public static void addRP(HashMap<String, Integer> roomplayers) {RoomBase.roomplayers = roomplayers;}
    public static void addPIR(HashMap<Player, Integer> playerinroom) {RoomBase.playerinroom = playerinroom;}
    public static void addPS(HashMap<Player, Integer> playersost) {RoomBase.playersost = playersost;}
    public static void addPN(HashMap<Integer, Player> playernum) {RoomBase.playernum = playernum;}
    public static void addHMW(HashMap<String, Integer> hMwait) {RoomBase.hMwait = hMwait;}
    public static void addHMWT(HashMap<String, Integer> hmWaitTp) {RoomBase.hmWaitTp = hmWaitTp;}
    public static void addRT(HashMap<String, Integer> roomtime) {RoomBase.roomtime = roomtime;}
    public static Scoreboard WarOnElytraSB = new Scoreboard("WOElSB", Scoreboard.SortOrder.ASCENDING, Scoreboard.DisplaySlot.SIDEBAR);

    public static void roomSetUp(String RoomNum, int PIRoom, int Y){
        if (roomsost.get(RoomNum) == 0){
            if (roomplayers.get(RoomNum) == 2){
                roomsost.put(RoomNum, 1);
                hMwait.put(RoomNum, 16);
                Y = 51;
            }else{
                for (Map.Entry<Player, Integer> entry : playerinroom.entrySet()) {
                    if (entry.getValue().equals(PIRoom)) {
                        Player pl = entry.getKey();
                        pl.sendTip("§aОжидание игроков...");
                        hMwait.put(RoomNum, 16);
                        Y = 51;
                    }
                }
            }
        }if (roomsost.get(RoomNum) == 1){
            if (roomplayers.get(RoomNum) == 1){
                hMwait.put(RoomNum, 16);
                roomsost.put(RoomNum, 0);
            }else{
                hMwait.put(RoomNum, hMwait.get(RoomNum) - 1);
                if (hMwait.get(RoomNum) > 5){
                    for (Map.Entry<Player, Integer> entry : playerinroom.entrySet()) {
                        if (entry.getValue().equals(PIRoom)) {
                            Player pl = entry.getKey();
                            pl.sendTip("До начала игры§a %wait%".replace("%wait%", String.valueOf(hMwait.get(RoomNum)))+ " сек");
                            UtilBase.playSound(pl, RANDOM_CLICK, 1.0F);
                        }
                    }
                }if (hMwait.get(RoomNum) <= 5 && hMwait.get(RoomNum) > 0){
                    for (Map.Entry<Player, Integer> entry : playerinroom.entrySet()) {
                        if (entry.getValue().equals(PIRoom)) {
                            Player pl = entry.getKey();
                            pl.sendTitle("§a%wait%".replace("%wait%", String.valueOf(hMwait.get(RoomNum))), "§fДо старта");
                            UtilBase.playSound(pl, NOTE_BIT, 2.0F);
                        }
                    }
                }if(hMwait.get(RoomNum) == 0){
                    for (Map.Entry<Player, Integer> entry : playerinroom.entrySet()) {
                        if (entry.getValue().equals(PIRoom)) {
                            Player pl = entry.getKey();
                            pl.getInventory().clearAll();
                            pl.getInventory().setChestplate(new ItemElytra());
                            pl.getInventory().setItem(0, new ItemSwordWood());
                            pl.setHealth(20);
                            pl.getFoodData().setLevel(20);
                            pl.sendTitle("\uE1F0\uE1F1\uE1F2", "Не умрите!");
                            while (Y != 54) {
                                pl.getLocation().level.setBlockAt(121, Y, 134, 0);
                                pl.getLocation().level.setBlockAt(122, Y, 135, 0);
                                pl.getLocation().level.setBlockAt(120, Y, 135, 0);
                                pl.getLocation().level.setBlockAt(121, Y, 136, 0);
                                pl.getLocation().level.setBlockAt(134, Y, 121, 0);
                                pl.getLocation().level.setBlockAt(135, Y, 122, 0);
                                pl.getLocation().level.setBlockAt(135, Y, 120, 0);
                                pl.getLocation().level.setBlockAt(136, Y, 121, 0);
                                Y++;
                            }
                            UtilBase.playSound(pl, NOTE_BELL, 3.0F);
                            roomsost.put(RoomNum, 2);
                            Y = 51;
                        }
                    }
                }
            }
        }
    }

    public static void roomWinEvent(PlayerDeathEvent e, Location center, String RoomNum, int PIRoom){
        if (roomsost.get(RoomNum) == 2){
            for (Map.Entry<Player, Integer> entry : playerinroom.entrySet()) {
                if (entry.getValue().equals(PIRoom)) {
                    Player pl = entry.getKey();
                    if (Objects.equals(pl.getName(), e.getEntity().getPlayer().getName())){
                        Player loser = e.getEntity().getPlayer();
                        if (!WarOnElytra.config.getKeys().contains(loser.getName()+".wins")){
                            WarOnElytra.config.set(loser.getName()+".wins", 0);
                            WarOnElytra.config.set(loser.getName()+".kills", 0);
                            WarOnElytra.config.set(loser.getName()+".deaths", 1);
                            WarOnElytra.config.set(loser.getName()+".revards", 0);
                            WarOnElytra.config.save();
                        }else {
                            WarOnElytra.config.set(loser.getName()+".deaths", WarOnElytra.config.getInt(loser.getName()+".deaths") + 1);
                            WarOnElytra.config.save();
                        }
                        e.setCancelled();
                        loser.setGamemode(Player.SPECTATOR);
                        loser.teleport(center);
                        loser.sendMessage("\n§cПОРАЖЕНИЕ\n\n§aПобед§8 -§f %wins§7 (+0)\n§aКилов§8 -§f %kills§7 (+0)\n§aСмертейv8 -§f %deaths§7 (+1)\n§aНаграды§8 -§f %revards§7 (+0)\n\n§o§2Удачной далее игры!\n".replace("%wins", String.valueOf(WarOnElytra.config.getInt(loser.getName()+".wins"))).replace("%kills", String.valueOf(WarOnElytra.config.getInt(loser.getName()+".kills"))).replace("%deaths", String.valueOf(WarOnElytra.config.getInt(loser.getName()+".deaths"))).replace("%revards", String.valueOf(WarOnElytra.config.getInt(loser.getName()+".revards"))));
                        loser.sendTitle("\uE1F3\uE1F4  \uE1F5\uE1F6","верю в твою победу");
                        loser.getInventory().clearAll();
                    }else{
                        Player winer = pl;
                        if (!WarOnElytra.config.getKeys().contains(winer.getName()+".wins")){
                            WarOnElytra.config.set(winer.getName()+".wins", 1);
                            WarOnElytra.config.set(winer.getName()+".kills", 1);
                            WarOnElytra.config.set(winer.getName()+".deaths", 0);
                            WarOnElytra.config.set(winer.getName()+".revards", 2);
                            WarOnElytra.config.save();
                        }else{
                            WarOnElytra.config.set(winer.getName()+".wins", WarOnElytra.config.getInt(winer.getName()+".wins") + 1);
                            WarOnElytra.config.set(winer.getName()+".kills", WarOnElytra.config.getInt(winer.getName()+".kills") + 1);
                            WarOnElytra.config.set(winer.getName()+".revards", WarOnElytra.config.getInt(winer.getName()+".revards") + 2);
                            WarOnElytra.config.save();
                        }
                        winer.sendTitle("\uE1F7\uE1F8  \uE1F9\uE1FA","поздравляю!");
                        winer.getInventory().clearAll();
                        winer.sendMessage("\n§6ПОБЕДА\n\n§aПобед§8 -§f %wins§7 (+1)\n§aКилов§8 -§f %kills§7 (+1)\n§aСмертейv8 -§f %deaths§7 (+0)\n§aНаграды§8 -§f %revards§7 (+2)\n\n§o§2Удачной далее игры!\n".replace("%wins", String.valueOf(WarOnElytra.config.getInt(winer.getName()+".wins"))).replace("%kills", String.valueOf(WarOnElytra.config.getInt(winer.getName()+".kills"))).replace("%deaths", String.valueOf(WarOnElytra.config.getInt(winer.getName()+".deaths"))).replace("%revards", String.valueOf(WarOnElytra.config.getInt(winer.getName()+".revards"))));
                        winer.getServer().dispatchCommand(winer.getServer().getConsoleSender(), "addplayerexp %p 2".replace("%p", winer.getName()));
                        for (Map.Entry<Player, Integer> entry1 : playerinroom.entrySet()) {
                            if (entry.getValue().equals(PIRoom)) {
                                Player play = entry1.getKey();
                                pl.sendActionBar("Игрок§a %winer% §fпобедил!".replace("%winer%", pl.getName()));
                            }
                        }
                    }
                    pl.getInventory().setItem(8, new ItemDye(1).setCustomName("§cВыйти"));
                    UtilBase.playSound(pl, MOB_ENDERDRAGON_DEATH, 4.0F);
                }
            }
            roomsost.put(RoomNum, 3);
        }
    }

    public static void roomEntityDamage(EntityDamageEvent e, String RoomNum, String WorldName){
        if (roomsost.get(RoomNum) == 3){
            if (e.getEntity() instanceof Player){
                if (((Player) e.getEntity()).getPlayer().getLocation().getLevel().getName().equals(WorldName)){
                    if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL)){
                        e.setCancelled();
                    }if (e.getCause().equals(EntityDamageEvent.DamageCause.VOID)){
                        e.setCancelled();
                    }
                }
            }
        }
    }

    public static void roomIntEvent(PlayerInteractEvent e, String RoomNum, String WorldName, int PIRoom){
        if (e.getPlayer().getLocation().getLevel().getName().equals(WorldName)){
            if (e.getItem().getId() == 351){
                Player pl = e.getPlayer();
                pl.teleport(pl.getServer().getLevelByName("MGLobby").getSpawnLocation());
                playersost.put(pl, 0);
                playerinroom.put(pl, 0);
                roomplayers.put(RoomNum, roomplayers.get(RoomNum) - 1);
                pl.sendMessage("\uE13F §8»§f Вы вышли из игры!");
                for (Map.Entry<Player, Integer> entry : playerinroom.entrySet()) {
                    if (entry.getValue().equals(PIRoom)) {
                        Player play = entry.getKey();
                        play.sendMessage("\uE18F§f Игрок §c%play%§f вышел из вашей игры §8(§7%players%§8/§72§8)".replace("%play%", pl.getName()).replace("%players%", String.valueOf(roomplayers.get(RoomNum))));
                    }
                }
            }
        }
    }

    public static void roomTeleport(String RoomNum, int PIRoom, int First, int Second){
        if (roomsost.get(RoomNum) == 3){
            if (hmWaitTp.get(RoomNum) > 0 && hmWaitTp.get(RoomNum) < 10){
                for (Map.Entry<Player, Integer> entry : playerinroom.entrySet()) {
                    if (entry.getValue().equals(PIRoom)) {
                        Player pl = entry.getKey();
                        pl.sendTip("Вы присоеденитесь к новой игре через §a%wait%".replace("%wait%", String.valueOf(hmWaitTp.get(RoomNum))));
                    }
                }
            }if (hmWaitTp.get(RoomNum) == 0) {
                if (playersost.get(playernum.get(First)) == 1){
                    if (playerinroom.get(playernum.get(First)) == PIRoom){
                        Player pl = playernum.get(First);
                        pl.sendMessage("\uE13F §8»§f Вы присоеденены к новой игре!");
                        playersost.put(pl, 0);
                        playerinroom.put(pl, 0);
                        pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "woel join " + pl.getName());
                    }
                }if (playersost.get(playernum.get(Second)) == 0){
                    hmWaitTp.put(RoomNum, 20);
                    roomplayers.put(RoomNum, 0);
                    roomsost.put(RoomNum, 0);
                    roomtime.put(RoomNum, 0);
                }
            }if (hmWaitTp.get(RoomNum) == -1){
                if (playersost.get(playernum.get(Second)) == 1) {
                    if (playerinroom.get(playernum.get(Second)) == PIRoom){
                        Player pl = playernum.get(Second);
                        pl.sendMessage("\uE13F §8»§f Вы присоеденены к новой игре!");
                        playersost.put(pl, 0);
                        playerinroom.put(pl, 0);
                        hmWaitTp.put(RoomNum, 20);
                        pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "woel join " + pl.getName());
                        roomplayers.put(RoomNum, 0);
                        roomsost.put(RoomNum, 0);
                        roomtime.put(RoomNum, 0);
                    }
                }
            }
            hmWaitTp.put(RoomNum, hmWaitTp.get(RoomNum) - 1);
        }
    }

    public static void barrier(Player pl, Location center){
        if (pl.getLocation().getX() > 142 | pl.getLocation().getX() < 114 | pl.getLocation().getZ() > 142 | pl.getLocation().getZ() < 114){
            pl.teleport(center);
            pl.sendMessage("\uE13F §8»§c Нельзя выходить за карту!");
        }
    }

    public static void cmdUse(Player pl, String cmd, String WorldName, PlayerCommandPreprocessEvent e){
        if (pl.getLocation().getLevel().getName().equals(WorldName)){
            if (cmd.contains("/")){
                if(!pl.hasPermission("fc.nozaptocmdwoel.use")){
                    if(!cmd.replace(" ","").equals("/report")){
                        if(!cmd.replace(" ","").equals("/woelquit"+pl.getName())){
                            if(!cmd.replace(" ","").equals("/woeljoin"+pl.getName())){
                                e.setCancelled(true);
                                pl.sendMessage("\uE10F §8»§f §cВы не можете использовать тут команду %cmd%".replace("%cmd%",cmd).replace("/",""));
                            }
                        }
                    }
                }
            }
        }
    }

    public static void dropCanc(Player pl, PlayerDropItemEvent e, String WorldName){
        if (pl.getLocation().getLevel().getName().equals(WorldName)){
            pl.sendMessage("\uE10F §8»§c Но-но-но, тут нельзя разбрасываться!");
            e.setCancelled(true);
        }
    }

    public static void clickTr(Player pl, InventoryClickEvent e, String WorldName){
        if (pl.getLocation().getLevel().getName().equals(WorldName)) {
            if (e.getSourceItem().getId() != 444) {
                pl.sendMessage("\uE10F §8»§c Но-но-но, не трогай эту штучку!");
                e.setCancelled(true);
            }
        }
    }

    public static void bbevent(Player pl, BlockBreakEvent e, String WorldName){
        if (e.getPlayer().getLocation().getLevel().getName().equals(WorldName)){
            if (e.getPlayer().getGamemode() == Player.SURVIVAL | e.getPlayer().getGamemode() == Player.ADVENTURE){
                pl.sendMessage("\uE10F §8»§c Но-но-но, не трогай эти блоки!");
                e.setCancelled();
            }
        }
    }

    public static void playerQuit(Player pl, String RoomNum, String WorldName, Integer PIRoom){
        if (pl.getLocation().getLevel().getName().equals(WorldName)) {
            if (roomsost.get(RoomNum) == 0 | roomsost.get(RoomNum) == 1 | roomsost.get(RoomNum) == 3) {
                roomplayers.put(RoomNum, roomplayers.get(RoomNum) - 1);
                playersost.put(pl, 0);
                playerinroom.put(pl, 0);
                for (Map.Entry<Player, Integer> entry : playerinroom.entrySet()) {
                    if (entry.getValue().equals(PIRoom)) {
                        Player play = entry.getKey();
                        play.sendMessage("\uE18F§f Игрок §c%play% §fвышел из вашей игры §8(§7%players%§8/§72§8)".replace("%play%", pl.getName()).replace("%players%", String.valueOf(roomplayers.get(RoomNum))));
                    }
                }
            }else if (roomsost.get(RoomNum) == 2) {
                if (playerinroom.get(pl) == 1){
                    playersost.put(pl, 0);
                    playerinroom.put(pl, 0);
                    for (Map.Entry<Player, Integer> entry : playerinroom.entrySet()) {
                        if (entry.getValue().equals(PIRoom)) {
                            Player play = entry.getKey();
                            play.sendMessage("\uE18F§f Игрок §c%play% §fвышел из вашей игры §8(§7%players%§8/§72§8)".replace("%play%", pl.getName()).replace("%players%", String.valueOf(roomplayers.get(RoomNum))));
                            play.getInventory().clearAll();
                            play.sendTitle("\uE1F7\uE1F8  \uE1F9\uE1FA","поздравляю!");
                            pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(),"title " + pl.getName() + " actionbar " + " \"Игрок§a %winer% §fпобедил!\"".replace("%winer%", pl.getName()));
                            play.getInventory().setItem(0,new ItemDye(1).setCustomName("§cВыход"));
                        }
                    }
                    roomsost.put("room1", 3);
                }
            }
        }
    }

    public static void gameTime(String RoomName, int PIRoom){
        if (roomsost.get(RoomName).equals(2)){
            roomtime.put(RoomName, roomtime.get(RoomName) + 1);
            for (Map.Entry<Player, Integer> entry : playerinroom.entrySet()) {
                if (entry.getValue().equals(PIRoom)) {
                    Player pl = entry.getKey();
                    WarOnElytraSB.setScore("\uE1BF", roomtime.get(RoomName));
                    WarOnElytraSB.showTo(pl);
                }
            }
        }
    }
}
