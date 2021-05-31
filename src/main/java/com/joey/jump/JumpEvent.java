package com.joey.jump;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

import static com.joey.jump.Jump.enabled;
import static com.joey.jump.Jump.velocity;

public class JumpEvent implements Listener {
    @EventHandler
    public void toggleFlight(PlayerToggleFlightEvent e) {
        if (enabled) {
            Player player = e.getPlayer();
            Vector vec = player.getLocation().getDirection();
            Vector unitVector = new Vector(vec.getX(), vec.getY(), vec.getZ());

            unitVector.normalize();
            player.setVelocity(unitVector.multiply(velocity));

            player.sendMessage("Super-Jump success!");

            e.setCancelled(true);
        }
    }
}
