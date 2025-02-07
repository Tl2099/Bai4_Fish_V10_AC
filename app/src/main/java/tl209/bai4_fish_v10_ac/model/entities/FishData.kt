package tl209.bai4_fish_v10_ac.model.entities

data class FishData(
    val id: Long? = null,     // Định danh duy nhất của cá
    val name: String,         // Tên cá
    val posX: Float,          // Tọa độ X
    val posY: Float,          // Tọa độ Y
    val size: Float,          // Kích thước (ví dụ: bán kính của cá)
    val mass: Float,          // Trọng lượng
    val speed: Float,         // Tốc độ di chuyển
    val direction: Float,     // Góc hướng di chuyển (độ)
    val scoreValue: Int,       // Điểm số khi cá ăn hoặc bị ăn
    val type: String,
    val lastUpdated:String = ""
    // Có thể bổ sung các thuộc tính khác nếu cần
)