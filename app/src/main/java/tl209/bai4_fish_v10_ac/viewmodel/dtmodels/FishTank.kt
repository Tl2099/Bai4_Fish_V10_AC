package tl209.bai4_fish_v10_ac.viewmodel.dtmodels

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

class FishTank {
    private val _fishList = MutableStateFlow<List<Fish>>(emptyList())
    val fishList: StateFlow<List<Fish>> get() = _fishList
    private val tankScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    fun addFish(fish: Fish) {
        val current = _fishList.value.toMutableList()
        current.add(fish)
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

                //Cap nhat vi tri cho tung con ca
                current.forEach { fish ->
                    //Cap nhat chuyen dong cua tung con ca
                    fish.move(screenWidth, screenHeight)
                }
                val eaten = mutableSetOf<Fish>()
                for (fishA in current) {
                    for (fishB in current) {
                        if (fishA !== fishB && fishB !in eaten) {
                            if (fishA.checkCollision(fishB)) {
                                fishA.handleCollision(fishB)
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

    //    suspend fun removeFish(fish: Fish) {
//        val current = _fishList.value.toMutableList()
//        current.remove(fish)
//        _fishList.value = current
//    }

    fun stopTank() {
        tankScope.cancel()
    }
}
