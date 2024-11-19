package com.example.lab5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {
    private lateinit var a4CheckBox: CheckBox
    private lateinit var a3CheckBox: CheckBox
    private lateinit var a1CheckBox: CheckBox
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

        a4CheckBox = findViewById(R.id.a4CheckBox)
        a3CheckBox = findViewById(R.id.a3CheckBox)
        a1CheckBox = findViewById(R.id.a1CheckBox)
        countPages = findViewById(R.id.editTextNumberDecimal)
        mCalculateButton = findViewById(R.id.calculateButton)
        mCalculateButton.isEnabled = false

        a4CheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                a3CheckBox.isChecked = false
                a1CheckBox.isChecked = false
            }
            updateCalculateButtonState()
        }
        a3CheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                a4CheckBox.isChecked = false
                a1CheckBox.isChecked = false
            }
            updateCalculateButtonState()
        }
        a1CheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                a4CheckBox.isChecked = false
                a3CheckBox.isChecked = false
            }
            updateCalculateButtonState()
        }
        countPages.addTextChangedListener {
            updateCalculateButtonState()
        }

        mCalculateButton.setOnClickListener {
            val intent = Intent(this, PriceActivity::class.java)
            intent.putExtra("a4CheckBox", a4CheckBox.isChecked)
            intent.putExtra("a3CheckBox", a3CheckBox.isChecked)
            intent.putExtra("a1CheckBox", a1CheckBox.isChecked)
            intent.putExtra("countPages", countPages.text.toString())

            startActivity(intent)
        }
    }

    private fun updateCalculateButtonState() {
        val isAnyCheckboxChecked = (a4CheckBox.isChecked ||
                a3CheckBox.isChecked || a1CheckBox.isChecked)
        val isCountPagesValid = countPages.text.isNotEmpty()
        mCalculateButton.isEnabled = isAnyCheckboxChecked && isCountPagesValid
    }
}