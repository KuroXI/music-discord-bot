package me.kuroxi.Event;

import me.kuroxi.Command.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Interaction extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getInteraction().getName().equals("play")) new Play(event);
        if (event.getInteraction().getName().equals("skip")) new Skip(event);
        if (event.getInteraction().getName().equals("pause")) new Pause(event);
        if (event.getInteraction().getName().equals("resume")) new Resume(event);
        if (event.getInteraction().getName().equals("stop")) new Stop(event);
        if (event.getInteraction().getName().equals("shuffle")) new Shuffle(event);
    }
}
