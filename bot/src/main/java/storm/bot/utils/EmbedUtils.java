package storm.bot.utils;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;

public class EmbedUtils 
{
	public EmbedBuilder getEmbedTemplate()
	{
		EmbedBuilder embed = new EmbedBuilder();
		
		embed.setColor(Color.red);
		
		return embed;
	}
}
