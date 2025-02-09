package tl209.bai4_fish_v10_ac.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import tl209.bai4_fish_v10_ac.viewmodel.domainmodels.Shark
import tl209.bai4_fish_v10_ac.viewmodel.domainmodels.Shrimp

class ShrimpView(
    id: Long = System.currentTimeMillis(),
    name: String = "Shrimp",
    posX: Float,
    posY: Float,
    size: Float = 10f,
    vx: Float,
    vy: Float,
    mass: Float = 5f,
    score: Int = 5,
    type: String = "Shrimp"
) : Shrimp(id, name, posX, posY, size, vx, vy, mass, score, type) {
    override fun draw(canvas: Canvas, paint: Paint) {
        val collisionRadius: Float = size * 0.8f
        canvas.save()
//        val angle = this.currentAngle  // hoặc this.currentAngle nếu không có getAngle()
//        Log.d("FishView", "Rotating with angle: $angle at (posX: $posX, posY: $posY)")
        canvas.rotate(currentAngle, posX, posY)
        paint.color = color


        // Vẽ thân cá
        canvas.drawOval(
            posX - size,
            posY - size / 2,
            posX + size,
            posY + size / 2,
            paint
        )

        // Vẽ đuôi
        canvas.drawTriangle(
            posX - size * 1.2f, posY,
            posX - size * 1.8f, posY - size / 2,
            posX - size * 1.8f, posY + size / 2,
            paint
        )

        canvas.restore()
        paint.color = android.graphics.Color.BLACK
        paint.textSize = size / 2
        canvas.drawText(score.toString(), posX - size / 2, posY - size, paint)
        // Vẽ vùng va chạm (debug)
        val debugPaint = Paint().apply {
            color = Color.RED
            style = Paint.Style.STROKE
            strokeWidth = 2f
        }
        canvas.drawCircle(posX, posY, collisionRadius, debugPaint)
    }
    private fun Canvas.drawTriangle(x1: Float, y1: Float, x2: Float, y2: Float, x3: Float, y3: Float, paint: Paint) {
        drawPath(android.graphics.Path().apply {
            moveTo(x1, y1)
            lineTo(x2, y2)
            lineTo(x3, y3)
            close()
        }, paint)
    }

    companion object {
        // Tạo NemoView từ một đối tượng Nemo (domain)
        fun fromDomain(shrimp: Shrimp): ShrimpView {
            return ShrimpView(
                id = shrimp.id,
                name = shrimp.name,
                posX = shrimp.posX,
                posY = shrimp.posY,
                size = shrimp.size,
                vx = shrimp.vx,
                vy = shrimp.vy,
                mass = shrimp.mass,
                score = shrimp.score,
                type = shrimp.type,
                //specialAbilityDescription = nemo.specialAbilityDescription
            )
        }
    }
}