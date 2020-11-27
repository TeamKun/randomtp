package sakastudio.randomtp;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class RockPlayer implements Listener {
    Randomtp randomtp = Randomtp.Instanse();
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        if(randomtp.UndiscoveryPlayer.containsKey(e.getPlayer().getName())){
            if(isMoved(e)) e.setCancelled(true);
        }
    }

    private boolean isMoved(PlayerMoveEvent e) {
        Location from=e.getFrom();
        Location to=e.getTo();
        int fromXPos = from.getBlockX();
        int fromZPos = from.getBlockZ();
        int toXPos = to.getBlockX();
        int toZPos = to.getBlockZ();
        return !(fromXPos==toXPos && fromZPos==toZPos);
    }
}
