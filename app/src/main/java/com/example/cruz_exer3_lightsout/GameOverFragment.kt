package com.example.cruz_exer3_lightsout


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.cruz_exer3_lightsout.databinding.FragmentGameOverBinding

/**
 * A simple [Fragment] subclass.
 */
class GameOverFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentGameOverBinding>(inflater, R.layout.fragment_game_over, container, false)

        binding.constraintLayout.setBackgroundColor(Color.parseColor("#000000"))
        binding.replayButton.setOnClickListener{view: View-> view.findNavController().navigate(R.id.action_gameOverFragment_to_titleFragment)}

        return binding.root
    }


}
