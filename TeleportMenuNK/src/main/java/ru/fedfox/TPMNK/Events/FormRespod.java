package ru.fedfox.TPMNK.Events;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.level.Location;
import ru.fedfox.TPMNK.Utils.FormsLists;

public class FormRespod implements Listener {


    @EventHandler
    public void formRespond(PlayerFormRespondedEvent e) {
        FormWindowSimple yt = FormsLists.ytm();
        FormWindowSimple don = FormsLists.donm();
        if (e.getResponse() == null)return;
        Player p = e.getPlayer();
        switch (((FormWindowSimple) e.getWindow()).getTitle()) {
            case "Меню телепорта из лобби":
                if (e.getWindow() instanceof FormWindowSimple) {
                    if (!e.wasClosed()) {
                        switch (((FormResponseSimple) e.getResponse()).getClickedButton().getText()) {
                            case "▹Выживание◃":
                                p.teleport(p.getServer().getLevelByName("survLobby").getSpawnLocation());
                                break;
                            case "▹Мини-игры◃":
                                p.teleport(p.getServer().getLevelByName("MGLobby").getSpawnLocation());
                                break;
                            case "▹FedCraft◃":
                                p.sendActionBar("§cРежим FedCraft пока что недоступен", 1, 4, 1);
                                break;
                            case "▹Гильдии◃":
                                p.sendActionBar("§cРежим Гильдии пока что недоступен", 1, 4, 1);
                                break;
                        }
                    }
                }
            case "Проводник":
                if (e.getWindow() instanceof FormWindowSimple) {
                    if (!e.wasClosed()) {
                        switch (((FormResponseSimple) e.getResponse()).getClickedButton().getText()) {
                            case "ул Главная":
                                p.sendMessage("§7(§bТ§7) §8» §fВас отвезли на §aулицу Главная");
                                p.teleport(new Location(112,4,123));
                                break;
                            case "ул Интремова":
                                p.sendMessage("§7(§bТ§7) §8» §fВас отвезли на §aулицу Интремова");
                                p.teleport(new Location(147,4,105));
                                break;
                            case "ул Хентемова":
                                p.sendMessage("§7(§bТ§7) §8» §fВас отвезли на §aулицу Хентемова");
                                p.teleport(new Location(156,4,156));
                                break;
                            case "ул Энтенкина":
                                p.sendMessage("§7(§bТ§7) §8» §fВас отвезли на §aулицу Энтенкина");
                                p.teleport(new Location(64,4,93));
                                break;
                        }
                    }
                }
            case "Донаты":
                if (e.getWindow() instanceof FormWindowSimple) {
                    if (!e.wasClosed()) {
                        switch (((FormResponseSimple) e.getResponse()).getClickedButton().getText()) {
                            case "Стандарт-Вип":
                                p.sendActionBar("§cВ настоящее время Стандарт вип в разработке", 1, 4, 1);
                                break;
                            case "Ютюбер":
                                p.showFormWindow(yt);
                                break;
                            case "Вип":
                                p.sendActionBar("§cВ настоящее время Вип в разработке", 1, 4, 1);
                                break;
                            case "Мега-Вип":
                                p.sendActionBar("§cВ настоящее время Мега вип в разработке", 1, 4, 1);
                                break;
                            case "Модератор":
                                p.sendActionBar("§cВ настоящее время Модератор в разработке", 1, 4, 1);
                                break;
                            case "Админ":
                                p.sendActionBar("§cВ настоящее время Админ в разработке", 1, 4, 1);
                                break;
                        }
                    }
                }
            case "Ютюбер":
                if (e.getWindow() instanceof FormWindowSimple) {
                    if (((FormResponseSimple) e.getResponse()).getClickedButton().getText().equals("Назад")){
                        e.getPlayer().showFormWindow(don);
                    }
                }
            case "Выберите режим выживания":
                if (e.getWindow() instanceof FormWindowSimple) {
                    switch (((FormResponseSimple) e.getResponse()).getClickedButton().getText()) {
                        case "Выживание FedFox":
                            p.teleport(p.getServer().getLevelByName("ffsurv").getSpawnLocation());
                            e.getPlayer().setGamemode(Player.SURVIVAL);
                            break;
                        case "Выживание Klavdin":
                            p.sendActionBar("§cВ настоящее время выживание Klavdin'а недоступно", 1, 4, 1);
                            break;
                        case "Выживание Defolt":
                            p.sendActionBar("§cВ настоящее время выживание Defoltn'а недоступно", 1, 4, 1);
                            break;
                    }
                }
            case "Выберите мини-игру":
                if (e.getWindow() instanceof FormWindowSimple) {
                    if (!e.wasClosed()) {
                        switch (((FormResponseSimple) e.getResponse()).getClickedButton().getText()) {
                            case "Битва на элитрах":
                                p.getServer().dispatchCommand(p.getServer().getConsoleSender(), "woel join %player%".replace("%player%", p.getName()));
                                break;
                            case "Водная схватка":
                                p.sendActionBar("§cВ настоящее время выживание Водная схватка в разработке", 1, 4, 1);
                                break;
                            case "Поймай лиса":
                                p.sendActionBar("§cВ настоящее время выживание Поймай лиса в разработке", 1, 4, 1);
                                break;
                            case "Быстрое действие":
                                p.sendActionBar("§cВ настоящее время выживание Быстрое действие в разработке", 1, 4, 1);
                                break;
                        }
                    }
                }
        }
    }

}
