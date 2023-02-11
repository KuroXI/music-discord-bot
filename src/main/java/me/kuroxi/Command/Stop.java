package me.kuroxi.Command;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import me.kuroxi.Music.PlayerManager;
import me.kuroxi.Utils.VoiceChannelCheck;
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
