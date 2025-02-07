package tl209.bai4_fish_v10_ac.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import tl209.bai4_fish_v10_ac.model.reponsitory.FishRepository
import tl209.bai4_fish_v10_ac.viewmodel.dtmodels.Fish
import tl209.bai4_fish_v10_ac.viewmodel.dtmodels.FishFactory
import tl209.bai4_fish_v10_ac.viewmodel.dtmodels.FishTank
import tl209.bai4_fish_v10_ac.viewmodel.dtmodels.toFishData
import tl209.bai4_fish_v10_ac.viewmodel.dtmodels.toPresentationFish

class FishTankVM : ViewModel() {
    // Quản lý bể cá thông qua SharkTank
    private val fishTank = FishTank()
    val fishList: StateFlow<List<Fish>> get() = fishTank.fishList

    // Tải dữ liệu FishData từ Repository và convert sang Fish
    suspend fun loadFishData() {
        val dataList = FishRepository.getFishDataList()
        val presentationList = dataList.map { it.toPresentationFish()}
        Log.i("Thread", "$presentationList")
        fishTank.setFishList(presentationList)
    }

    // Khi UI thay đổi, lưu lại dữ liệu bằng cách chuyển đổi ngược sang FishData
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun saveFishData() {
        val presentationList = fishList.value
        presentationList.forEach { fish ->
            val data = fish.toFishData()
            Log.i("Thread", "$data")

            // Ví dụ: cập nhật hoặc lưu dữ liệu qua Repository
                FishRepository.addOrUpdateFish(data)

        }
    }

    // Thêm cá ngẫu nhiên qua Factory và cập nhật bể
    fun addRandomFish(screenWidth: Int, screenHeight: Int) {
        val fish: Fish = FishFactory.createRandomFish(screenWidth, screenHeight)
        fishTank.addFish(fish)
    }

    // Bắt đầu vòng lặp cập nhật bể cá trên coroutine
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun startTankUpdates(screenWidth: Int, screenHeight: Int) {
        //viewModelScope.launch(Dispatchers.Default) {
            //while (true) {
                fishTank.updateTank(screenWidth, screenHeight)
                saveFishData()
                //delay(30L) // ~33 fps
            //}
        //}
    }
    override fun onCleared() {
        super.onCleared()
        fishTank.stopTank()
    }
}