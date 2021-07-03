package com.zuhriyansauqi.frisianflag

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zuhriyansauqi.frisianflag.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}