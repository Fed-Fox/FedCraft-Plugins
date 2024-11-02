package ru.fedfox.TPMNK.Events;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.form.window.FormWindowSimple;
import ru.fedfox.TPMNK.Utils.FormsLists;

public class ItemsInteract implements Listener {

    @EventHandler
    public void Intaeract (PlayerInteractEvent e){
        FormWindowSimple tpl = FormsLists.tplm();
        FormWindowSimple don = FormsLists.donm();
        FormWindowSimple surv = FormsLists.survm();
        FormWindowSimple mg = FormsLists.mgm();

        Player p = e.getPlayer();
        switch (p.getLocation().getLevel().getName()){
            case "Lobby":
                switch (e.getItem().getId()){
                    case 345:
                        p.showFormWindow(tpl);
                        break;
                    case 341:
                        p.sendActionBar("§cВ настоящее время Группы недоступны", 1, 4, 1);
                        break;
                    case 771:
                        p.sendActionBar("§cВ настоящее время Друзья недоступны", 1, 4, 1);
                        break;
                    case 266:
                        p.showFormWindow(don);
                        break;
                }
                break;
            case "survLobby":
                switch (e.getItem().getId()){
                    case 345:
                        p.showFormWindow(surv);
                        break;
                    case 341:
                        p.sendActionBar("§cВ настоящее время Группы недоступны", 1, 4, 1);
                        break;
                    case 771:
                        p.sendActionBar("§cВ настоящее время Друзья недоступны", 1, 4, 1);
                        break;
                    case 266:
                        p.showFormWindow(don);
                        break;
                }
                break;
            case "MGLobby":
                switch (e.getItem().getId()){
                    case 345:
                        p.showFormWindow(mg);
                        break;
                    case 341:
                        p.sendActionBar("§cВ настоящее время Группы недоступны", 1, 4, 1);
                        break;
                    case 771:
                        p.sendActionBar("§cВ настоящее время Друзья недоступны", 1, 4, 1);
                        break;
                    case 266:
                        p.showFormWindow(don);
                        break;
                }
                break;
        }
    }

}
