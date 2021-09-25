package com.example.android.tictactoegame

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        start_game.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val inputFirst=first_player_name?.text.toString().trim()
        val inputSecond=second_player_name?.text.toString().trim()
        if (inputFirst.isNullOrEmpty())
        {
            var  toast = Toast.makeText(this,"Enter First Player Name",Toast.LENGTH_SHORT)
            toast.show()
        }
        else if(inputSecond.isNullOrEmpty())
        {
            var toast= Toast.makeText(this,"Enter Second Player Name",Toast.LENGTH_SHORT)
            toast.show()
        }
        else {
            val intent = Intent(this, MainGame::class.java)
            intent.putExtra("FIRST_NAME", first_player_name.text.toString())
            intent.putExtra("SECOND_NAME", second_player_name.text.toString())
            startActivity(intent)
        }
    }
}