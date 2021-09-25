package com.example.android.tictactoegame
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_scoreboard.*

class scoreboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoreboard)
        var player=intent?.extras?.getString("WINNER").toString()
        var player1=intent?.extras?.getString("FIRST_NAME").toString()
        var player2=intent?.extras?.getString("SECOND_NAME").toString()
        var player1_win=intent?.extras?.getInt("PLAYER1_WIN")
        var player2_win=intent?.extras?.getInt("PLAYER2_WIN")
        var player1_lost=intent?.extras?.getInt("PLAYER1_LOST")
        var player2_lost=intent?.extras?.getInt("PLAYER2_LOST")
        player_one.text=player1.toString()
        player_two.text=player2.toString()
        if(player=="PLAYER1")
        {
            player_one_won.text= (player1_win).toString()
            player_one_lost.text=(player1_lost).toString()
            player_two_won.text=(player2_win).toString()
            player_two_lost.text= (player1_win).toString()
        }
        else if(player=="PLAYER2")
        {
            player_one_won.text= (player1_win).toString()
            player_one_lost.text=(player1_lost).toString()
            player_two_won.text=(player2_win).toString()
            player_two_lost.text= (player1_win).toString()
        }
        player_one_score.text=(player_one_won.text.toString().toInt()-player_one_lost.text.toString().toInt()).toString()
        player_two_score.text=(player_two_won.text.toString().toInt()-player_two_lost.text.toString().toInt()).toString()

        play_again.setOnClickListener {
            var intent3= Intent(this,MainGame::class.java)
            intent3.putExtra("PLAYER1_WIN",player1_win)
            intent3.putExtra("PLAYER2_WIN",player2_win)
            intent3.putExtra("PLAYER1_LOST",player1_lost)
            intent3.putExtra("PLAYER2_LOST",player2_lost)
            intent3.putExtra("FIRST_NAME",player1)
            intent3.putExtra("SECOND_NAME",player2)
            startActivity(intent3)
        }
        fresh_start.setOnClickListener {
            var intent4=Intent(this,MainActivity::class.java)
            startActivity(intent4)
        }

    }


}