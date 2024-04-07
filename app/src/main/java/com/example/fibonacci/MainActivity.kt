package com.example.fibonacci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var indexText: EditText
    private lateinit var calculateButton: Button
    private lateinit var resultTextView: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        indexText = findViewById(R.id.indexField)
        calculateButton = findViewById(R.id.calculateButton)
        resultTextView = findViewById(R.id.resultTextView)
        progressBar = findViewById(R.id.progressBar)

        calculateButton.setOnClickListener {
            Log.d(TAG, "Start Calculation ")

            calculateButton.text = ""
            calculateButton.isEnabled = false
            progressBar.visibility = ProgressBar.VISIBLE

            // Launching a coroutine
            CoroutineScope(Dispatchers.Main).launch {
                if (indexText.text.isNotEmpty()) {
                    val result = withContext(Dispatchers.Default) {
                        async { calculateFib(indexText.text.toString().toULong()) }
                    }.await()

                    resultTextView.text = result.toString()
                }
                calculateButton.text = "Calculate"
                progressBar.visibility = ProgressBar.INVISIBLE
                calculateButton.isEnabled = true
                Log.d(TAG, "End Calculation ")
            }

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