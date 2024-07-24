package com.behruzbek0430.polyline_polygone

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.behruzbek0430.polyline_polygone.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {
    private val binding by lazy { ActivityFirstBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(binding.root)

        binding.PolyLine.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.PolyGone.setOnClickListener {
            startActivity(Intent(this, PalygoneActivity::class.java))
        }

    }
}