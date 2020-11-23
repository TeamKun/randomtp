package sakastudio.randomtp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Team;

import java.util.*;

public final class Randomtp extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        radius = 500;
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    int radius = 500;
    HashMap<String, String> PlayerMessage = new HashMap<String, String>();
    ArrayList<Player> UndiscoveryPlayer = new ArrayList<Player>();


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
                UndiscoveryPlayer.add(player);
            }
            sender.sendMessage("ランダムTPを実行しました");

            return true;
        }
        if(cmd.getName().equalsIgnoreCase("setRadius")){
            if (args.length != 1){sender.sendMessage("/setRadius <TPの半径>");}

            radius = Integer.parseInt(args[0]);
            sender.sendMessage("半径を" + args[0] + "に設定しました");
            return true;
        }
        if(cmd.getName().equalsIgnoreCase("setMessage")){
            if (args.length != 1){sender.sendMessage("/setMessage <メッセージ>");}

            PlayerMessage.put(sender.getName(),args[0]);
            sender.sendMessage("メッセージを設定：" + args[0]);
            return true;
        }
        if(cmd.getName().equalsIgnoreCase("getMessage")){
            if (args.length != 1){sender.sendMessage("/getMessage <プレイヤー名>");}

            if (PlayerMessage.containsKey(args[0])){
                sender.sendMessage(args[0]+":" + PlayerMessage.get(args[0]));
            }else{
                sender.sendMessage(args[0]+"はメッセージを送信していません");
            }
            return true;
        }
        return false;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

    }
}
