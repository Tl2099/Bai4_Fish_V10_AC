package tl209.bai4_fish_v10_ac.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tl209.bai4_fish_v10_ac.viewmodel.FishTankVM
import tl209.bai4_fish_v10_ac.viewmodel.domainmodels.Fish
import tl209.bai4_fish_v10_ac.viewmodel.domainmodels.Nemo
import tl209.bai4_fish_v10_ac.viewmodel.domainmodels.Salmon
import tl209.bai4_fish_v10_ac.viewmodel.domainmodels.Shark
import tl209.bai4_fish_v10_ac.viewmodel.domainmodels.Shrimp

class FishTankView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {
    //var size: Float = 50f
    private var fishList: List<Fish> = emptyList()
    private val paint = Paint()

    fun setViewModel(viewModel: FishTankVM, lifecycleOwner: LifecycleOwner) {
        lifecycleOwner.lifecycleScope.launch {
            viewModel.fishList.collect { list ->
                fishList = list
                invalidate() // Yêu cầu vẽ lại View khi danh sách thay đổi
                delay(30L)
            }
        }
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // Lấy thông số màn hình từ resources
        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val screenHeight = displayMetrics.heightPixels

        // Tùy chỉnh kích thước theo thông số màn hình và yêu cầu từ MeasureSpec
        val measuredWidth = resolveSize(screenWidth, widthMeasureSpec)
        val measuredHeight = resolveSize(screenHeight, heightMeasureSpec)

        setMeasuredDimension(measuredWidth, measuredHeight)
    }


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Vẽ nền bể cá
        canvas.drawColor(0xFFCCE5FF.toInt())

        fishList.forEach { fish ->
            // Nếu cần chuyển đổi, bạn có thể sử dụng adapter:
             //val fishView = FishViewAdapter.adapt(fish)
             //fishView.draw(canvas, paint)
            // Nếu lớp domain đã có hàm draw() được override (như trong NemoView, SharkView, …)

            when (fish) {
                is Nemo -> {
                    // Vì fish đã là NemoView (vì NemoView kế thừa Nemo), ta chỉ cần gọi draw
                    fish.draw(canvas, paint)
                    Log.i("FishTankView", "Đã vẽ Nemo với currentAngle = ${fish.currentAngle}")
                }
                is Shark->{
                    fish.draw(canvas, paint)
                }
                is Salmon->{
                    fish.draw(canvas, paint)
                }
                is Shrimp->{
                    fish.draw(canvas, paint)
                }
                else -> {
                    Log.e("FishTankView", "Unknown fish type: $fish")
                }
            }
//            when (fish) {
//                is Nemo -> {
//                    // Vì fish đã là NemoView (vì NemoView kế thừa Nemo), ta chỉ cần gọi draw
//                    fish.draw(canvas, paint)
//                    Log.i("FishTankView", "Đã vẽ Nemo với currentAngle = ${fish.currentAngle}")
//                }
//                is Shark->{
//                    fish.draw(canvas, paint)
//                }
//                is Salmon->{
//                    fish.draw(canvas, paint)
//                }
//                is Shrimp->{
//                    fish.draw(canvas, paint)
//                }
//                else -> {
//                    Log.e("FishTankView", "Unknown fish type: $fish")
//                }
//            }
        }
        postInvalidateOnAnimation() // Yeu cau ve lai man hình voi toc do man hinh

    }
}