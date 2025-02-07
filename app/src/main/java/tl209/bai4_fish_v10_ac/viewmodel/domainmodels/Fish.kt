package tl209.bai4_fish_v10_ac.viewmodel.domainmodels

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import tl209.bai4_fish_v10_ac.model.skills.FishSkill
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random

abstract class Fish(
    open val id: Long = System.currentTimeMillis(),
    open var name: String,
    open var posX: Float,
    open var posY: Float,
    open var size: Float,      // Bán kính của cá
    open var vx: Float, //= Random.nextFloat()* 4 - 2,        // Vận tốc theo trục X
    open var vy: Float, //= Random.nextFloat() * 4 - 2,        // Vận tốc theo trục Y
    open var mass: Float,
    open var score: Int = 0,
    open var type: String = "",
) {
    private var moveJob: Job? = null
    var lastUpdateTime = System.nanoTime()
    fun getFishType() = type



    // StateFlow cập nhật vị trí để notify UI
    val positionState = MutableStateFlow(Pair(posX, posY))

     val collisionBox: RectF
        get() = RectF(posX, posY, posX + size, posY + size)

    abstract fun draw(canvas: Canvas, paint: Paint)

    private fun updatePosition() {
        posX += vx
        posY += vy
    }

    // Steering behaviors
    private var targetAngle = 0f
    private var currentAngle = 0f
    private val maxSpeed = 3f
    private val maxForce = 0.1f
    private val wanderStrength = 0.5f

    fun getCurrentAngle(): Float {
        return currentAngle
    }

    open fun move(screenWidth: Int, screenHeight: Int) {
        applyWanderForce()
        applyBoundaryForce(screenWidth, screenHeight)
        limitSpeed()
        updatePosition()
        updateRotation()
    }

    fun startMoving(scope: CoroutineScope, screenWidth: Int, screenHeight: Int) {
        if (moveJob?.isActive == true) return  // Nếu đã chạy, không khởi chạy lại
        moveJob = scope.launch {
            while (isActive) {
                move(screenWidth, screenHeight)
                // Nếu bạn muốn cập nhật trạng thái (ví dụ: thông qua StateFlow) của cá thì thêm ở đây.
                delay(30L)  // Delay giữa các lần cập nhật, ~33fps
            }
        }
    }

    fun stopMoving() {
        moveJob?.cancel()
        moveJob = null
    }

    open fun applyWanderForce() {
        val angleNoise = (Random.nextFloat() - 0.5f) * wanderStrength
        targetAngle += angleNoise

        val desiredX = cos(targetAngle) * maxSpeed
        val desiredY = sin(targetAngle) * maxSpeed

        val steerX = desiredX - vx
        val steerY = desiredY - vy

        vx += constrain(steerX, -maxForce, maxForce)
        vy += constrain(steerY, -maxForce, maxForce)
    }

    open fun applyBoundaryForce(screenWidth: Int, screenHeight: Int) {
        val margin = size * 2
        val boundaryForce = 0.2f
        when {
            posX < margin -> vx += boundaryForce
            posX > screenWidth - margin -> vx -= boundaryForce
            posY < margin -> vy += boundaryForce
            posY > screenHeight - margin -> vy -= boundaryForce
        }
    }

    open fun limitSpeed() {
        val speed = sqrt(vx.pow(2) + vy.pow(2))
        if (speed > maxSpeed) {
            vx = vx / speed * maxSpeed
            vy = vy / speed * maxSpeed
        }
    }

    open fun updateRotation() {
        val target = atan2(vy, vx).toDegrees()
        currentAngle = currentAngle + (target - currentAngle) * 0.1f
    }

    private fun constrain(value: Float, min: Float, max: Float) = value.coerceIn(min, max)
    private fun Float.toDegrees() = this * 180f / PI.toFloat()

    open val skills: MutableList<FishSkill> = mutableListOf()

    // Khi cá ăn được cá khác
    internal fun eat(prey: Fish) {
        score += prey.score
        mass += prey.mass
        size += prey.size * 0.1f
        prey.size = 0f
    }

    open fun canEat(prey: Fish): Boolean {
        // Chỉ ăn khi kích thước của predator lớn hơn prey
        return this.size > prey.size
    }
}