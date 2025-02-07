package tl209.bai4_fish_v10_ac.model.reponsitory

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.withContext
import tl209.bai4_fish_v10_ac.model.entities.FishData
import tl209.bai4_fish_v10_ac.model.store.FishStore

object FishRepository {
    private val _listUpdated = MutableSharedFlow<List<FishData>>() // Phát danh sách đã cập nhật
    val listUpdated: SharedFlow<List<FishData>> = _listUpdated
    private val items = mutableListOf<FishData>()


    suspend fun getFishDataList(): List<FishData>{
        return withContext(Dispatchers.IO){
            return@withContext FishStore.getAllFish()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun addOrUpdateFish(fishData: FishData):Boolean{
        return withContext(Dispatchers.IO){
            try {
                val newFishData = FishStore.addFish(fishData)
                items.clear()
                items.add(newFishData)

                _listUpdated.emit(items)
                true
            }
            catch (e: Exception){
                false
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun addOrUpdateFishs(fishDatas: List<FishData>):Boolean{
        return withContext(Dispatchers.IO){
            try {
                items.clear()
                fishDatas.forEach { fishData ->
                    val newFishData = FishStore.addFish(fishData)
                    items.add(newFishData)
                }
                _listUpdated.emit(items)
                true
            }
            catch (e: Exception){
                false
            }
        }
    }

    suspend fun removeAnimal(fishData: FishData): Boolean {
        return withContext(Dispatchers.IO){
            return@withContext FishStore.removeFish(fishData)
        }
    }
}