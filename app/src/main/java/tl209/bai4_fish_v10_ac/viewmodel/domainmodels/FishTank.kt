package tl209.bai4_fish_v10_ac.viewmodel.domainmodels

import android.annotation.SuppressLint
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.math.sqrt

class FishTank {
    private val _fishList = MutableStateFlow<List<Fish>>(emptyList())
    val fishList: StateFlow<List<Fish>> get() = _fishList
    private val tankScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    fun addFish(fish: Fish) {
        val current = _fishList.value.toMutableList()
        current.add(fish)
        _fishList.value = current
    }

    fun addFishList(fishList: List<Fish>){
        val current = _fishList.value.toMutableList()
        current.addAll(fishList)
        _fishList.value = current
    }

    fun setFishList(newList: List<Fish>) {
        _fishList.value = newList
    }

    @SuppressLint("SuspiciousIndentation")
    fun updateTank(screenWidth: Int, screenHeight: Int) {
        tankScope.launch {
            while (isActive) {
                //Lay danh sach hien tai
                val current = _fishList.value.toMutableList()

                val eaten = mutableSetOf<Fish>()
                for (fishA in current) {
                    for (fishB in current) {
                        if (fishA !== fishB && fishB !in eaten) {
                            if (checkCollision(fishA,fishB)) {
                                handleCollision(fishA,fishB)
                                if (fishA.canEat(fishB)) {
                                    eaten.add(fishB)
                                }
                            }
                        }
                    }
                }
                //
                //Loai bo cac con ca da bi an
                if (eaten.isNotEmpty()) {
                    Log.i("Thread2", "$eaten")
                    current.removeAll(eaten)
                    Log.i("Thread3", "$current")
                    _fishList.value = current
                } else {
                    //Cap nhat danh sach neu vi tri ca thay doi
                    _fishList.value = current
                }
                delay(30L)
            }
        }
    }

    // Hàm kiểm tra va chạm giữa 2 cá
    fun checkCollision(fishA: Fish, fishB: Fish): Boolean {
        val dx = fishA.posX - fishB.posX
        val dy = fishA.posY - fishB.posY
        val distance = sqrt((dx * dx + dy * dy).toDouble()).toFloat()
        return distance < (fishA.size + fishB.size)
    }

    // Hàm xử lý va chạm giữa 2 cá
    fun handleCollision(fishA: Fish, fishB: Fish) {
        if (fishA.canEat(fishB)) {
            fishA.eat(fishB)
        } else if (kotlin.math.abs(fishA.size - fishB.size) <= 1f) {
            // Đảo chiều vận tốc cho cả 2 cá
            fishA.vx = -fishA.vx
            fishA.vy = -fishA.vy
            fishB.vx = -fishB.vx
            fishB.vy = -fishB.vy
        }
    }

    //    suspend fun removeFish(fish: Fish) {
//        val current = _fishList.value.toMutableList()
//        current.remove(fish)
//        _fishList.value = current
//    }

    fun stopTank() {
        tankScope.cancel()
    }
}
