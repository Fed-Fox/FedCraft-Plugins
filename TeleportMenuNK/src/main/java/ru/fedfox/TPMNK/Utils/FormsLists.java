package ru.fedfox.TPMNK.Utils;

import cn.nukkit.event.Listener;
import cn.nukkit.form.window.FormWindowSimple;
import ru.fedfox.TPMNK.Bases.Forms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class FormsLists implements Listener {

    public static FormWindowSimple tplm(){
        FormWindowSimple tpl;
        ArrayList<String> tpllb = new ArrayList<>(Arrays.asList("▹Выживание◃","▹Мини-игры◃","▹FedCraft◃","▹Гильдии◃", " §cЗакрыть"));
        HashMap<Integer, String> tplli = new HashMap<>();
        tplli.put(0,"textures/items/redstone_dust.png");
        tplli.put(1,"textures/items/blaze_powder.png");
        tplli.put(2,"textures/items/emerald.png");
        tplli.put(3,"textures/items/amethyst_shard.png");
        tplli.put(4,"textures/blocks/barrier.png");
        tpl = Forms.createForm("Меню телепорта из лобби","Выбери нужный режим для телепорта",tpllb, tplli);
        return tpl;
    }

    public static FormWindowSimple ytm(){
        FormWindowSimple yt;
        ArrayList<String> ytlb = new ArrayList<>(Collections.singletonList("Назад"));
        yt = Forms.createForm("Ютюбер","Привелегия означающая что у вас есть ютюб канал.\n\nВозможности:\n- Команда /sing -> Позволяет сыграть мелодию рядом с вами\n- Команда /chatinfo -> Отправить красивое сообщение всему серверу\n- +15% к опыту на Мини-играх\n- Бесплатная шляпа «Солнцезащитные очки»\n- Все команды Стандарт випа",ytlb, null);
        return yt;
    }

    public static FormWindowSimple provm(){
        FormWindowSimple prov;
        ArrayList<String> provlb = new ArrayList<>(Arrays.asList("ул Интремова","ул Главная","ул Хентемова","ул Энтенкина"));
        prov = Forms.createForm("Проводник","Братишка, куда тебя довезти?",provlb, null);
        return prov;
    }

    public static FormWindowSimple survm(){
        FormWindowSimple surv;
        ArrayList<String> survlb = new ArrayList<>(Arrays.asList("Выживание FedFox","Выживание Klavdin","Выживание Defolt", "В лобби"));
        surv = Forms.createForm("Выберите режим выживания","",survlb, null);
        return surv;
    }

    public static FormWindowSimple mgm(){
        FormWindowSimple mg;
        ArrayList<String> mglb = new ArrayList<>(Arrays.asList("Битва на элитрах","Водная схватка","Поймай лиса", "Быстрое действие", "В лобби"));
        mg = Forms.createForm("Выберите мини-игру","",mglb, null);
        return mg;
    }

    public static FormWindowSimple startm(){
        FormWindowSimple start;
        ArrayList<String> startlb = new ArrayList<>(Arrays.asList("§aНачать","§cНазад"));
        start = Forms.createForm("Стартовый гайд","Хотите ли вы пройти начальное обучение?",startlb, null);
        return start;
    }

    public static FormWindowSimple donm(){
        FormWindowSimple don;
        ArrayList<String> donlb = new ArrayList<>(Arrays.asList("Стандарт-Вип","Ютюбер","Вип","Мега-Вип","Модератор", "Админ"));
        don = Forms.createForm("Донаты","Привелегии сервера",donlb, null);
        return don;
    }

}
