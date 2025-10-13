package no.agentorw.lidarCov2BM;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class LidarCov2BM extends JavaPlugin {
    public final Logger log =  getLogger();

    @Override
    public void onEnable() {
        // Plugin startup logi
        log.info("LidarCov2BM enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        log.info("LidarCov2BM disabled");
    }
}
