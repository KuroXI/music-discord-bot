package com.github.KuroXI.Command;

import com.github.KuroXI.Music.PlayerManager;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.github.KuroXI.Utils.VoiceChannelCheck;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Pause {
    public Pause(SlashCommandInteractionEvent event) {
        if (!new VoiceChannelCheck().check(event)) return;

        AudioPlayer audioPlayer = PlayerManager.getINSTANCE().getMusicManager(event.getGuild()).audioPlayer;

        if (audioPlayer.isPaused()) {
            event.reply("The music is already paused!").queue();
        } else {
            audioPlayer.setPaused(true);
            event.reply("The music has been paused!").queue();
        }
    }
}
