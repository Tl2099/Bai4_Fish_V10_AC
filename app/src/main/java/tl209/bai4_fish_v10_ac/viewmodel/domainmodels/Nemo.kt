package tl209.bai4_fish_v10_ac.viewmodel.domainmodels

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import tl209.bai4_fish_v10_ac.model.skills.EnhancedSpecialAbilitySkill
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random

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
    override var moveJob: Job? = null

    override fun draw(canvas: Canvas, paint: Paint) {
        println("Draw Nemo")
    }


    override val skills: MutableList<tl209.bai4_fish_v10_ac.model.skills.FishSkill> = mutableListOf(
        EnhancedSpecialAbilitySkill(boostFactor = 1.5f, description = specialAbilityDescription)
    )


    override var targetAngle = 0f
    override var currentAngle = 0f
        get() = field
        set(value) {
            field = value
        }
    override val maxSpeed = 3f
    override val maxForce = 0.1f
    override val wanderStrength = 0.5f


    override fun constrain(value: Float, min: Float, max: Float) = value.coerceIn(min, max)
    override fun Float.toDegrees() = this * 180f / PI.toFloat()

    override suspend fun move(screenWidth: Int, screenHeight: Int) {
        applyWanderForce()
        applyBoundaryForce(screenWidth, screenHeight)
        limitSpeed()
        updatePosition()
        Log.d("FishVelocity", "vx: $vx, vy: $vy")
        updateRotation()
    }

    override fun startMoving(scope: CoroutineScope, screenWidth: Int, screenHeight: Int) {
        Log.d("FishMove", "startMoving() called for fish: $name")
        if (moveJob?.isActive == true) return  // Nếu đã chạy, không khởi chạy lại
        moveJob = scope.launch {
            while (isActive) {
                move(screenWidth, screenHeight)
                Log.d("FishMove", "move() called at: ${System.currentTimeMillis()}")
                // Nếu bạn muốn cập nhật trạng thái (ví dụ: thông qua StateFlow) của cá thì thêm ở đây.
                delay(30L)  // Delay giữa các lần cập nhật, ~33fps
            }
        }
    }

    override fun updatePosition() {
        posX += vx
        posY += vy

    }

    override fun applyWanderForce() {
        val angleNoise = (Random.nextFloat() - 0.5f) * wanderStrength
        targetAngle += angleNoise

        val desiredX = cos(targetAngle) * maxSpeed
        val desiredY = sin(targetAngle) * maxSpeed

        val steerX = desiredX - vx
        val steerY = desiredY - vy

        vx += constrain(steerX, -maxForce, maxForce)
        vy += constrain(steerY, -maxForce, maxForce)
    }

    override fun applyBoundaryForce(screenWidth: Int, screenHeight: Int) {
        val margin = size * 2
        val boundaryForce = 0.2f
        when {
            posX < margin -> vx += boundaryForce
            posX > screenWidth - margin -> vx -= boundaryForce
            posY < margin -> vy += boundaryForce
            posY > screenHeight - margin -> vy -= boundaryForce
        }
    }

    override fun limitSpeed() {
        val speed = sqrt(vx.pow(2) + vy.pow(2))
        if (speed > maxSpeed) {
            vx = vx / speed * maxSpeed
            vy = vy / speed * maxSpeed
        }
    }

    override fun updateRotation() {
        val target = atan2(vy, vx).toDegrees()
        val oldAngle = currentAngle
        currentAngle += (target - currentAngle) * 0.3f
        Log.d("FishRotation", "oldAngle: $oldAngle, target: $target, newAngle: $currentAngle")
    }

    override fun eat(prey: Fish) {
        score += prey.score
        mass += prey.mass
        size += prey.size * 0.3f
        prey.size = 0f
    }

    override fun die() {
        moveJob?.cancel()
    }

}