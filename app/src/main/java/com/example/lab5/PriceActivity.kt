package com.example.lab5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PriceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_price)

        val isCheckedA4 = intent.getBooleanExtra("a4RadioButton", false)
        val isCheckedA3 = intent.getBooleanExtra("a3RadioButton", false)
        val isCheckedA1 = intent.getBooleanExtra("a1RadioButton", false)
        val countValueString = intent.getStringExtra("countPages")


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mBackButton: Button = findViewById(R.id.backButton)
        val outputLabel: TextView = findViewById(R.id.orderAmount)
        val countValue = countValueString?.toIntOrNull() ?: 0
        var result = 0

        when {
            isCheckedA4 -> { result = countValue * 10 }
            isCheckedA3 -> { result = countValue * 30 }
            isCheckedA1 -> { result = countValue * 100 }

        }
        outputLabel.text = getString(R.string.result,
            getString(R.string.order_amount), result, getString(R.string.rubles))

        mBackButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
