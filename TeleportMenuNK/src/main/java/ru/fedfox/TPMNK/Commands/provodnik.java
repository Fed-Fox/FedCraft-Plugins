package ru.fedfox.TPMNK.Commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.form.window.FormWindowSimple;
import ru.fedfox.TPMNK.Utils.FormsLists;

public class provodnik extends Command {

    public provodnik() {
        super("provodnik","§aUI проводника");
        this.setPermission("fc.provodnik.use");
    }

    @Override
    public boolean execute(CommandSender s, String str, String[] args) {
        FormWindowSimple prov = FormsLists.provm();
        if (args.length <= 0){
            s.sendMessage("§7Использование /provodnik <ник>");
        }if (args[0].length() <= 1){
            s.sendMessage("§7Использование /provodnik <ник>");
        }else{
            Player pl = s.getServer().getPlayer(args[0]);
            if (pl == null){
                s.sendMessage("§cНе найдено игрока " + args[0]);
            }else {
                pl.showFormWindow(prov);
            }
        }
        return false;
    }
}
