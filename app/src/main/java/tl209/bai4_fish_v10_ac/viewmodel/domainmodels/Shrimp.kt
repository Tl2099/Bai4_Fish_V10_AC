package tl209.bai4_fish_v10_ac.viewmodel.domainmodels

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

open class Shrimp(
    id: Long = System.currentTimeMillis(),
    name: String = "Shrimp",
    posX: Float,
    posY: Float,
    size: Float = 10f,      // Kích thước cố định nhỏ nhất
    vx: Float,
    vy: Float,
    mass: Float = 5f,       // Khối lượng cố định
    score: Int = 5,          // Điểm nhỏ khi được ăn
    type: String = "Shrimp",
) : Fish(id, name, posX, posY, size, vx, vy, mass, score, type) {
    val color: Int = Color.MAGENTA
    override fun draw(canvas: Canvas, paint: Paint) {
        TODO("Not yet implemented")
    }
}