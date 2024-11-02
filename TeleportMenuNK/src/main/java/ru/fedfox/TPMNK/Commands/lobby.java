package ru.fedfox.TPMNK.Commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

public class lobby extends Command {
    public lobby() {
        super("lobby","§aТелепорт в лобби");
        this.setPermission("fc.lobby.use");
    }

    @Override
    public boolean execute(CommandSender s, String str, String[] args) {
        if (s instanceof Player) {
            Player p = (Player) s;
            p.teleport(p.getServer().getLevelByName("Lobby").getSpawnLocation());
        }
        return false;
    }
}
