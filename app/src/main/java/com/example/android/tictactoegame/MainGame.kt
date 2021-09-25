package com.example.android.tictactoegame

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main_game.*

class MainGame : AppCompatActivity() ,View.OnClickListener{
    var PLAYER:Boolean=true
    var TURN_COUNT=0

    var boardStatus=Array(3){IntArray(3)}
    lateinit var board :Array<Array<Button>>

    lateinit var PLAYER1 :String
    lateinit var PLAYER2 :String
    lateinit var player:String
    var player1_win=0
    var player2_win=0
    var player1_lost=0
    var player2_lost=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_game)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        board = arrayOf(arrayOf(button1,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9))
        next.isEnabled=false
        PLAYER1=intent?.extras?.getString("FIRST_NAME").toString()
        PLAYER2=intent?.extras?.getString("SECOND_NAME").toString()
        player1_win= intent?.extras?.getInt("PLAYER1_WIN")!!
        player2_win=intent?.extras?.getInt("PLAYER2_WIN")!!
        player1_lost= intent?.extras?.getInt("PLAYER1_LOST")!!
        player2_lost=intent?.extras?.getInt("PLAYER2_LOST")!!
        initializeBoardStatus()
        for(i in board)
        {
            for( button in i)
            {
                button.setOnClickListener(this)
            }
        }

        next.setOnClickListener {
            PLAYER=true
            TURN_COUNT=0
            initializeBoardStatus()
            val intent2 =Intent(this, scoreboard::class.java)
            intent2.putExtra("WINNER",player)
            intent2.putExtra("FIRST_NAME",PLAYER1)
            intent2.putExtra("SECOND_NAME",PLAYER2)
            intent2.putExtra("PLAYER1_WIN",player1_win)
            intent2.putExtra("PLAYER2_WIN",player2_win)
            intent2.putExtra("PLAYER1_LOST",player1_lost)
            intent2.putExtra("PLAYER2_LOST",player2_lost)
            startActivity(intent2)

        }

    }
    private fun initializeBoardStatus() {
        for ( i in 0..2)
        {
            for (j in 0..2)
            {
                boardStatus[i][j]=-1
                board[i][j].isEnabled=true
                board[i][j].text=""
            }
        }
        updateDisplay("New Game! $PLAYER1's turn")
    }

    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.button1->{
                updateValue(row=0,col=0,player=PLAYER)
            }
            R.id.button2->{
                updateValue(row=0,col=1,player=PLAYER)
            }
            R.id.button3->{
                updateValue(row=0,col=2,player=PLAYER)
            }
            R.id.button4->{
                updateValue(row=1,col=0,player=PLAYER)
            }
            R.id.button5->{
                updateValue(row=1,col=1,player=PLAYER)
            }
            R.id.button6->{
                updateValue(row=1,col=2,player=PLAYER)
            }
            R.id.button7->{
                updateValue(row=2,col=0,player=PLAYER)
            }
            R.id.button8->{
                updateValue(row=2,col=1,player=PLAYER)
            }
            R.id.button9->{
                updateValue(row=2,col=2,player=PLAYER)
            }
        }
        TURN_COUNT++
        PLAYER = !PLAYER
        if(PLAYER) {
            updateDisplay("$PLAYER1's Turn")
        }
        else
        {
            updateDisplay("$PLAYER2's Turn")
        }
        if(TURN_COUNT==9)
        {
            updateDisplay("Game Draw !")
            player="Draw"
            next_Enable()
        }
        checkWinner()
    }

    private fun checkWinner() {
        //Horizontal rows
        for(i in 0..2)
        {
            if(boardStatus[i][0]==boardStatus[i][1]&& boardStatus[i][1]==boardStatus[i][2])
            {
                if(boardStatus[i][0]==1)
                {
                    updateDisplay("$PLAYER1 is winner")
                    player="PLAYER1"
                    disableall()
                    next_Enable()
                    player1_win++
                    player2_lost++
                    break
                }
                else if (boardStatus[i][0]==0)
                {
                    updateDisplay("$PLAYER2 is winner")
                    player="PLAYER2"
                    disableall()
                    next_Enable()
                    player2_win++
                    player1_lost++
                    break
                }
            }
        }
        // For Vertical column
        for(i in 0..2)
        {
            if(boardStatus[0][i]==boardStatus[1][i]&& boardStatus[0][i]==boardStatus[2][i])
            {
                if(boardStatus[0][i]==1)
                {
                    updateDisplay("$PLAYER1 is winner")
                    player="PLAYER1"
                    disableall()
                    next_Enable()
                    player1_win++
                    player2_lost++
                    break
                }
                else if (boardStatus[0][i]==0)
                {
                    updateDisplay("$PLAYER2 is winner")
                    player="PLAYER2"
                    disableall()
                    next_Enable()
                    player2_win++
                    player1_lost++
                    break
                }
            }
        }
        //Diagonal Check
        if ((boardStatus[1][1]==boardStatus[2][2]&&boardStatus[1][1]==boardStatus[0][0]) ||
            (boardStatus[1][1]==boardStatus[2][0]&&boardStatus[1][1]==boardStatus[0][2]))
        {
            if(boardStatus[1][1]==1)
            {
                updateDisplay("$PLAYER1 is winner")
                player="PLAYER1"
                disableall()
                next_Enable()
                player1_win++
                player2_lost++
            }
            else if (boardStatus[1][1]==0)
            {
                updateDisplay("$PLAYER2 is winner")
                player="PLAYER2"
                disableall()
                next_Enable()
                player2_win++
                player1_lost++
            }
        }

    }

    private fun disableall() {
        for(i in board)
        {
            for( button in i)
            {
                button.isEnabled=false
            }
        }
    }

    private fun updateDisplay(text: String) {
        displayPlayer.text=text.uppercase()
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text: String =if (player) "X" else "O"
        val value :Int =if(player) 1 else 0
        board[row][col].apply{
            isEnabled=false
            setText(text)
        }
        boardStatus[row][col]=value
    }
    private fun next_Enable()
    {
        next.isEnabled=true
    }
}


