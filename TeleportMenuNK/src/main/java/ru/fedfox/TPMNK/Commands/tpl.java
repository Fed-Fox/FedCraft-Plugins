package ru.fedfox.TPMNK.Commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.form.window.FormWindowSimple;
import ru.fedfox.TPMNK.Utils.FormsLists;

public class tpl extends Command {
    public tpl() {
        super("tpl", "§aТелепорт из лобби");
        this.setPermission("fc.tpl.use");
    }

    @Override
    public boolean execute(CommandSender s, String str, String[] args) {
        FormWindowSimple tpl = FormsLists.tplm();
        if (s instanceof Player) {
            Player p = (Player) s;
            p.showFormWindow(tpl);
        }
        return false;
    }
}
