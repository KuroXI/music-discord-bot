package me.kuroxi.Command;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import me.kuroxi.Music.PlayerManager;
import me.kuroxi.Music.TrackScheduler;
import me.kuroxi.Utils.VoiceChannelCheck;
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
