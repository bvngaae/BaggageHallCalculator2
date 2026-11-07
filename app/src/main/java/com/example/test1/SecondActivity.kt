package com.example.test1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // 1. Button
        val btnBackIcon = findViewById<TextView>(R.id.btnBackIcon)
        btnBackIcon.setOnClickListener {
            finish()
        }

        // 2. github link
        val tvGitHub = findViewById<TextView>(R.id.tvGitHub)
        tvGitHub.setTextColor(android.graphics.Color.parseColor("#FACC15"))

        tvGitHub.setOnClickListener {
            val url = "https://github.com/bvngaae"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }
}