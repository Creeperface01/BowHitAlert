package gt.creeperface.bowhitalert

import cn.nukkit.Player
import cn.nukkit.entity.projectile.EntityArrow
import cn.nukkit.event.EventHandler
import cn.nukkit.event.EventPriority
import cn.nukkit.event.Listener
import cn.nukkit.event.entity.ProjectileHitEvent
import cn.nukkit.level.Sound
import cn.nukkit.plugin.PluginBase

/**
 * @author CreeperFace
 */
class BowHitAlert : PluginBase(), Listener {

    override fun onEnable() {
        server.pluginManager.registerEvents(this, this)
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    fun onBowHit(e: ProjectileHitEvent) {
        val entity = e.entity
        val movingPos = e.movingObjectPosition

        if (entity is EntityArrow && movingPos.entityHit != null) {
            val player = entity.shootingEntity

            if (player != null && player is Player) {
                player.level.addSound(player.add(0.toDouble(), player.eyeHeight.toDouble()), Sound.RANDOM_ORB, 1.toFloat(), 1.toFloat(), player)
            }
        }
    }
}