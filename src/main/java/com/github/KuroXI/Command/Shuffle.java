package com.github.KuroXI.Command;

import com.github.KuroXI.Music.PlayerManager;
import com.github.KuroXI.Music.TrackScheduler;
import com.github.KuroXI.Utils.VoiceChannelCheck;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Shuffle {
    public Shuffle(SlashCommandInteractionEvent event) {
        if (!new VoiceChannelCheck().check(event)) return;

        TrackScheduler trackScheduler = PlayerManager.getINSTANCE().getMusicManager(event.getGuild()).trackScheduler;
        trackScheduler.shuffleQueue();

        event.reply("The queue has been shuffled!").queue();
    }
}
