package me.kuroxi.Command;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import me.kuroxi.Music.PlayerManager;
import me.kuroxi.Utils.VoiceChannelCheck;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Pause {
    public Pause(SlashCommandInteractionEvent event) {
        if (!new VoiceChannelCheck().check(event)) return;

        AudioPlayer audioPlayer = PlayerManager.getINSTANCE().getMusicManager(event.getGuild()).audioPlayer;

        if (audioPlayer.isPaused()) {
            event.reply("The music is already paused!").queue();
        } else {
            audioPlayer.setPaused(true);
            event.reply("The music has been paused!").queue();
        }
    }
}
