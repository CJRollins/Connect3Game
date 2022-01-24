package com.example.connect3

import android.view.View

class Game {
    var playerTurn: Boolean = true
    var gameWon: Boolean = false

    fun updateView(view: View){
        if(!gameWon && view.background == null){
            if(playerTurn){
                view.setBackgroundResource(R.drawable.red)
                view.tag = R.string.red
            }else{
                view.setBackgroundResource(R.drawable.yellow)
                view.tag = R.string.yellow
            }
        }
    }

    fun reset(){
        gameWon = false
        playerTurn = true
    }
}