package tl209.bai4_fish_v10_ac.model.skills

import tl209.bai4_fish_v10_ac.viewmodel.domainmodels.Fish

interface FishSkill {
    val name: String
    val cooldown: Long  // (mili giây)
    fun activate(fish: Fish)
}