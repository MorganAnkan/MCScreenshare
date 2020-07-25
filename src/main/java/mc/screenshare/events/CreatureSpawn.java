package mc.screenshare.events;

import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CreatureSpawn implements Listener {
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if(event.getEntityType() == EntityType.SNOWMAN) {
            event.setCancelled(true);
        }
    }
}
