package com.example.fibonacci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var indexText: EditText
    private lateinit var calculateButton: Button
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        indexText = findViewById(R.id.indexField)
        calculateButton = findViewById(R.id.calculateButton)
        resultTextView = findViewById(R.id.resultTextView)

        calculateButton.setOnClickListener {
            Log.d(TAG, "Start Calculation ")

            if (indexText.text.isNotEmpty()) {
                resultTextView.text = calculateFib(indexText.text.toString().toULong()).toString()
            }
            Log.d(TAG, "End Calculation ")
        }
    }

    fun calculateFib (index : ULong) : ULong {
        var minusTwo = 0uL
        var minusOne = 1uL
        var result   = 0uL
        if (index < 2uL ) {
            return index
        }

        for (i in 2L..index.toLong()) {
            result = minusTwo + minusOne
            minusTwo = minusOne
            minusOne = result
        }
        return result
    }

    companion object {
        private const val TAG = "FIBONACCI"
    }

}