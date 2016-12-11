package asherbig.testPlugin.main;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener{

	public PlayerListener(Test plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onSneak(PlayerToggleSneakEvent e) {
		Player player = e.getPlayer();
		player.sendMessage("No sneaking");
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockHit(BlockBreakEvent e) {
		
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String time = format.format(now);
		
		Player player = e.getPlayer();
		Block item = e.getBlock();
		Material item_material = item.getType();
		String item_name = item_material.toString();
		
		player.sendMessage("You broke a " + item_name + " at " + time);
	}
}
