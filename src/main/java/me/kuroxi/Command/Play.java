package me.kuroxi.Command;

import me.kuroxi.Music.PlayerManager;
import me.kuroxi.Utils.VoiceChannelCheck;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Play {
    public Play(SlashCommandInteractionEvent event) {
        if (!new VoiceChannelCheck().check(event)) return;

        PlayerManager.getINSTANCE().loadAndPlay(event);
    }
}
