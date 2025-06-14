import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch


class MyviewModel : ViewModel() {
    val sharedFlow = MutableSharedFlow<Int>()

    init {
        emitvalue()
    }

    fun emitvalue() {
        viewModelScope.launch {
            repeat(10) {
                delay(1000)

                sharedFlow.emit(it)
            }
        }
    }
}
