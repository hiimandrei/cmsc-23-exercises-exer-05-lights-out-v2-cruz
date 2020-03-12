package com.example.cruz_exer3_lightsout


import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.fragment.findNavController
import com.example.cruz_exer3_lightsout.databinding.ActivityMainBinding
import com.example.cruz_exer3_lightsout.databinding.FragmentGameBinding

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding

    val hasLit = IntArray(25) {i -> 0}
    var clickableViews = ArrayList<View> ()
    var tapCount = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentGameBinding>(inflater, R.layout.fragment_game, container, false)

        this.clickableViews = arrayListOf(
            binding.button1, binding.button2,
            binding.button3, binding.button4,
            binding.button5, binding.button6,
            binding.button7, binding.button8,
            binding.button9, binding.button10,
            binding.button11, binding.button12,
            binding.button13, binding.button14,
            binding.button15, binding.button16,
            binding.button17, binding.button18,
            binding.button19, binding.button20,
            binding.button21, binding.button22,
            binding.button23, binding.button24,
            binding.button25
        )


        setListeners()
        binding.constraintLayout.setBackgroundColor(Color.parseColor("#000000"))
        binding.scoreCounter.text = tapCount.toString()

        return binding.root
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
        binding.scoreCounter.text = tapCount.toString()
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
        binding.resetButton.setOnClickListener{
            resetGame()
        }

        finishGame()
    }

    private fun resetGame(){
        for(i in 0..24){
            clickableViews[i].setBackgroundColor(Color.parseColor("#FFC107"))
            hasLit[i] = 0
        }
        tapCount = 0
        binding.scoreCounter.text = tapCount.toString()
    }

    private fun finishGame(){
        var isFinished = true

        for(i in this.hasLit){
            if(i == 0){
                isFinished = false
                break
            }
        }

        if(tapCount > 15){
            findNavController().navigate(R.id.action_gameFragment_to_gameOverFragment)
            return
        }

        if(isFinished == true){
            findNavController().navigate(R.id.action_gameFragment_to_gameWonFragment)
        }
    }


}
