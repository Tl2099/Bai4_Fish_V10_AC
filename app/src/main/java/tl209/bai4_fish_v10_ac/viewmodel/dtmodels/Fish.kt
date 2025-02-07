package tl209.bai4_fish_v10_ac.viewmodel.dtmodels

import android.graphics.RectF
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import tl209.bai4_fish_v10_ac.model.skills.FishSkill
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random

open class Fish(
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

    open val collisionBox: RectF
        get() = RectF(posX, posY, posX + size, posY + size)


    open fun checkCollision(other: Fish): Boolean {
        val dx = posX - other.posX
        val dy = posY - other.posY
        val distance = sqrt((dx * dx + dy * dy).toDouble()).toFloat()
        return distance < (size + other.size)
    }

    open fun handleCollision(other: Fish){
        if(this.canEat(other)){
            this.eat(other)
        }else if(kotlin.math.abs(this.size - other.size) <=1f){
            vx =- vx
            vy =- vy
            other.vx = -other.vx
            other.vy = -other.vy
        }
    }

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

    private fun applyWanderForce() {
        val angleNoise = (Random.nextFloat() - 0.5f) * wanderStrength
        targetAngle += angleNoise

        val desiredX = cos(targetAngle) * maxSpeed
        val desiredY = sin(targetAngle) * maxSpeed

        val steerX = desiredX - vx
        val steerY = desiredY - vy

        vx += constrain(steerX, -maxForce, maxForce)
        vy += constrain(steerY, -maxForce, maxForce)
    }

    private fun applyBoundaryForce(screenWidth: Int, screenHeight: Int) {
        val margin = size * 2
        val boundaryForce = 0.2f
        when {
            posX < margin -> vx += boundaryForce
            posX > screenWidth - margin -> vx -= boundaryForce
            posY < margin -> vy += boundaryForce
            posY > screenHeight - margin -> vy -= boundaryForce
        }
    }

    private fun limitSpeed() {
        val speed = sqrt(vx.pow(2) + vy.pow(2))
        if (speed > maxSpeed) {
            vx = vx / speed * maxSpeed
            vy = vy / speed * maxSpeed
        }
    }

    private fun updateRotation() {
        val target = atan2(vy, vx).toDegrees()
        currentAngle = currentAngle + (target - currentAngle) * 0.1f
    }

    open fun move(screenWidth: Int, screenHeight: Int) {
        applyWanderForce()
        applyBoundaryForce(screenWidth, screenHeight)
        limitSpeed()
        updatePosition()
        updateRotation()
    }

    private fun constrain(value: Float, min: Float, max: Float) = value.coerceIn(min, max)
    private fun Float.toDegrees() = this * 180f / PI.toFloat()

    open val skills: MutableList<FishSkill> = mutableListOf()

    // Khi cá ăn được cá khác
    open fun eat(prey: Fish) {
        score += prey.score
        mass += prey.mass
        size += prey.size * 0.1f
        prey.size = 0f
    }

    open fun canEat(prey: Fish): Boolean {
        // Chỉ ăn khi kích thước của predator lớn hơn prey
        return this.size > prey.size
    }

//    fun startMoving(scope: CoroutineScope, fishTank: FishTank) {
//        moveJob = CoroutineScope(Dispatchers.Default).launch {
//            while (isActive) {
//
//                //Test thu doan nay ve doc do
//                //Chay sao cho muon {
//                val now = System.nanoTime()
//                val deltaTime = (now - lastUpdateTime) / 1_000_000_000f  // tính deltaTime bằng giây
//                lastUpdateTime = now
//                // }
//                fishTank.moveFish(this@Fish) // Gọi phương thức moveFish từ FishTank
//                //check.invoke(this@Fish)
//                delay((deltaTime*1000).toLong())  // Delay giữa các lần di chuyển
//            }
//        }
//    }
}