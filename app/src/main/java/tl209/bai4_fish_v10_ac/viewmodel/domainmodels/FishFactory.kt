package tl209.bai4_fish_v10_ac.viewmodel.domainmodels

import kotlinx.coroutines.CoroutineScope
import tl209.bai4_fish_v10_ac.view.NemoView
import tl209.bai4_fish_v10_ac.view.SalmonView
import tl209.bai4_fish_v10_ac.view.SharkView
import tl209.bai4_fish_v10_ac.view.ShrimpView
import kotlin.random.Random


object FishFactory {

//    fun createRandomFish(screenWidth: Int, screenHeight: Int): Fish {
//        return when (Random.nextInt(5)) {
//            0 -> Shark(
//                name = "Shark",
//                posX = Random.nextFloat() * screenWidth,
//                posY = Random.nextFloat() * screenHeight,
//                size = 70f,
//                vx = Random.nextFloat() * 5 - 2.5f,
//                vy = Random.nextFloat() * 5 - 2.5f,
//                mass = 200f,
//                score = 100,
//                type = "Shark",
//                biteForce = 1.5f
//            )
//            1 -> Salmon(
//                name = "Salmon",
//                posX = Random.nextFloat() * screenWidth,
//                posY = Random.nextFloat() * screenHeight,
//                size = 50f,
//                vx = Random.nextFloat() * 10 - 5,
//                vy = Random.nextFloat() * 10 - 5,
//                mass = 80f,
//                score = 100,
//                type = "Salmon",
//                migrationPattern = "North"
//            )
//            2 -> Nemo(
//                name = "Nemo",
//                posX = Random.nextFloat() * screenWidth,
//                posY = Random.nextFloat() * screenHeight,
//                size = 50f,
//                vx = Random.nextFloat() * 15 - 7.5f,
//                vy = Random.nextFloat() * 15 - 7.5f,
//                mass = 80f,
//                score = 100,
//                type = "Nemo",
//                specialAbilityDescription = "Quick Recovery"
//            )
//            3 -> Shrimp(
//                name = "Shrimp",
//                posX = Random.nextFloat() * screenWidth,
//                posY = Random.nextFloat() * screenHeight,
//                size = 10f,
//                vx = Random.nextFloat() * 10 - 5,
//                vy = Random.nextFloat() * 10 - 5,
//                mass = 5f,
//                score = 5,
//                type = "Shrimp"
//            )
//            else -> Salmon(
//                name = "Salmon",
//                posX = Random.nextFloat() * screenWidth,
//                posY = Random.nextFloat() * screenHeight,
//                size = 50f,
//                vx = Random.nextFloat() * 10 - 5,
//                vy = Random.nextFloat() * 10 - 5,
//                mass = 80f,
//                score = 100,
//                type = "Salmon",
//                migrationPattern = "North"
//            )
//        }
//    }
//
//    fun createRandomShrimpList(screenWidth: Int, screenHeight: Int, count: Int = 10): List<Shrimp> {
//        val shrimpList = mutableListOf<Shrimp>()
//        repeat(count) { i ->
//            val randomX = Random.nextInt(screenWidth).toFloat()
//            val randomY = Random.nextInt(screenHeight).toFloat()
//            val shrimp = Shrimp(
//                id = System.currentTimeMillis(),
//                name = "Shrimp_$i",
//                posX = randomX,
//                posY = randomY,
//                size = 10f,
//                vx = Random.nextFloat() * 10 - 5,  // Giá trị vận tốc lớn hơn
//                vy = Random.nextFloat() * 10 - 5,
//                mass = 5f,
//                score = 5,
//                type = "Shrimp"
//            )
//            shrimpList.add(shrimp)
//        }
//        return shrimpList
//    }
    fun createRandomFish(screenWidth: Int, screenHeight: Int): Fish {
        return when (Random.nextInt(5)) {
            0 -> SharkView(
                name = "Shark",
                posX = Random.nextFloat() * screenWidth,
                posY = Random.nextFloat() * screenHeight,
                size = 70f, // Shark lớn hơn
                vx = Random.nextFloat() * 5 - 2.5f, // Di chuyển chậm hơn
                vy = Random.nextFloat() * 5 - 2.5f,
                mass = 200f,
                score = 100,
                biteForce = 1.5f
            )
            1 -> SalmonView(
                name = "Salmon",
                posX = Random.nextFloat() * screenWidth,
                posY = Random.nextFloat() * screenHeight,
                size = 50f,
                vx = Random.nextFloat() * 10 - 5,
                vy = Random.nextFloat() * 10 - 5,
                mass = 80f,
                score = 100,
                migrationPattern = "North"
            )
            2 -> NemoView(
                name = "Nemo",
                posX = Random.nextFloat() * screenWidth,
                posY = Random.nextFloat() * screenHeight,
                size = 50f,
                vx = Random.nextFloat() * 15 - 7.5f,
                vy = Random.nextFloat() * 15 - 7.5f,
                mass = 80f,
                score = 100,
                type = "Nemo",
                specialAbilityDescription = "Quick Recovery"
            )
            3 -> ShrimpView(
                posX = Random.nextFloat() * screenWidth,
                posY = Random.nextFloat() * screenHeight,
                vx = Random.nextFloat() * 10 - 5,
                vy = Random.nextFloat() * 10 - 5
            )
            // Các trường hợp khác (Shark, Salmon, Shrimp) trả về instance tương ứng
            else -> {
                // Ví dụ, trả về một Salmon (hoặc Nemo như fallback)
                NemoView(
                    name = "Nemo",
                    posX = Random.nextFloat() * screenWidth,
                    posY = Random.nextFloat() * screenHeight,
                    size = 40f,
                    vx = Random.nextFloat() * 15 - 7.5f,
                    vy = Random.nextFloat() * 15 - 7.5f,
                    mass = 80f,
                    score = 100,
                    type = "Nemo",
                    specialAbilityDescription = "Quick Recovery"
                )
            }
        }
    }

    fun createRandomShrimpList(screenWidth: Int, screenHeight: Int, count: Int = 10, scope: CoroutineScope): List<Shrimp> {
        val shrimpList = mutableListOf<Shrimp>()
        repeat(count) { i ->
            val randomX = Random.nextInt(screenWidth).toFloat()
            val randomY = Random.nextInt(screenHeight).toFloat()
            // Tạo một con Shrimp với các giá trị mặc định và vận tốc ngẫu nhiên
            val shrimp = ShrimpView(
                id = System.currentTimeMillis(),  // Hoặc bạn có thể để mặc định nếu class Shrimp đã có giá trị mặc định
                name = "Shrimp_$i",
                posX = randomX,
                posY = randomY,
                size = 10f,           // Kích thước (bán kính) của Shrimp
                vx = Random.nextFloat() * 2 - 1, // Vận tốc theo X ngẫu nhiên từ -1 đến 1
                vy = Random.nextFloat() * 2 - 1, // Vận tốc theo Y ngẫu nhiên từ -1 đến 1
                mass = 5f,
                score = 5,
                type = "Shrimp"
            )
            shrimp.startMoving(scope, screenWidth, screenHeight)
            shrimpList.add(shrimp)
        }
        return shrimpList
    }
}