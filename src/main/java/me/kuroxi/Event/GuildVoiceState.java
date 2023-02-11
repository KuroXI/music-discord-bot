package me.kuroxi.Event;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import me.kuroxi.Music.PlayerManager;
import me.kuroxi.Utils.Timeout;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class GuildVoiceState extends ListenerAdapter {
    @Override
    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {
        // Join Call
        if (event.getNewValue() != null && event.getOldValue() == null) {
            Member bot = event.getGuild().getSelfMember();

            if (!bot.getVoiceState().inAudioChannel()) return;
            if (!bot.getVoiceState().getChannel().getId().equals(event.getNewValue().getId())) return;
            if (event.getEntity().getUser().isBot()) return;

            AudioPlayer audioPlayer = PlayerManager.getINSTANCE().getMusicManager(event.getGuild()).audioPlayer;
            if (audioPlayer.isPaused()) audioPlayer.setPaused(false);
        }

        // Leave Call
        if (event.getOldValue() != null && event.getNewValue() == null) {
            Member bot = event.getGuild().getSelfMember();

            if (!bot.getVoiceState().inAudioChannel()) return;
            if (!bot.getVoiceState().getChannel().getId().equals(event.getOldValue().getId())) return;
            if (event.getEntity().getUser().isBot()) return;

            AudioPlayer audioPlayer = PlayerManager.getINSTANCE().getMusicManager(event.getGuild()).audioPlayer;
            if (event.getOldValue().getMembers().stream().filter(member -> !member.getUser().isBot()).count() == 0) {

                audioPlayer.setPaused(true);
                new Timeout(() -> {
                    if (Objects.requireNonNull(event.getChannelLeft()).getMembers().size() == 0) return;

                    long count = event.getChannelLeft().getMembers().stream().filter(member -> !member.getUser().isBot()).count();
                    if (count == 0) {
                        audioPlayer.stopTrack();
                        event.getGuild().getAudioManager().closeAudioConnection();
                        PlayerManager.getINSTANCE().removeMusicManager(event.getGuild());
                    }
                }, 30000);
            }
        }

        // Move Call
        if (event.getOldValue() != null && event.getNewValue() != null && !Objects.equals(event.getOldValue(), event.getNewValue())) {
            Member bot = event.getGuild().getSelfMember();

            if (!bot.getVoiceState().inAudioChannel()) return;
            if (!event.getOldValue().getId().equals(bot.getVoiceState().getChannel().getId())) return;
            if (event.getEntity().getUser().isBot()) return;

            AudioPlayer audioPlayer = PlayerManager.getINSTANCE().getMusicManager(event.getGuild()).audioPlayer;
            if (event.getOldValue().getMembers().stream().filter(member -> !member.getUser().isBot()).count() == 0) {

                audioPlayer.setPaused(true);
                new Timeout(() -> {
                    if (Objects.requireNonNull(event.getChannelLeft()).getMembers().size() == 0) return;

                    long count = event.getChannelLeft().getMembers().stream().filter(member -> !member.getUser().isBot()).count();
                    if (count == 0) {
                        audioPlayer.stopTrack();
                        event.getGuild().getAudioManager().closeAudioConnection();
                        PlayerManager.getINSTANCE().removeMusicManager(event.getGuild());
                    }
                }, 30000);
            }
        }
    }
}
