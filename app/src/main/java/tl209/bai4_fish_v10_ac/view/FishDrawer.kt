package tl209.bai4_fish_v10_ac.view

import android.graphics.Canvas
import android.graphics.Paint
import tl209.bai4_fish_v10_ac.viewmodel.domainmodels.Fish

interface FishDrawer<T: Fish> {
    fun draw(canvas: Canvas, paint: Paint, fish: T)
}