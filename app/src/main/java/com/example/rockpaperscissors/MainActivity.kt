package com.example.rockpaperscissors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var rockButton: Button
    private lateinit var paperButton: Button
    private lateinit var scissorsButton: Button
    private lateinit var newGameButton: Button

    private lateinit var resultText: TextView
    private lateinit var droidMoveText: TextView
    private lateinit var yourMoveText: TextView

    private var isGameOver: Boolean = false

    private var userMove: RockPaperScissorsMove? = null
    private var droidMove: RockPaperScissorsMove? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rockButton = findViewById(R.id.rockButton)
        paperButton = findViewById(R.id.paperButton)
        scissorsButton = findViewById(R.id.scissorsButton)
        newGameButton = findViewById(R.id.newGameButton)

        resultText = findViewById(R.id.winLoseText)
        droidMoveText = findViewById(R.id.droidMoveText)
        yourMoveText = findViewById(R.id.yourMoveText)
    }

    fun rockClicked(view:View) {
        if (!isGameOver) {
            handleMove(RockPaperScissorsMove.rock)
        }
    }
    fun paperClicked(view:View) {
        if (!isGameOver) {
            handleMove(RockPaperScissorsMove.paper)
        }
    }
    fun scissorsClicked(view:View) {
        if (!isGameOver) {
            handleMove(RockPaperScissorsMove.scissors)
        }
    }
    fun newGameClicked(view:View) {
        isGameOver = false
        droidMoveText.text = "Droid Move:"
        yourMoveText.text = "Your Move:"
        resultText.text = ""
    }

    private fun handleMove(move: RockPaperScissorsMove) {
        userMove = move
        droidMove = randomMove()

        yourMoveText.text = "Your Move: ${userMove!!.move}"

        droidMoveText.text = "Droid Move: ${droidMove!!.move}"

        handleWinner()
    }

    private fun randomMove(): RockPaperScissorsMove {
        val randomNumber = Random.nextInt(3)

        val randomMove = when (randomNumber) {
            0 -> RockPaperScissorsMove.rock
            1 -> RockPaperScissorsMove.paper
            2 -> RockPaperScissorsMove.scissors
            else -> RockPaperScissorsMove.rock
        }

        return randomMove
    }

    private fun handleWinner() {
        resultText.text = getResultText()
        isGameOver = true
    }

    private fun getResultText(): String {
        val userMove = userMove!!
        val droidMove = droidMove!!

        val youWonText = "You won!"
        val tieText = "Tie!"
        val droidWonText = "Droid won!"

        if (userMove == droidMove) {
            return tieText
        } else if (
            (userMove == RockPaperScissorsMove.rock && droidMove == RockPaperScissorsMove.scissors) ||
            (userMove == RockPaperScissorsMove.paper && droidMove == RockPaperScissorsMove.rock) ||
            (userMove == RockPaperScissorsMove.scissors && droidMove == RockPaperScissorsMove.paper)
        ) {
            return youWonText
        } else {
            return droidWonText
        }
    }

}

enum class RockPaperScissorsMove(val move: String) {
    rock("Rock"),
    paper("Paper"),
    scissors("Scissors")
}
