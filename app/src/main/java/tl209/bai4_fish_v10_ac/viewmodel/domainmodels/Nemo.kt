package tl209.bai4_fish_v10_ac.viewmodel.domainmodels

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import tl209.bai4_fish_v10_ac.model.skills.EnhancedSpecialAbilitySkill

open class Nemo(
    id: Long = System.currentTimeMillis(),
    name: String,
    posX: Float,
    posY: Float,
    size: Float,
    vx: Float,
    vy: Float,
    mass: Float,
    score: Int = 70,
    type: String = "Nemo",
    val specialAbilityDescription: String = "Quick Recovery"
) : Fish(id, name, posX, posY, size, vx, vy, mass, score, type) {

    val color: Int = Color.BLUE

    override fun draw(canvas: Canvas, paint: Paint) {
        TODO("Not yet implemented")
    }


    override val skills: MutableList<tl209.bai4_fish_v10_ac.model.skills.FishSkill> = mutableListOf(
        EnhancedSpecialAbilitySkill(boostFactor = 1.5f, description = specialAbilityDescription)
    )
}