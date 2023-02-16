package me.kuroxi.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import me.kuroxi.Utils.Timeout;
import net.dv8tion.jda.api.entities.Guild;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class TrackScheduler extends AudioEventAdapter {
    private final AudioPlayer audioPlayer;
    private BlockingQueue<AudioTrack> queue;
    private final Guild guild;

    public TrackScheduler(AudioPlayer audioPlayer, Guild guild) {
        this.audioPlayer = audioPlayer;
        this.guild = guild;
        this.queue = new LinkedBlockingDeque<>();
    }

    public void queue(AudioTrack audioTrack) {
        if (!audioPlayer.startTrack(audioTrack, true)) queue.offer(audioTrack);
    }

    public void nextTrack() {
        audioPlayer.startTrack(this.queue.poll(), false);
    }

    public void shuffleQueue() {
        List<AudioTrack> list = new ArrayList<>();
        queue.drainTo(list);

        Collections.shuffle(list);

        queue = new LinkedBlockingDeque<>();
        queue.addAll(list);
    }

    public BlockingQueue<AudioTrack> getQueue() {
        return queue;
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        if (endReason.mayStartNext) {
            nextTrack();

            if (getQueue().size() == 0) new Timeout(() -> {
                if (player.getPlayingTrack() == null) {
                    player.stopTrack();

                    guild.getAudioManager().closeAudioConnection();
                    PlayerManager.getINSTANCE().removeMusicManager(guild);
                }
            }, 5 * 60 * 1000);
        }
    }
}
