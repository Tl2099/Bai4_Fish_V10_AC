package tl209.bai4_fish_v10_ac.model.skills

import tl209.bai4_fish_v10_ac.viewmodel.domainmodels.Fish

class BiteAttackSkill(
    override val cooldown: Long = 3000L
) : FishSkill {
    override val name: String = "Bite Attack"
    override fun activate(fish: Fish) {
        // Logic tấn công, ví dụ: giảm sức khỏe của cá mục tiêu
        println("${fish.name} performs a Bite Attack!")
    }
}