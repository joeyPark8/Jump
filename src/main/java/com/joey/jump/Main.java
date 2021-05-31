package com.joey.jump;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.BooleanArgument;
import dev.jorel.commandapi.arguments.FloatArgument;
import dev.jorel.commandapi.arguments.IntegerArgument;
import org.bukkit.plugin.java.JavaPlugin;

import static com.joey.jump.Jump.enabled;
import static com.joey.jump.Jump.velocity;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        System.out.println(this.getName() + " is activated");

        getServer().getPluginManager().registerEvents(new JumpEvent(), this);

        new CommandAPICommand("jump")
                .withAliases("jm")
                .withSubcommand(new CommandAPICommand("super")
                        .withSubcommand(new CommandAPICommand("velocity")
                                .withArguments(new FloatArgument("value"))
                                .executesPlayer((player, args) -> {
                                    velocity = (float) args[0];

                                    player.sendMessage("set jump velocity = " + args[0]);
                                })
                        )
                        .withSubcommand(new CommandAPICommand("enable")
                                .withArguments(new BooleanArgument("value"))
                                .executesPlayer((player, args) -> {
                                    enabled = (boolean) args[0];

                                    player.sendMessage("enable jump = " + args[0]);
                                })
                        )
                ).register();
    }

    @Override
    public void onDisable() {
        System.out.println(this.getName() + " is deactivated");
    }
}
