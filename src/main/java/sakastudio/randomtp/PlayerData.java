package sakastudio.randomtp;

import org.bukkit.entity.Player;

public class PlayerData{
    public Player player;
    public double x;
    public double z;
    public PlayerData(Player _player,double _x,double _z){
        player = _player;
        x = _x;
        z = _z;
    }
}
