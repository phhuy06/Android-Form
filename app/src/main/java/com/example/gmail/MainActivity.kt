package com.example.gmail

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var inputNumber: EditText
    private lateinit var evenButton: RadioButton
    private lateinit var oddButton: RadioButton
    private lateinit var squareButton: RadioButton
    private lateinit var showButton: Button
    private lateinit var errorText: TextView
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputNumber = findViewById(R.id.InputNumber)
        evenButton = findViewById(R.id.EvenButton)
        oddButton = findViewById(R.id.OddButton)
        squareButton = findViewById(R.id.SquareButton)
        showButton = findViewById(R.id.ShowButton)
        errorText = findViewById(R.id.ErrorText)
        listView = findViewById(R.id.ListView)

        showButton.setOnClickListener {
            val input = inputNumber.text.toString()
            if (input.isEmpty()) {
                errorText.text = "Please enter a positive number."
                return@setOnClickListener
            }

            val n: Int = try {
                input.toInt().also {
                    if (it < 0) {
                        errorText.text = "Please enter a positive number."
                        return@setOnClickListener
                    }
                }
            } catch (e: NumberFormatException) {
                errorText.text = "Invalid input. Please enter a valid number."
                return@setOnClickListener
            }

            errorText.text = ""
            val results = ArrayList<String>()

            when {
                evenButton.isChecked -> {
                    for (i in 0..n step 2) {
                        results.add(i.toString())
                    }
                }
                oddButton.isChecked -> {
                    for (i in 1..n step 2) {
                        results.add(i.toString())
                    }
                }
                squareButton.isChecked -> {
                    var i = 0
                    while (i * i <= n) {
                        results.add((i * i).toString())
                        i++
                    }
                }
                else -> {
                    errorText.text = "Please select a number type."
                    return@setOnClickListener
                }
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, results)
            listView.adapter = adapter
        }
    }
}
