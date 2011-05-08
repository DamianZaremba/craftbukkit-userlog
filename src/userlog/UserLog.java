package userlog;

import java.util.logging.Logger;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

public class UserLog extends JavaPlugin {
	protected static final Logger log	= Logger.getLogger("Minecraft");
	public UserLogPlayerListener listner	= new UserLogPlayerListener(this);

	public static UserLogFile datastore;
	public String name;
	public String version;

	public static final String logFile	= "userlog.dat";

	@Override
	public void onDisable() {
		datastore.removeAllUsers();
		log.info(
			getDescription().getName() + " " + getDescription().getVersion() + " disabled"
		);
	}

	@Override
	public void onEnable() {
		name = getDescription().getName();
		version = getDescription().getVersion();

		getServer().getPluginManager().registerEvent(Event.Type.PLAYER_JOIN, listner, Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Event.Type.PLAYER_QUIT, listner, Priority.Normal, this);

		datastore = new UserLogFile();		
		if (!datastore.init()) {
			log.severe(
				name + ": Could not init the log file"
			);
			getServer().getPluginManager().disablePlugin(this);
			return;
		}

		datastore.removeAllUsers();
		Player[] players = this.getServer().getOnlinePlayers();
		for (Player player : players) {
			datastore.addUser(player.getName());
		}
		log.info(
			name + " is enabled!"
		);
	}
}
