package tl209.bai4_fish_v10_ac.view

import tl209.bai4_fish_v10_ac.viewmodel.domainmodels.Fish
import tl209.bai4_fish_v10_ac.viewmodel.domainmodels.Nemo
import tl209.bai4_fish_v10_ac.viewmodel.domainmodels.Salmon
import tl209.bai4_fish_v10_ac.viewmodel.domainmodels.Shark
import tl209.bai4_fish_v10_ac.viewmodel.domainmodels.Shrimp

object FishViewAdapter {
    // Chuyển đổi một đối tượng domain thành đối tượng view (Fish có thể có hàm draw() được ghi đè)
    fun adapt(fish: Fish): Fish {
        return when (fish) {
            is Nemo -> NemoView.fromDomain(fish)
            is Salmon -> SalmonView.fromDomain(fish)
            is Shark -> SharkView.fromDomain(fish)
            is Shrimp -> ShrimpView.fromDomain(fish)
            else -> fish // fallback: trả về đối tượng domain gốc
        }
    }
}