package tl209.bai4_fish_v10_ac.viewmodel.dtmodels

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import tl209.bai4_fish_v10_ac.model.skills.CamouflageSkill

class Salmon(
    id: Long = System.currentTimeMillis(),
    name: String,
    posX: Float,
    posY: Float,
    size: Float,
    vx: Float,
    vy: Float,
    mass: Float,
    score: Int = 80,
    type: String = "B",
    val migrationPattern: String = "North"
) : Fish(id, name, posX, posY, size, vx, vy, mass, score, type) {
    val color: Int = Color.GREEN

    override val skills: MutableList<tl209.bai4_fish_v10_ac.model.skills.FishSkill> = mutableListOf(
        CamouflageSkill()
    )

//    override fun draw(canvas: Canvas, paint: Paint) {
//        canvas.save()
//        canvas.rotate(getCurrentAngle(), posX, posY)
//        // Vẽ thân cá dưới dạng oval
//        canvas.drawOval(
//            posX - size,
//            posY - size / 2,
//            posX + size,
//            posY + size / 2,
//            paint
//        )
//        canvas.restore()
//
//        // Vẽ điểm số trên đầu cá
//        paint.color = Color.BLACK
//        paint.textSize = size / 2
//        canvas.drawText(score.toString(), posX - size / 2, posY - size, paint)
//
//    }
}