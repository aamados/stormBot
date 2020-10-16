package storm.bot.commands;

import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.HierarchyException;
import storm.bot.Main;
import storm.bot.utils.MessageUtils;

public class AddRole implements Commands
{
	MessageUtils util = new MessageUtils();

	@Override
	public String getCommandLabel() 
	{
		return Main.prefix + "addRole";
	}

	@Override
	public ArrayList<String> getArgs() 
	{
		ArrayList<String> args = new ArrayList<>();
		
		args.add("role");
		args.add("@user");
		return args;
	}

	@Override
	public int getArgLen() 
	{
		return getArgs().size();
	}

	@Override
	public void executeCommand(GuildMessageReceivedEvent e) 
	{
		String[] args = e.getMessage().getContentRaw().split(" ");
		
		if (!(e.getGuild().getMember(e.getAuthor()).hasPermission(Permission.ADMINISTRATOR)))
		{
			util.sendErrorMessage("You don't have permission to use this command.", e);
		}
		
		if (!(e.getChannel().getName().equalsIgnoreCase("roles")))
		{
			util.sendErrorMessage("This command must be executed in the #roles channel.", e);
			return;
		}
					
		if (args.length == 1 || args.length > 3)
		{
			util.sendErrorMessage("Usage: .addRole <ROLE> <@USER> OR .addRole <ROLE>", e);
			return;
		}
		

		// If the sender doesn't tell us a user, assume its to self.
		if (args.length == 2)
		{
			List<Role> roleArg = e.getGuild().getRolesByName(args[1], true);
			Role role = (roleArg.size() > 0) ? roleArg.get(0) : null;
			
			if (role == null)
			{
				util.sendErrorMessage("That role doesn't exist. use '.listroles.' to list all roles.", e);
				return;
			}
			
			long sender = e.getMessage().getAuthor().getIdLong();
			
			System.out.println("Author: " + sender + "\nRole: " + role.toString());
			
			// If we try to add a role that is higher or equal
			try
			{
				e.getGuild().addRoleToMember(sender, role).complete();
			}
			catch(HierarchyException errorEvent)
			{
				util.sendErrorMessage(errorEvent.getLocalizedMessage(), e);
				return;
			}
			
			util.sendMessage("Added the " + args[1] + " role to yourself.", e);
			return;
		}
		
		// If the sender tells us a person to add a role to
		if (args.length == 3)
		{
			List<Role> roleArg = e.getGuild().getRolesByName(args[1], true);
			Role role = (roleArg.size() > 0) ? roleArg.get(0) : null;
			
			List<Member> mentioned = e.getMessage().getMentionedMembers();
			Member user = (mentioned.size() > 0) ? mentioned.get(0) : null;
							
			// Checks if author mentioned someone.
			if (user == null)
			{
				util.sendErrorMessage("You must mention a user.", e); 
				return;
			}
			
			if (role == null)
			{
				util.sendErrorMessage("That role doesn't exist. use '.listroles.' to list all roles.", e);
				return;
			}
			
			// If we try to add a role that is higher or equal
			try
			{
				e.getGuild().addRoleToMember(user, role).complete();
			}
			catch(HierarchyException errorEvent)
			{
				util.sendErrorMessage(errorEvent.getLocalizedMessage(), e);
				return;
			}
			
			util.sendMessage("Added the " + args[1] + " role to " + user.getNickname(), e);
		}
		return;
	}

	@Override
	public String getHelp() 
	{
		return "Adds the role of a given user.";
	}

}
