package tl209.bai4_fish_v10_ac.model.skills

import tl209.bai4_fish_v10_ac.viewmodel.domainmodels.Fish

class EnhancedSpecialAbilitySkill(
    override val cooldown: Long = 4000L,
    val boostFactor: Float = 1.5f,
    val description: String = "Boost Speed"
) : FishSkill {
    override val name: String = "Special Ability"
    override fun activate(fish: Fish) {
        // Logic kích hoạt hiệu ứng đặc biệt, ví dụ: tăng tốc độ tạm thời
        println("${fish.name} activates its Special Ability: $description")
        // Tăng tốc độ tạm thời
        fish.vx *= boostFactor
        fish.vy *= boostFactor
    }
}