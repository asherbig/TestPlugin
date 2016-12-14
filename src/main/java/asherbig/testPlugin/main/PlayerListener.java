package asherbig.testPlugin.main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener{
	
	private Test plugin;
	private FileConfiguration config;

	public PlayerListener(Test plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		config = plugin.getConfig();
	}
	
	//sends a message to the player if they are sneaking
	@EventHandler
	public void onSneak(PlayerToggleSneakEvent e) {
		Player player = e.getPlayer();
		if (!player.isSneaking()) {
			player.sendMessage(config.getString("message"));
		}
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
	
	@EventHandler
	public void onDamageReceived(EntityDamageEvent e) {
		Entity attacked_thing = e.getEntity();
		if ((attacked_thing instanceof Player)) {
			Player p = (Player) attacked_thing;
			//turns off player damage in god mode
			if (Test.gods.contains(p.getUniqueId())) {
				e.setCancelled(true);
				p.setFireTicks(0);
			}
		}
	}
	
	@EventHandler
	public void onGettingHungry(FoodLevelChangeEvent e) {
		if (Test.gods.contains(e.getEntity().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onExplosion(EntityExplodeEvent e) {
		if (!config.getBoolean("Block Damage from Explosions")) {
			e.setCancelled(true);
		}
	}
}
