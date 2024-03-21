package com.example.timer.ui.timer1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Timer
import kotlin.concurrent.scheduleAtFixedRate

class Timer1ViewModel : ViewModel() {
    private val timerValueLiveData = MutableLiveData<Long>()
    val timerValue: LiveData<Long>
        get() = timerValueLiveData

    private var timer: Timer? = null

    init {
        timerValueLiveData.value = 0
    }

    fun startTimer() {
        timer = Timer()
        timer?.scheduleAtFixedRate(0, 1000) {
            timerValueLiveData.postValue(timerValueLiveData.value!! + 1)
        }
    }

    fun pauseTimer() {
        timer?.cancel()
        timer = null
    }

    fun resetTimer() {
        pauseTimer()
        timerValueLiveData.postValue(0)
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }
}
