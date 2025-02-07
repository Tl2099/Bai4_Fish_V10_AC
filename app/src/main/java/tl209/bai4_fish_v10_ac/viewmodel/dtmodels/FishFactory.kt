package tl209.bai4_fish_v10_ac.viewmodel.dtmodels

import kotlin.random.Random

object FishFactory {
    fun createRandomFish(screenWidth: Int, screenHeight: Int): Fish {
        return when (Random.nextInt(5)) {
            0 -> Shark(
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
            1 -> Salmon(
                name = "Salmon",
                posX = Random.nextFloat() * screenWidth,
                posY = Random.nextFloat() * screenHeight,
                size = 40f,
                vx = Random.nextFloat() * 10 - 5,
                vy = Random.nextFloat() * 10 - 5,
                mass = 80f,
                score = 80,
                migrationPattern = "North"
            )
            2 -> Nemo(
                name = "Chub",
                posX = Random.nextFloat() * screenWidth,
                posY = Random.nextFloat() * screenHeight,
                size = 40f, // Cùng kích thước với Salmon
                vx = Random.nextFloat() * 15 - 7.5f, // Di chuyển nhanh hơn
                vy = Random.nextFloat() * 15 - 7.5f,
                mass = 80f,
                score = 70,
                specialAbilityDescription = "Quick Recovery"
            )
            3 -> Shrimp(
                posX = Random.nextFloat() * screenWidth,
                posY = Random.nextFloat() * screenHeight,
                vx = Random.nextFloat() * 10 - 5,
                vy = Random.nextFloat() * 10 - 5
            )
            else -> Salmon(
                name = "Salmon",
                posX = Random.nextFloat() * screenWidth,
                posY = Random.nextFloat() * screenHeight,
                size = 40f,
                vx = Random.nextFloat() * 10 - 5,
                vy = Random.nextFloat() * 10 - 5,
                mass = 80f,
                score = 80,
                migrationPattern = "North"
            )
        }
    }
}