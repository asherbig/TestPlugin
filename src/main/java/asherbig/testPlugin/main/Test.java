package asherbig.testPlugin.main;

import org.bukkit.plugin.java.JavaPlugin;

public class Test extends JavaPlugin{
	@Override
	public void onEnable() {
		getLogger().info("Plugin has been enabled!");
	}
	@Override
	public void onDisable() {
		getLogger().info("Plugin has been disabled!");
	}
}
