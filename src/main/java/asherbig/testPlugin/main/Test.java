package asherbig.testPlugin.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Test extends JavaPlugin{
	
	@Override
	public void onEnable() {
		new PlayerListener(this);
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
		
		return false;
	}
}
