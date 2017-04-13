package fr.klemms.interchat.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.klemms.interchat.Interchat;

public class PluginListener implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		// Sending messages is very easy, just call newMessage() on the SyncChannel you created, you can add as much data as you want, when you're done adding data call send() to send the message
		Interchat.chatChannel.newMessage()
			.addData("player", event.getPlayer().getDisplayName())
			.addData("message", event.getMessage())
			.send();
		
		/* Some examples on how to ignore specific users
		
		It is recommended to store the result of SyncChannel#getUsersSubscribedToThisChannel() when you use it several times in a function because a new object is generated everytime you use it
		
		List<UUID> subscribers = Interchat.chatChannel.getUsersSubscribedToThisChannel();
		
		Interchat.chatChannel.newMessage()
			.addData("player", event.getPlayer().getDisplayName())
			.addData("message", event.getMessage())
			.send(subscribers.get(0));
		
		Interchat.chatChannel.newMessage()
			.addData("player", event.getPlayer().getDisplayName())
			.addData("message", event.getMessage())
			.send(subscribers.toArray(new UUID[subscribers.size()]));
		
		*/
	}
}
