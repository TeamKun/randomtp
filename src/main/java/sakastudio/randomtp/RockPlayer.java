package sakastudio.randomtp;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class RockPlayer extends BukkitRunnable {
    Randomtp randomtp = Randomtp.Instanse();
    @Override
    public void run() {
        for(PlayerData p : randomtp.UndiscoveryPlayer.values()){
            //to-do　プレイヤーを足止めする処理
        }
    }
}
