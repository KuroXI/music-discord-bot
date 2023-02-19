package com.github.KuroXI.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import net.dv8tion.jda.api.entities.Guild;


public class GuildMusicManager {
    public final AudioPlayer audioPlayer;
    public final TrackScheduler trackScheduler;

    public GuildMusicManager(AudioPlayerManager audioPlayerManager, Guild guild) {
        audioPlayer = audioPlayerManager.createPlayer();
        trackScheduler = new TrackScheduler(audioPlayer, guild);
        audioPlayer.addListener(trackScheduler);
    }

    public AudioPlayerSendHandler getSendHandler() {
        return new AudioPlayerSendHandler(audioPlayer);
    }
}
