package storm.bot.commands;

import java.util.ArrayList;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import storm.bot.Main;
import storm.bot.utils.MessageUtils;

public class CommandManager extends ListenerAdapter
{
	private ArrayList<String> exempt = new ArrayList<>();
	private MessageUtils msg = new MessageUtils();
	
	public CommandManager()
	{
		Main.cmds.put(new Info().getCommandLabel().toLowerCase(), new Info());
		Main.cmds.put(new ListRoles().getCommandLabel().toLowerCase(), new ListRoles());
		Main.cmds.put(new DeleteRole().getCommandLabel().toLowerCase(), new DeleteRole());
		Main.cmds.put(new AddRole().getCommandLabel().toLowerCase(), new AddRole());		
		Main.cmds.put(new Dog().getCommandLabel().toLowerCase(), new Dog());
		Main.cmds.put(new Help().getCommandLabel().toLowerCase(), new Help());
		
		// Argument check exempt.
		exempt.add(new DeleteRole().getCommandLabel().toLowerCase());
		exempt.add(new AddRole().getCommandLabel().toLowerCase());
	}	
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent e)
	{
		String[] args = e.getMessage().getContentRaw().split(" ");
		
		// Check if command exists in commands.
		if (Main.cmds.containsKey(args[0].toLowerCase()))
		{
			// Get command object
			Commands command = Main.cmds.get(args[0]);
			
			int argLen = command.getArgLen();
			
			// Commands that don't necessarily need every argument.
			if (!(exempt.contains(args[0])))
			{
				// Message length v.s. Command Arguments
				if (args.length < argLen + 1|| args.length > argLen + 1)
				{
					// Base usage message.
					String message = "Usage: " + args[0] + " ";
					StringBuilder sb = new StringBuilder(message);
					
					// Iterate over all arguments
					for (String argument : command.getArgs())
					{
						sb.append("<" + argument.toUpperCase() + "> ");
					}
					
					msg.sendMessage(sb.toString(), e);
					
					return;
				}
			}
			
			// Execute Command
			command.executeCommand(e);
		}
		
		return;
	}
}
