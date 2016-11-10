package fr.klemms.interchat;

import org.bukkit.plugin.java.JavaPlugin;

import fr.klemms.interchat.events.PluginListener;
import fr.klemms.syncit.ServerListener;
import fr.klemms.syncit.SubscriberListener;
import fr.klemms.syncit.SyncChannel;
import fr.klemms.syncit.SyncItAPI;
import fr.klemms.syncit.SyncItMessageEvent;

public class Interchat extends JavaPlugin {
	
	// Note that we stone our channel in a static variable so we can use it anywhere
	public static SyncChannel chatChannel;

	@Override
	public void onEnable() {
		Config.registerConfig(this);
		this.getServer().getPluginManager().registerEvents(new PluginListener(), this);
		
		// This is how you create a channel - - See PluginListener.java to know how to send messages
		Interchat.chatChannel = SyncItAPI.createChannel(this, "INTERCHAT-CHAT", new ServerListener() {
				@Override
				public void newConnection() {
					// Here do what you want to do when a user joins the channel
				}
			});
		
		// This is how you listen to a channel
		// NOTE : Here we listen to our own channel, but in practice listening to your own channel is useless
		SyncItAPI.subscribe(new SubscriberListener() {
			@Override
			public void incomingMessage(SyncItMessageEvent event) {
				// Here we get our values
				// event.getData("player");
				// event.getData("message");
			}
		}, "127.0.0.1", 19005, "INTERCHAT-CHAT");
	}

	@Override
	public void onDisable() {
		
	}
}
