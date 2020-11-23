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
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;

import java.util.*;

public final class Randomtp extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        randomtp = this;
        getServer().getPluginManager().registerEvents(this, this);
        radius = 500;
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    int radius = 500;
    //プレイヤーのメッセージを格納
    HashMap<String, String> PlayerMessage = new HashMap<String, String>();
    //未発見プレイヤーを格納
    public HashMap<String, PlayerData> UndiscoveryPlayer = new HashMap<String, PlayerData>();
    //各プレイヤーのスコアを格納
    HashMap<String, Integer> PlayerScore = new HashMap<String, Integer>();


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
                UndiscoveryPlayer.put(player.getName(),new PlayerData(player,x,z));
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
        if(cmd.getName().equalsIgnoreCase("showscore")){
            sender.sendMessage("プレイヤースコア一覧");
            for(Map.Entry<String, Integer> entry : PlayerScore.entrySet()){
                sender.sendMessage(entry.getKey()+":"+entry.getValue());
            }
            return true;
        }
        if(cmd.getName().equalsIgnoreCase("showlist")){
            sender.sendMessage("未発見プレイヤーリスト");
            for(String val : UndiscoveryPlayer.keySet()){
                sender.sendMessage(val);
            }
            return true;
        }
        return false;
    }

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event) {
        //未発見リストからの削除とスコアの加算
        Entity entity = event.getRightClicked();
        if(entity instanceof Player){
            Player target = (Player) entity;
            UndiscoveryPlayer.remove(target.getName());

            if (PlayerScore.get(event.getPlayer().getName()) == null){
                PlayerScore.put(event.getPlayer().getName(),1);
            }else {
                PlayerScore.put(event.getPlayer().getName(),PlayerScore.get(event.getPlayer().getName())+1);
            }
        }
    }

    static Randomtp randomtp;
    public static Randomtp Instanse(){
        return  randomtp;
    }
}
