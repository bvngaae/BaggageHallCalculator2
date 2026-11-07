package com.example.test1

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Tunggu selama 2 saat (2000ms), kemudian beralih ke skrin kalkulator utama (MainActivity)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Tutup SplashActivity supaya pengguna tidak boleh kembali ke skrin logo apabila menekan butang back
        }, 2000)
    }
}