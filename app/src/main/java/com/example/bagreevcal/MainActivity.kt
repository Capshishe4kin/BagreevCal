package com.example.bagreevcal

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.bagreevcal.Values.floatMutableList
import com.example.bagreevcal.Values.stringMutableList
import com.example.bagreevcal.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val arrayButtons by lazy {
            arrayOf(
                binding.plusButton,
                binding.minusButton,
                binding.multiplyButton,
                binding.divideButton
            )
        }
        for (i in arrayButtons.indices)
            arrayButtons[i].setOnClickListener {
                val check = binding.numberEditText.text.toString().isNotEmpty()
                if (check) {
                    stringMutableList.apply {
                        val operator = (it as Button).text.toString()
                        val number = binding.numberEditText.text.toString().toFloat()
                        add(operator)
                        floatMutableList.add(number)
                        binding.numbersTextView.text =
                            "${binding.numbersTextView.text} $number $operator"
                        binding.numberEditText.setText(
                            binding.numberEditText.text.toString().substring(0, 0)
                        )
                    }
                    return@setOnClickListener
                }
                Snackbar.make(it, "Please write a number", Snackbar.LENGTH_SHORT).show()
            }

        binding.customClearBtn.setOnClickListener {
            stringMutableList = mutableListOf<String>()
            floatMutableList = mutableListOf<Float>()
            binding.numbersTextView.text = binding.numbersTextView.text.toString().substring(0, 0)
        }
        binding.resultButton.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            if (binding.numberEditText.text.toString().isNotEmpty()) {
                var result: String? = ""
                val getLastNumberAsFloat = binding.numberEditText.text.toString().toFloat()
                for (i in floatMutableList.indices) result += "${floatMutableList[i]}${stringMutableList[i]}"
                result += "$getLastNumberAsFloat"
                Log.d("Final", result.toString())
                intent.putExtra("final", result)
                startActivity(intent)
                return@setOnClickListener
            }
            Snackbar.make(it, "Please enter the last number!", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        stringMutableList = mutableListOf<String>()
        floatMutableList = mutableListOf<Float>()
    }
}