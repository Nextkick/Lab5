package com.example.lab5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {
    private lateinit var a4RadioButton: RadioButton
    private lateinit var a3RadioButton: RadioButton
    private lateinit var a1RadioButton: RadioButton
    private lateinit var countPages: EditText
    private lateinit var mCalculateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        a4RadioButton = findViewById(R.id.a4RadioButton)
        a3RadioButton = findViewById(R.id.a3RadioButton)
        a1RadioButton = findViewById(R.id.a1RadioButton)
        countPages = findViewById(R.id.editTextNumberDecimal)
        mCalculateButton = findViewById(R.id.calculateButton)
        mCalculateButton.isEnabled = false

        countPages.addTextChangedListener {
            updateCalculateButtonState()
        }

        mCalculateButton.setOnClickListener {
            val intent = Intent(this, PriceActivity::class.java)
            intent.putExtra("a4RadioButton", a4RadioButton.isChecked)
            intent.putExtra("a3RadioButton", a3RadioButton.isChecked)
            intent.putExtra("a1RadioButton", a1RadioButton.isChecked)
            intent.putExtra("countPages", countPages.text.toString())

            startActivity(intent)
        }
    }

    private fun updateCalculateButtonState() {
        val isAnyRadioButtonChecked = (a4RadioButton.isChecked ||
                a3RadioButton.isChecked || a1RadioButton.isChecked)
        val isCountPagesValid = countPages.text.isNotEmpty()
        mCalculateButton.isEnabled = isAnyRadioButtonChecked && isCountPagesValid
    }
}