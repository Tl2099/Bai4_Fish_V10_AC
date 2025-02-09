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
    protected abstract var moveJob: Job?
    var lastUpdateTime = System.nanoTime()
    fun getfFishType() = type

    fun getiId(): Long = this.id
    fun getsSize(): Float = this.size

    // StateFlow cập nhật vị trí để notify UI
    val positionState = MutableStateFlow(Pair(posX, posY))

     val collisionBox: RectF
        get() = RectF(posX, posY, posX + size, posY + size)

    abstract fun draw(canvas: Canvas, paint: Paint)

    abstract fun updatePosition()

    // Steering behaviors
    open var targetAngle = 0f
    open var currentAngle= 0f
    open val maxSpeed = 3f
    open val maxForce = 0.1f
    open val wanderStrength = 0.5f



    abstract suspend fun move(screenWidth: Int, screenHeight: Int)

    abstract fun startMoving(scope: CoroutineScope, screenWidth: Int, screenHeight: Int)

    fun stopMoving() {
        moveJob?.cancel()
        moveJob = null
    }

    open fun applyWanderForce() {
    }

    open fun applyBoundaryForce(screenWidth: Int, screenHeight: Int) {
    }

    open fun limitSpeed() {
    }

    open fun updateRotation() {
    }

    abstract fun constrain(value: Float, min: Float, max: Float):Float
    abstract fun Float.toDegrees():Float

    open val skills: MutableList<FishSkill> = mutableListOf()

    // Khi cá ăn được cá khác
    abstract fun eat(prey: Fish)
    abstract fun die()

    open fun canEat(prey: Fish): Boolean {
        // Chỉ ăn khi kích thước của predator lớn hơn prey
        return this.size > prey.size
    }
}