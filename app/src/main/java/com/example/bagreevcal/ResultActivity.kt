package com.example.bagreevcal

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bagreevcal.databinding.ActivityResultBinding
import net.objecthunter.exp4j.ExpressionBuilder

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        try {
            val express = ExpressionBuilder(intent.extras?.getString("final"))
            binding.resultTextView.text =
                "Result is: " + express.build().evaluate().toString()
        } catch (e: Exception) {
            binding.resultTextView.text = "Result is: unable to calculate!"
        }

    }
}