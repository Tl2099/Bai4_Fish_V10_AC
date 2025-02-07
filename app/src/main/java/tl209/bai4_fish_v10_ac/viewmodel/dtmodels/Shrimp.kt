package tl209.bai4_fish_v10_ac.viewmodel.dtmodels

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Shrimp(
    id: Long = System.currentTimeMillis(),
    name: String = "Shrimp",
    posX: Float,
    posY: Float,
    size: Float = 10f,      // Kích thước cố định nhỏ nhất
    vx: Float,
    vy: Float,
    mass: Float = 5f,       // Khối lượng cố định
    score: Int = 5,          // Điểm nhỏ khi được ăn
    type: String = "D",
) : Fish(id, name, posX, posY, size, vx, vy, mass, score, type) {
    val color: Int = Color.MAGENTA

//    override fun draw(canvas: Canvas, paint: Paint) {
//        paint.color = color
//        canvas.drawCircle(posX, posY, size, paint)
//        paint.color = Color.BLACK
//        paint.textSize = size / 2
//        canvas.drawText(score.toString(), posX - size / 2, posY - size, paint)
//    }
}