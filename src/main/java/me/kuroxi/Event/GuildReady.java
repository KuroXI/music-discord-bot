package me.kuroxi.Event;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class GuildReady extends ListenerAdapter {
    @Override
    public void onGuildReady(GuildReadyEvent event) {
        event.getJDA().updateCommands().addCommands(
                Commands.slash("pause", "pause the song").setGuildOnly(true),
                Commands.slash("play", "play a song").addOption(OptionType.STRING, "song", "name of a song", true).setGuildOnly(true),
                Commands.slash("resume", "resume the song").setGuildOnly(true),
                Commands.slash("shuffle", "shuffle the queue").setGuildOnly(true),
                Commands.slash("skip", "skip a song").setGuildOnly(true),
                Commands.slash("stop", "stop the queue").setGuildOnly(true)
        ).queue();
    }
}