package ru.fedfox.TPMNK.Bases;

import cn.nukkit.Player;
import cn.nukkit.event.Listener;
import cn.nukkit.level.Sound;
import cn.nukkit.network.protocol.PlaySoundPacket;

public class Sounds implements Listener {

    public static void playSound(Player player, Sound sound, Float volume){
        PlaySoundPacket packet = new PlaySoundPacket();
        packet.name = sound.getSound();
        packet.volume = volume;
        packet.pitch = 1.0F;
        packet.x = player.getFloorX();
        packet.y = player.getFloorY();
        packet.z = player.getFloorZ();
        player.dataPacket(packet);
    }

}
