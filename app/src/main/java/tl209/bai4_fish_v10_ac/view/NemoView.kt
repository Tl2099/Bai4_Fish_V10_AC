package tl209.bai4_fish_v10_ac.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import tl209.bai4_fish_v10_ac.viewmodel.domainmodels.Fish
import tl209.bai4_fish_v10_ac.viewmodel.domainmodels.Nemo

class NemoView(
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
    specialAbilityDescription: String = "Quick Recovery"
) : Nemo(id, name, posX, posY, size, vx, vy, mass, score, type, specialAbilityDescription) {


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
            posY - size/2,
            posX + size,
            posY + size/2,
            paint
        )

        // Vẽ đuôi
        canvas.drawTriangle(
            posX - size * 1.2f, posY,
            posX - size * 1.8f, posY - size/2,
            posX - size * 1.8f, posY + size/2,
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
        fun fromDomain(nemo: Nemo): NemoView {
            return NemoView(
                id = nemo.id,
                name = nemo.name,
                posX = nemo.posX,
                posY = nemo.posY,
                size = nemo.size,
                vx = nemo.vx,
                vy = nemo.vy,
                mass = nemo.mass,
                score = nemo.score,
                type = nemo.type,
                specialAbilityDescription = nemo.specialAbilityDescription
            )
        }
    }


}