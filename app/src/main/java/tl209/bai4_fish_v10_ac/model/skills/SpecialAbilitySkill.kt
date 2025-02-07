package tl209.bai4_fish_v10_ac.model.skills

import tl209.bai4_fish_v10_ac.viewmodel.dtmodels.Fish

class SpecialAbilitySkill(
    override val cooldown: Long = 4000L,
    val description: String = "Boost Speed"
) : FishSkill {
    override val name: String = "Special Ability"
    override fun activate(fish: Fish) {
        // Logic kích hoạt hiệu ứng đặc biệt, ví dụ: tăng tốc độ tạm thời
        println("${fish.name} activates its Special Ability: $description")
    }
}