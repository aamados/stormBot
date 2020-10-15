package storm.bot.events;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class EventListeners extends ListenerAdapter {

	@Override
	public void onReady(ReadyEvent e)
	{
		System.out.println("> Storm bot is ready.");
	}

}
