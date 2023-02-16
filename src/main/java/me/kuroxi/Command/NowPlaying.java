package me.kuroxi.Command;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import me.kuroxi.Music.PlayerManager;
import me.kuroxi.Utils.DurationFormat;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class NowPlaying {
    public NowPlaying(SlashCommandInteractionEvent event) {
        AudioPlayer audioPlayer = PlayerManager.getINSTANCE().getMusicManager(event.getGuild()).audioPlayer;
        String currentDuration = new DurationFormat().format(audioPlayer.getPlayingTrack().getPosition());
        String totalDuration = new DurationFormat().format(audioPlayer.getPlayingTrack().getDuration());

        if (audioPlayer.getPlayingTrack() != null) {
            event.reply("Currently playing **" + audioPlayer.getPlayingTrack().getInfo().title + "** (`" + currentDuration + "/" + totalDuration + "`)").queue();
        } else {
            event.reply("There's no song that's are currently playing!").queue();
        }
    }
}
