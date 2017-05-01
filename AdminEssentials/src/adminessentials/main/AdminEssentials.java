package adminessentials.main;

import adminessentials.cmds.ClearChatCommand;
import adminessentials.cmds.MuteChatCommand;
import adminessentials.listeners.AsyncPlayerChat;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import adminessentials.cmds.FreezeCommand;
import adminessentials.listeners.FreezeCheck;
import adminessentials.utils.ConfigManager;
import adminessentials.utils.ServerHandler;
import adminessentials.utils.Settings;
import adminessentials.utils.command.CommandFramework;

/**
	 * 
	 * Created on Apr 29, 2017 by Jeremy Gooch.
	 * 
	 */

public class AdminEssentials extends JavaPlugin {
	
	private static AdminEssentials instance;
	
	private CommandFramework framework;
	
	private ConfigManager config;
	
	private ServerHandler serverHandler;
	
	public void onEnable() {
		
		instance = this;
		framework = new CommandFramework(this);
		serverHandler = new ServerHandler(getServer());
		
		config = new ConfigManager("config", null);
		
		Settings.initSettings();
		
		framework.registerCommands(new FreezeCommand());
		framework.registerCommands(new ClearChatCommand());
		framework.registerCommands(new MuteChatCommand());
		
		registerPluginEvents(new FreezeCheck());
		registerPluginEvents(new AsyncPlayerChat());
		
	}
	
	public void onDisable() {
		
		instance = null;
		framework = null;
		
	}
	
	private void registerPluginEvents(Listener... listeners) {
		
		for (Listener listener : listeners) {
			
			getServer().getPluginManager().registerEvents(listener, this);
			
		}
		
	}
	
	public static AdminEssentials get() {
		return instance;
	}
	
	public CommandFramework getCommandFramework() {
		return framework;
	}
	
	public ConfigManager getConfigurationFile() {
		return config;
	}
	
	public ServerHandler getServerHandler() {
		return serverHandler;
	}
	
}
