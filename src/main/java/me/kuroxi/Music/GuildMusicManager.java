package me.kuroxi.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

public class GuildMusicManager {
    public final AudioPlayer audioPlayer;
    public final TrackScheduler trackScheduler;

    public GuildMusicManager(AudioPlayerManager audioPlayerManager) {
        audioPlayer =audioPlayerManager.createPlayer();
        trackScheduler = new TrackScheduler(audioPlayer);
        audioPlayer.addListener(trackScheduler);
    }

    public AudioPlayerSendHandler getSendHandler() {
        return new AudioPlayerSendHandler(audioPlayer);
    }
}
