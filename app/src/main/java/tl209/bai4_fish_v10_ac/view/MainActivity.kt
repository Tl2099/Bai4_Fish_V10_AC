package tl209.bai4_fish_v10_ac.view

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import tl209.bai4_fish_v10_ac.R
import tl209.bai4_fish_v10_ac.viewmodel.FishTankVM

class MainActivity : AppCompatActivity() {
    private val viewModel: FishTankVM by viewModels()
    private lateinit var fishTankView: FishTankView

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val screenHeight = displayMetrics.heightPixels

        fishTankView = findViewById(R.id.fishTankView)
        fishTankView.setViewModel(viewModel, this)

        val addFishButton = findViewById<android.widget.Button>(R.id.addFishButton)

        addFishButton.setOnClickListener {
            Log.i("Thread3","Đã click")
            viewModel.addRandomFish(screenWidth, screenHeight)
        }


        lifecycleScope.launch {
            viewModel.startTankUpdates(screenWidth, screenHeight)
            viewModel.loadFishData()
        }
    }
}