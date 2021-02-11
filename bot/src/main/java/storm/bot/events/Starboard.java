package storm.bot.events;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Message.Attachment;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import storm.bot.utils.EmbedUtils;

public class Starboard extends ListenerAdapter
{
	EmbedUtils embedUtils = new EmbedUtils();
	final int starsNeeded = 6;
	
	@Override
	public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent e)
	{
		long msgId = e.getReaction().getMessageIdLong();
		Message msg = e.getChannel().retrieveMessageById(msgId).complete();
		EmbedBuilder contents = embedUtils.getEmbedTemplate();
		String jumpUrl = msg.getJumpUrl();
		
		// Get all :star: reacts
		MessageReaction reacts = msg.getReactions().stream().filter(r -> r.getReactionEmote().getName().equals(e.getReactionEmote()
				.getName())).findFirst().orElse(null);
		
		if (reacts == null)
			return;		
		
		if (e.getReactionEmote().getName().equalsIgnoreCase("⭐") && (reacts.getCount() == starsNeeded))
		{	
			
			// Not trying to starboard the starboard you know?
			if (e.getChannel().getName().equalsIgnoreCase("starboard"))
				return;
			
			contents.setAuthor(msg.getAuthor().getName(), null, msg.getAuthor().getAvatarUrl());
			
			contents.setDescription(msg.getContentDisplay());
			contents.setFooter(String.valueOf(msgId));
			contents.setTimestamp(Instant.now());
			contents.addField("", "[Jump](" + jumpUrl + ")", true);
			
			List<Attachment> alist = msg.getAttachments().stream().collect(Collectors.toList());
			
			alist.forEach(out -> System.out.println(out));
			
			for (Attachment attachment: msg.getAttachments())
			{
				if (attachment.isImage())
					contents.setImage(attachment.getUrl());
			}
			
			// We check if the message is embedded last
			if (msg.getEmbeds().size() > 0)
				contents.setImage(msg.getEmbeds().get(0).getImage().getUrl());
			
			e.getGuild().getTextChannelsByName("starboard", true).get(0).sendMessage(contents.build()).queue();
			
		}
		
		return;
	}
}
