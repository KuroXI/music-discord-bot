package com.github.KuroXI.Command;

import com.github.KuroXI.Music.PlayerManager;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.github.KuroXI.Utils.VoiceChannelCheck;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Stop {
    public Stop(SlashCommandInteractionEvent event) {
        if (!new VoiceChannelCheck().check(event)) return;

        AudioPlayer audioPlayer = PlayerManager.getINSTANCE().getMusicManager(event.getGuild()).audioPlayer;

        if (audioPlayer.getPlayingTrack() == null) {
            event.reply("There's no music playing.").queue();
            return;
        }

        audioPlayer.stopTrack();
        event.getGuild().getAudioManager().closeAudioConnection();
        PlayerManager.getINSTANCE().removeMusicManager(event.getGuild());

        event.reply("The music has been stopped").queue();
    }
}
