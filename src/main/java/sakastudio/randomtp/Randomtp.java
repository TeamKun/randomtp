package sakastudio.randomtp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;

import java.util.*;

public final class Randomtp extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        randomtp = this;
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(this, this);
        radius = 500;
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    int radius = 500;

    //to-do　手動でプレイヤーを開放するコマンド
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        if(cmd.getName().equalsIgnoreCase("exerandomTp")){
            Player p = (Player)sender;
            Random r = new Random();
            double playerX = p.getLocation().getX();
            double playerZ = p.getLocation().getZ();

            Collection<? extends Player> players = Bukkit.getOnlinePlayers();

            for (Player player : players) {
                double x = r.nextInt(radius * 2) - radius + playerX;
                double z = r.nextInt(radius * 2) - radius + playerZ;

                Location location = new Location(p.getWorld(), x,255,z);
                player.teleport(location);
            }
            sender.sendMessage("ランダムTPを実行しました");

            return true;
        }
        if(cmd.getName().equalsIgnoreCase("setRadius")){
            if (args.length != 1){sender.sendMessage("/setRadius <TPの半径>");return false;}

            radius = Integer.parseInt(args[0]);
            sender.sendMessage("半径を" + args[0] + "に設定しました");
            return true;
        }
        return false;
    }

    static Randomtp randomtp;
    public static Randomtp Instanse(){
        return  randomtp;
    }
}
