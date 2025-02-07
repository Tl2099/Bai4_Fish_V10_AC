package tl209.bai4_fish_v10_ac.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import tl209.bai4_fish_v10_ac.viewmodel.dtmodels.Nemo

class NemoView{
    fun drawNemo(canvas: Canvas, paint: Paint, fish: Nemo) {
        val collisionRadius: Float = fish.size * 0.8f
        canvas.save()
        canvas.rotate(fish.getCurrentAngle(), fish.posX, fish.posY)
        paint.color = fish.color


        // Vẽ thân cá
        canvas.drawOval(
            fish.posX - fish.size,
            fish.posY - fish.size/2,
            fish.posX + fish.size,
            fish.posY + fish.size/2,
            paint
        )

        // Vẽ đuôi
        canvas.drawTriangle(
            fish.posX - fish.size * 1.2f, fish.posY,
            fish.posX - fish.size * 1.8f, fish.posY - fish.size/2,
            fish.posX - fish.size * 1.8f, fish.posY + fish.size/2,
            paint
        )

        canvas.restore()
        paint.color = android.graphics.Color.BLACK
        paint.textSize = fish.size / 2
        canvas.drawText(fish.score.toString(), fish.posX - fish.size / 2, fish.posY - fish.size, paint)
        // Vẽ vùng va chạm (debug)
        val debugPaint = Paint().apply {
            color = Color.RED
            style = Paint.Style.STROKE
            strokeWidth = 2f
        }
        canvas.drawCircle(fish.posX, fish.posY, collisionRadius, debugPaint)
    }
    private fun Canvas.drawTriangle(x1: Float, y1: Float, x2: Float, y2: Float, x3: Float, y3: Float, paint: Paint) {
        drawPath(android.graphics.Path().apply {
            moveTo(x1, y1)
            lineTo(x2, y2)
            lineTo(x3, y3)
            close()
        }, paint)
    }
}