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
import com.example.cruz_exer3_lightsout.databinding.FragmentGameBinding
import com.example.cruz_exer3_lightsout.databinding.FragmentGameWonBinding

/**
 * A simple [Fragment] subclass.
 */
class GameWonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentGameWonBinding>(inflater, R.layout.fragment_game_won, container, false)

        binding.constraintLayout.setBackgroundColor(Color.parseColor("#000000"))

        return binding.root
    }


}
