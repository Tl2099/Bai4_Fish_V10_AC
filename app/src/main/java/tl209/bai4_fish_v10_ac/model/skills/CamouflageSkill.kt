package tl209.bai4_fish_v10_ac.model.skills

import tl209.bai4_fish_v10_ac.viewmodel.dtmodels.Fish

class CamouflageSkill(
    override val cooldown: Long = 5000L
) : FishSkill {
    override val name: String = "Camouflage"
    override fun activate(fish: Fish) {
        // Logic ẩn mình, ví dụ: thay đổi trạng thái hiển thị của cá
        println("${fish.name} activates Camouflage!")
    }
}