package asherbig.testPlugin.main;

import org.bukkit.ChatColor;
import java.util.HashSet;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Test extends JavaPlugin{
	
	protected static HashSet<UUID> gods = new HashSet<UUID>();
	
	@Override
	public void onEnable() {
		new PlayerListener(this);
		this.saveDefaultConfig();
	}
	@Override
	public void onDisable() {
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("hello") && sender instanceof Player) {
			Player player = (Player) sender;
			player.sendMessage("Hello, " + player.getName() + "!");
			player.sendMessage("You have jump boost for 5 seconds");
			player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, 3));
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("edit")) {
			if (args.length > 0) {
				String message = "";
				for (int i = 0; i < args.length; i++) {
					message += args[i] + " ";
				}
				this.getConfig().set("message", message);
				this.saveConfig();
				sender.sendMessage("Successfully changed sneak message to " + this.getConfig().getString("message"));
				return true;
			} else {
				sender.sendMessage("Usage: /edit <new sneak message>");
			}
		}
		
		if (cmd.getName().equalsIgnoreCase("heal")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				player.setHealth(20);
				player.sendMessage(ChatColor.GREEN + "You have been healed to full health!");
				return true;
			} else {
				sender.sendMessage("You do not need healing, you aren't a person!");
				return false;
			}
		}
		
		if (cmd.getName().equalsIgnoreCase("feed")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				player.setFoodLevel(20);
				player.sendMessage(ChatColor.GREEN + "You have been fed to full hunger!");
				return true;
			} else {
				sender.sendMessage("You do not need feeding, you aren't a person!");
				return false;
			}
		}
		
		if (cmd.getName().equalsIgnoreCase("godmode")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Only players can enter god mode");
				return true;
			}
			Player player = (Player) sender;
			if (gods.contains(player.getUniqueId())) {
				gods.remove(player.getUniqueId());
				player.sendMessage(ChatColor.GOLD + "God mode disabled!");
			} else {
				gods.add(player.getUniqueId());
				player.sendMessage(ChatColor.GOLD + "God mode enabled!");
			}
			return true;
		}
		return false;
	}

}
