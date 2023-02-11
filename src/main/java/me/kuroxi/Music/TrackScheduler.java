package me.kuroxi.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class TrackScheduler extends AudioEventAdapter {
    private final AudioPlayer audioPlayer;
    private BlockingQueue<AudioTrack> queue;

    public TrackScheduler(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
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
        }
    }
}
