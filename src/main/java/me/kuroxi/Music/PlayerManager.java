package me.kuroxi.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import me.kuroxi.Utils.DurationFormat;
import me.kuroxi.Utils.URLChecker;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PlayerManager {
    private static PlayerManager INSTANCE;
    private final Map<Long, GuildMusicManager> musicManagers;
    private final AudioPlayerManager audioPlayerManager;

    public PlayerManager() {
        this.musicManagers = new HashMap<>();

        this.audioPlayerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(audioPlayerManager);
        AudioSourceManagers.registerLocalSource(audioPlayerManager);
    }

    public synchronized GuildMusicManager getMusicManager(Guild guild) {
        long guildID = guild.getIdLong();
        GuildMusicManager musicManager = musicManagers.get(guildID);

        if (musicManager == null) {
            musicManager = new GuildMusicManager(audioPlayerManager);
            musicManagers.put(guildID, musicManager);
        }

        guild.getAudioManager().setSendingHandler(musicManager.getSendHandler());
        return musicManager;
    }

    public void removeMusicManager(Guild guild) {
        musicManagers.remove(guild.getIdLong());
    }

    public void loadAndPlay(SlashCommandInteractionEvent event) {
        GuildMusicManager musicManager = getMusicManager(Objects.requireNonNull(event.getGuild()));

        String prompt = Objects.requireNonNull(event.getOption("song")).getAsString();
        boolean isURL = new URLChecker().check(prompt);
        if (!isURL) prompt = "ytsearch:" + prompt;

        audioPlayerManager.loadItemOrdered(musicManager, prompt, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack audioTrack) {
                musicManager.trackScheduler.queue(audioTrack);
                event.reply(audioTrack.getInfo().title + " added to the queue! (`" + new DurationFormat().format(audioTrack.getDuration()) + "`)").queue();
            }

            @Override
            public void playlistLoaded(AudioPlaylist audioPlaylist) {
                final List<AudioTrack> tracks = audioPlaylist.getTracks();

                if (isURL) {
                    if (!tracks.isEmpty()) {
                        long totalDuration = 0;
                        for (AudioTrack track : tracks) {
                            musicManager.trackScheduler.queue(track);
                            totalDuration += track.getDuration();
                        }

                        event.reply("**" + tracks.size() + "** songs has been added to the queue! (`" + new DurationFormat().format(totalDuration) + "`)").queue();
                    }
                } else {
                    musicManager.trackScheduler.queue(tracks.get(0));
                    event.reply(tracks.get(0).getInfo().title + " added to the queue! (`" + new DurationFormat().format(tracks.get(0).getDuration()) + "`)").queue();
                }
            }

            @Override
            public void noMatches() {
                event.reply("Found nothing").queue();
            }

            @Override
            public void loadFailed(FriendlyException e) {
                event.reply("Failed loading the song").queue();
            }
        });
    }

    public static PlayerManager getINSTANCE() {
        if (INSTANCE == null) INSTANCE =new PlayerManager();
        return INSTANCE;
    }
}
