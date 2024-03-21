package com.example.timer.ui.timer3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.timer.databinding.FragmentTimer3Binding

class Timer3Fragment : Fragment() {
    private var _binding: FragmentTimer3Binding? = null
    private val binding get() = _binding!!

    private lateinit var timerViewModel: Timer3ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTimer3Binding.inflate(inflater, container, false)
        val root: View = binding.root

        timerViewModel = ViewModelProvider(this).get(Timer3ViewModel::class.java)

        val timerTextView: TextView = binding.timer3TimerTextView
        timerViewModel.timerValue.observe(viewLifecycleOwner) { time ->
            timerTextView.text = getTimeString(time)
        }

        // Start, pause, and reset buttons
        binding.timer3StartButton.setOnClickListener {
            timerViewModel.startTimer()
        }

        binding.timer3PauseButton.setOnClickListener {
            timerViewModel.pauseTimer()
        }

        binding.timer3ResetButton.setOnClickListener {
            timerViewModel.resetTimer()
        }

        return root
    }

    // Format time in HH:MM:SS
    private fun getTimeString(timeInSeconds: Long): String {
        val hours = timeInSeconds / 3600
        val minutes = (timeInSeconds % 3600) / 60
        val seconds = timeInSeconds % 60
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
