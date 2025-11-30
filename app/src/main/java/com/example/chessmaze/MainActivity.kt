package com.example.chessmaze

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Instead of R.layout.activity_main, we load our GameView!
        val game = GameView(this)
        setContentView(game)
    }
}