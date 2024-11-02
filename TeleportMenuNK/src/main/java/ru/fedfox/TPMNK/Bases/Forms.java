package ru.fedfox.TPMNK.Bases;

import cn.nukkit.event.Listener;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;

import java.util.ArrayList;
import java.util.HashMap;

public class Forms implements Listener {

    public static FormWindowSimple createForm(String FormTitle, String FormContent, ArrayList<String> ButtonsList, HashMap<Integer, String> ImagesList){
        FormWindowSimple form = new FormWindowSimple(FormTitle, FormContent);
        ElementButton button;
        ElementButtonImageData buttonImage;
        int i = 0;
        for (String ButtonName : ButtonsList){
            if (ImagesList != null){
                buttonImage = new ElementButtonImageData("path", ImagesList.get(i));
                button = new ElementButton(ButtonName, buttonImage);
                form.addButton(button);
                i++;
            }else{
                button = new ElementButton(ButtonName);
                form.addButton(button);
            }
        }
        return form;
    }

}
