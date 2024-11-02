package ru.fedfox.WOENK.Base;
import cn.nukkit.Player;
import cn.nukkit.event.Listener;
import cn.nukkit.level.Sound;
import cn.nukkit.network.protocol.PlaySoundPacket;
import cn.nukkit.plugin.PluginBase;

public class UtilBase extends PluginBase implements Listener {

    /*                           Метод для воспроизведения звуков для игрока                           */

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
