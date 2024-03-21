import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.timer.ui.timer1.Timer1ViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Timer

class TimerTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var timer: Timer
    private lateinit var viewModel: Timer1ViewModel

    @Before
    fun setup() {
        timer = Timer()
        viewModel = Timer1ViewModel()
        viewModel.timer = timer
    }

    @Test
    fun `Time format`() {
        val time = viewModel.formatTime(59)
        assertEquals("00:00:59", time)
    }

    @Test
    fun testTimerFunctionality() {
        //Start button, by checking increase in value
        viewModel.startTimer()
        Thread.sleep(100) // Wait for the timer to increment
        assertEquals(1, viewModel.timerValueLiveData.value)

        //Stop button, via checking lack of change
        viewModel.pauseTimer()
        assertEquals(1, viewModel.timerValueLiveData.value) // Value should remain the same after pausing

        //Reset button, via checking reset to 0
        viewModel.resetTimer()
        assertEquals(0, viewModel.timerValueLiveData.value)
    }
}
