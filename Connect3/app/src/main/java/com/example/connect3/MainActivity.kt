package com.example.connect3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.forEach
import androidx.gridlayout.widget.GridLayout

class MainActivity : AppCompatActivity() {

    // 0: yellow, 1: red, 2: empty
    private var gameState = mutableListOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
    private var winningPositions = mutableListOf(listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8), listOf(0, 4, 8), listOf(2, 4, 6), listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8))
    private var activePlayer: Int = 0
    private var gameActive: Boolean = true

    fun dropIn(v: View){
        val text: TextView = findViewById(R.id.textInfo)
        val button: Button = findViewById(R.id.button)
        val counter: ImageView = v as ImageView
        val tappedCounter: Int = counter.tag.toString().toInt()

        if (gameState[tappedCounter] == 2 && gameActive){
            gameState[tappedCounter] = activePlayer
            counter.translationY = -1500f
            activePlayer = if (activePlayer == 0){
                counter.setImageResource(R.drawable.yellow)
                1
            } else {
                counter.setImageResource(R.drawable.red)
                0
            }
            counter.animate().translationYBy(1500f).duration = 300
            for (winningPosition in winningPositions){
                if(gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    gameActive = false
                    if (activePlayer == 1){
                        text.setText(R.string.yellow_win)
                    }else{
                        text.setText(R.string.red_win)
                    }
                    text.visibility = View.VISIBLE
                    button.visibility = View.VISIBLE
                }
            }
        }
    }
    fun reset(v: View){
        val text: TextView = findViewById(R.id.textInfo)
        val button: Button = findViewById(R.id.button)
        val grid: GridLayout = findViewById(R.id.gridLayout)

        button.visibility = View.INVISIBLE
        text.visibility = View.INVISIBLE
        grid.forEach {
            val counter: ImageView = it as ImageView
            counter.setImageResource(0)
        }
        gameState = mutableListOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
        activePlayer = 0
        gameActive = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}