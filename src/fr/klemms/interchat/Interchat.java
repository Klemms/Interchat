package fr.klemms.interchat;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

import fr.klemms.interchat.events.PluginListener;
import fr.klemms.syncit.ServerListener;
import fr.klemms.syncit.SubscriberListener;
import fr.klemms.syncit.SubscriberSyncChannel;
import fr.klemms.syncit.SyncChannel;
import fr.klemms.syncit.SyncData;
import fr.klemms.syncit.SyncItAPI;
import fr.klemms.syncit.SyncItMessageEvent;

public class Interchat extends JavaPlugin {
	
	// Note that we store our channel in a static variable so we can use it anywhere
	public static SyncChannel chatChannel;

	@Override
	public void onEnable() {
		Config.registerConfig(this);
		this.getServer().getPluginManager().registerEvents(new PluginListener(), this);
		
		// This is how you create a channel - - See PluginListener.java to know how to send messages
		Interchat.chatChannel = SyncItAPI.createChannel(this, "INTERCHAT-CHAT", new ServerListener() {
				@Override
				public void newConnection(UUID connectionID) {
					// Here do what you want to do when a user joins the channel
					// Note that the 'connectionID' is unique to each subscriber
					
				}

				@Override
				public void connectionLost(UUID connectionID) {
					// Here do what you want to do when a user is disconnected
					
				}
				
				@Override
				public void newMessage(UUID connectionID, List<SyncData> dataList, HashMap<String, String> dataMap) {
					// Here do what you want when a user sends data to your channel
					// Data received is available both in a List of SyncData objects and in a map for ease of use
					
					// dataMap.get("player");
					// dataMap.get("message");
				}
			});
		
		// This is how you listen to a channel
		// The subscribe() method returns a SubscriberSyncChannel object that you can use to send messages back to the server, messages are built and sent the same way as they are on the server (See PluginListener.java)
		// NOTE : Here we listen to our own channel, but in practice listening to your own channel is useless
		SubscriberSyncChannel subscriberChannel = SyncItAPI.subscribe(new SubscriberListener() {
			@Override
			public void incomingMessage(SyncItMessageEvent event) {
				// Here we get our values
				// event.getData("player");
				// event.getData("message");
			}
		}, "127.0.0.1", 19005, "INTERCHAT-CHAT");
		
//		 This is how you send a message back to the server as a listener
//		subscriberChannel.newMessage()
//			.addData("player", "Klemms")
//			.addData("message", "Hello, world!")
//			.send();
		
//		This is how you unsubscribe from a channel, you won't receive any more message from this channel from this server
//		subscriberChannel.unsubscribe();
	}

	@Override
	public void onDisable() {
		
	}
}
