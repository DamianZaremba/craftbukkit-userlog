package userlog;

import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class UserLogPlayerListener extends PlayerListener {
	public UserLogPlayerListener(UserLog instance) {}
    
	@Override
	public void onPlayerJoin(PlayerJoinEvent event) {
		UserLog.datastore.addUser(event.getPlayer().getName());
	}

	@Override
	public void onPlayerQuit(PlayerQuitEvent event) {
		UserLog.datastore.removeUser(event.getPlayer().getName());
	}
}
