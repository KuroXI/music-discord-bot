package com.github.KuroXI.Command;

import com.github.KuroXI.Music.PlayerManager;
import com.github.KuroXI.Music.TrackScheduler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.github.KuroXI.Utils.VoiceChannelCheck;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Skip {
    public Skip(SlashCommandInteractionEvent event) {
        if (!new VoiceChannelCheck().check(event)) return;

        AudioPlayer audioPlayer = PlayerManager.getINSTANCE().getMusicManager(event.getGuild()).audioPlayer;
        TrackScheduler trackScheduler = PlayerManager.getINSTANCE().getMusicManager(event.getGuild()).trackScheduler;

        event.reply("Skipped " + audioPlayer.getPlayingTrack().getInfo().title).queue();
        trackScheduler.nextTrack();
    }
}
