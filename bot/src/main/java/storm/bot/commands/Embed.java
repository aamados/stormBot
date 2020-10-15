package storm.bot.commands;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import storm.bot.Main;

public class Embed extends ListenerAdapter
{
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent e)
	{
		String[] args = e.getMessage().getContentRaw().split("//s+");
		
		if (args[0].equalsIgnoreCase(Main.prefix + "embed"))
		{
			EmbedBuilder embed = new EmbedBuilder();
			
			embed.setTitle("Test title", null);
			embed.setColor(Color.red);
			embed.setDescription("Test description");
			embed.addField("Test field", "Another test field.", false);
			embed.addBlankField(false);
			embed.setAuthor("Name of author", null);
			embed.setAuthor("Name of author", null);
			embed.setFooter("Footer", null);
			embed.setThumbnail(Main.iconURL);
			
			// Helpful guide on Embedded messages!
			// https://raw.githubusercontent.com/DV8FromTheWorld/JDA/assets/assets/docs/embeds/07-addField.png
			
			e.getChannel().sendMessage(embed.build()).queue();
		}
	}
}
