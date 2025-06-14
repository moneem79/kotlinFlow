package com.example.kotlinflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class mainViewMODel:ViewModel() {

    val countDownFlow = flow<Int> {
        val startingValue = 10
        var currentValue = startingValue
        emit(startingValue)
        while (currentValue > 0){
            delay(1000L)
            currentValue--
            emit(currentValue)


        }


    }
    init {
        collectFlow()
    }

    private fun collectFlow() {
       val count = countDownFlow.onEach {
            println(it)
        }.launchIn(viewModelScope)
        viewModelScope.launch {
            countDownFlow
                .flatMapConcat { value ->
                    flow {
                        emit("A")
                        delay(100)
                        emit("B")
                    }

                }
            }
        }
    }
