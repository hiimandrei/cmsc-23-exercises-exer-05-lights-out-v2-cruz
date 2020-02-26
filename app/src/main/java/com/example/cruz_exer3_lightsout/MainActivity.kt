package com.example.cruz_exer3_lightsout

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    val hasLit = IntArray(25) {i -> 0}
    var clickableViews = ArrayList<View> ()
    var tapCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.clickableViews = arrayListOf(
            findViewById<Button>(R.id.button1),findViewById<Button>(R.id.button2),
            findViewById<Button>(R.id.button3),findViewById<Button>(R.id.button4),
            findViewById<Button>(R.id.button5),findViewById<Button>(R.id.button6),
            findViewById<Button>(R.id.button7),findViewById<Button>(R.id.button8),
            findViewById<Button>(R.id.button9),findViewById<Button>(R.id.button10),
            findViewById<Button>(R.id.button11),findViewById<Button>(R.id.button12),
            findViewById<Button>(R.id.button13),findViewById<Button>(R.id.button14),
            findViewById<Button>(R.id.button15),findViewById<Button>(R.id.button16),
            findViewById<Button>(R.id.button17),findViewById<Button>(R.id.button18),
            findViewById<Button>(R.id.button19),findViewById<Button>(R.id.button20),
            findViewById<Button>(R.id.button21),findViewById<Button>(R.id.button22),
            findViewById<Button>(R.id.button23),findViewById<Button>(R.id.button24),
            findViewById<Button>(R.id.button25)
        )
        setListeners()
        loadScene()
    }

    private fun setViewsInvisible(){
        for(item in clickableViews){
            item.setVisibility(View.GONE)
        }
    }

    private fun setViewsVisible(){
        for(item in clickableViews){
            item.setVisibility(View.VISIBLE)
        }
    }

    private fun loadScene(){
        setViewsInvisible()
        findViewById<TextView>(R.id.nickname_edit).text = ""
        findViewById<ConstraintLayout>(R.id.constraint_layout).setBackgroundColor(Color.parseColor("#FFFFFF"))
        findViewById<TextView>(R.id.nickname_text).setVisibility(View.GONE)
        findViewById<TextView>(R.id.score_counter).setVisibility(View.GONE)
        findViewById<TextView>(R.id.count_text).setVisibility(View.GONE)
        findViewById<TextView>(R.id.win_text).setVisibility(View.GONE)
        findViewById<Button>(R.id.reset_button).setVisibility(View.GONE)
        findViewById<Button>(R.id.replay_button).setVisibility(View.GONE)
        findViewById<TextView>(R.id.nickname_edit).setVisibility(View.VISIBLE)
        findViewById<TextView>(R.id.game_title).setVisibility(View.VISIBLE)
        findViewById<Button>(R.id.play_button).setVisibility(View.VISIBLE)
    }

    private fun gameScene(view: View){
        // Hide the keyboard.
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

        findViewById<ConstraintLayout>(R.id.constraint_layout).setBackgroundColor(Color.parseColor("#000000"))
        setViewsVisible()
        findViewById<TextView>(R.id.nickname_text).text = "Player: " + findViewById<TextView>(R.id.nickname_edit).text

        findViewById<TextView>(R.id.nickname_text).setVisibility(View.VISIBLE)
        findViewById<TextView>(R.id.score_counter).setVisibility(View.VISIBLE)
        findViewById<TextView>(R.id.count_text).setVisibility(View.VISIBLE)
        findViewById<Button>(R.id.reset_button).setVisibility(View.VISIBLE)

        findViewById<TextView>(R.id.score_counter).text = tapCount.toString()

        findViewById<TextView>(R.id.game_title).visibility = View.GONE
        findViewById<EditText>(R.id.nickname_edit).visibility = View.GONE
        view.visibility = View.GONE

        resetGame()
    }

    private fun setColor(clickableViews: List<View>, tagNumber: Int, difference: Int){
        if(this.hasLit[tagNumber+difference] == 0){
            clickableViews[tagNumber+difference].setBackgroundColor(Color.parseColor("#3b444b"))
            this.hasLit[tagNumber+difference] = 1
        }else{
            clickableViews[tagNumber+difference].setBackgroundColor(Color.parseColor("#FFC107"))
            this.hasLit[tagNumber+difference] = 0
        }
    }
    private fun toggleColor (view: View, clickableViews: List<View>) {
        this.tapCount += 1
        findViewById<TextView>(R.id.score_counter).text = tapCount.toString()
        val tagNumber = Integer.parseInt(view.tag.toString()) - 1

        if(this.hasLit[tagNumber] == 0){
            clickableViews[tagNumber].setBackgroundColor(Color.parseColor("#3b444b"))
            this.hasLit[tagNumber] = 1
        }else{
            clickableViews[tagNumber].setBackgroundColor(Color.parseColor("#FFC107"))
            this.hasLit[tagNumber] = 0
        }

        if(tagNumber - 1 >= 0 && tagNumber % 5 != 0){
            setColor(clickableViews, tagNumber, -1)
        }
        if(tagNumber + 1 < 25 && tagNumber % 5 != 4){
            setColor(clickableViews, tagNumber, 1)
        }
        if(tagNumber - 5 >= 0){
            setColor(clickableViews, tagNumber, -5)
        }
        if(tagNumber + 5 < 25){
            setColor(clickableViews, tagNumber, 5)
        }

        finishGame()
    }
    private fun setListeners(){
        for(item in clickableViews){
            item.setOnClickListener{ toggleColor(it, clickableViews) }
        }
        findViewById<Button>(R.id.reset_button).setOnClickListener{ resetGame()}
        findViewById<Button>(R.id.play_button).setOnClickListener {
            gameScene(it)
            tapCount = 0
        }
        findViewById<Button>(R.id.replay_button).setOnClickListener(){
            loadScene()
            tapCount = 0
        }
        finishGame()
    }

    private fun resetGame(){
        for(i in 0..24){
            clickableViews[i].setBackgroundColor(Color.parseColor("#FFC107"))
            hasLit[i] = 0
        }
    }

    private fun finishGame(){
        var isFinished = true

        for(i in this.hasLit){
            if(i == 0){
                isFinished = false
                break
            }
        }

        if(isFinished == true){
            setViewsInvisible()
            findViewById<TextView>(R.id.nickname_text).setVisibility(View.GONE)
            findViewById<Button>(R.id.reset_button).setVisibility(View.GONE)
            findViewById<TextView>(R.id.win_text).text = "Congratulations " + findViewById<TextView>(R.id.nickname_edit).text + "!"
            findViewById<TextView>(R.id.win_text).setVisibility(View.VISIBLE)
            findViewById<Button>(R.id.replay_button).setVisibility(View.VISIBLE)
        }
    }
}
