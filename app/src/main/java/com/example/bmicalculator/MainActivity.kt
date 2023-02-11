package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val calcButton = findViewById<Button>(R.id.btnCalculate)
        var result = findViewById<CardView>(R.id.cvResult)

        result.visibility = INVISIBLE
        calcButton.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            //result.visibility = VISIBLE
            if (validateInput(weight, height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                val bmi2Digits = String.format("%2f", bmi).toFloat()
                result.visibility = VISIBLE
                displayResult(bmi2Digits)
            }
        }
    }
    private fun validateInput(weight:String?,height:String?):Boolean{
        return when {
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight is empty", Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Height is empty", Toast.LENGTH_LONG).show()
                return false
            }

            else -> {
                return true
            }
        }
    }

    private fun displayResult(bmi: Float) {
        val index = findViewById<TextView>(R.id.tvIndex)
        val messageIndex = findViewById<TextView>(R.id.tvResult)
        val info = findViewById<TextView>(R.id.tvInfoM)
        index.text = bmi.toString()
        info.text = "Normal Range is 18.5 - 24.99"

        var resultText = ""
        var color = 0
        when {
            bmi < 18.50 -> {
                resultText = "Underweight"
                color = R.color.underWeight
            }
            bmi in 25.00..29.99 -> {
                resultText = "OverWeight"
                color = R.color.OverWeight
            }
            bmi in 18.50..24.99 -> {
                resultText = "Normal"
                color = R.color.Normal
            }
            bmi > 29.99 -> {
                resultText = "Obese"
                color = R.color.obease

            }
        }
        messageIndex.setTextColor(ContextCompat.getColor(this,color))
        messageIndex.text = resultText
    }
}