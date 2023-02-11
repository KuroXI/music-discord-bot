package me.kuroxi.Utils;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class VoiceChannelCheck {
    public boolean check(SlashCommandInteractionEvent event) {
        if (!event.getMember().getVoiceState().inAudioChannel()) {
            event.reply("You're not in the voice channel!").queue();
            return false;
        }

        GuildVoiceState botChannel = event.getGuild().getSelfMember().getVoiceState();
        if (botChannel.inAudioChannel()) {
            if (!event.getMember().getVoiceState().getChannel().getId().equals(botChannel.getChannel().getId())) {
                event.reply("We're not in the same voice channel!").queue();
                return false;
            }
        }

        return true;
    }
}
