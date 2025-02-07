package tl209.bai4_fish_v10_ac.model.store

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import kotlinx.coroutines.delay
import tl209.bai4_fish_v10_ac.model.entities.FishData
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object FishStore {
    private val fishDataList = mutableListOf<FishData>()
    private fun getCurrentTimestamp(): Long {
        return System.currentTimeMillis()
    }

    suspend fun getAllFish(): List<FishData> {
        delay(1000)
        return fishDataList.toList()
    }

    suspend fun getFish(index: Int): FishData {
        delay(1000)
        return fishDataList[index].copy()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun addFish(fishData: FishData):FishData {
        delay(1000)
        val newId = getCurrentTimestamp()
        fishData.copy(id=newId, lastUpdated = getCurrentDateTime()).also {
            fishDataList.add(it)
            Log.d("AnimalsStore","add success $fishData")
            Log.d("AnimalsStore","total: \n $fishDataList")
            return it.copy()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getCurrentDateTime(): String{
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return currentDateTime.format(formatter)
    }

    suspend fun removeFish(fishData:FishData):Boolean {
        delay(1000)
        try {
            val position = fishDataList.indexOfFirst { it.id == fishData.id }
            fishDataList.removeAt(position)

            return true
        }
        catch (e:Exception){
            return false
        }
    }
}