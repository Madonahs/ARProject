package com.madonasyombua.arproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.madonasyombua.arproject.databinding.ActivityMainBinding
import com.madonasyombua.arproject.ui.AnimalShapeActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startArDemo()
    }

    private fun startArDemo(){
        binding.arDemo.setOnClickListener { startActivity(AnimalShapeActivity.newIntent(this)) }
    }

}