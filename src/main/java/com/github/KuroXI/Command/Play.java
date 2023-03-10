package com.github.KuroXI.Command;

import com.github.KuroXI.Music.PlayerManager;
import com.github.KuroXI.Utils.URLChecker;
import com.github.KuroXI.Utils.VoiceChannelCheck;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.Objects;

public class Play {
    public Play(SlashCommandInteractionEvent event) {
        if (!new VoiceChannelCheck().check(event)) return;

        if (!event.getGuild().getSelfMember().getVoiceState().inAudioChannel()) {
            final AudioManager audioManager = event.getGuild().getAudioManager();
            final VoiceChannel voiceChannel = (VoiceChannel) event.getMember().getVoiceState().getChannel();

            audioManager.openAudioConnection(voiceChannel);
        }

        String prompt = Objects.requireNonNull(event.getOption("song")).getAsString();
        if (!new URLChecker().check(prompt)) prompt = "ytsearch:" + prompt;

        PlayerManager.getINSTANCE().loadAndPlay(event, prompt);
    }
}
