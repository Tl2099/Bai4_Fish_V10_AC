package tl209.bai4_fish_v10_ac.viewmodel.dtmodels

import tl209.bai4_fish_v10_ac.model.entities.FishData
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

fun FishData.toPresentationFish(): Fish {
    val rad = Math.toRadians(this.direction.toDouble())
    val vx = this.speed * cos(rad).toFloat()
    val vy = this.speed * sin(rad).toFloat()
    return Fish(
        id = this.id ?: System.currentTimeMillis(),
        name = this.name,
        posX = this.posX,
        posY = this.posY,
        size = this.size,
        vx = vx,
        vy = vy,
        mass = this.mass,
        score = this.scoreValue,
        type = this.type
    )
}

fun Fish.toFishData(): FishData {
    val speed = sqrt(vx * vx + vy * vy)
    val direction = Math.toDegrees(atan2(vy.toDouble(), vx.toDouble())).toFloat()
    return FishData(
        id = this.id,
        name = this.name,
        posX = this.posX,
        posY = this.posY,
        size = this.size,
        mass = this.mass,
        speed = speed,
        direction = direction,
        scoreValue = this.score,
        type = this.type
    )
}