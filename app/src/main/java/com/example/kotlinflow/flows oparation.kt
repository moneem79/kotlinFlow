package com.example.kotlinflow

import androidx.compose.runtime.Composable
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.reduce



//**Transformative Operators**:`filter`, `map`, `onEach`//

suspend fun mapFilteronEach() {

    val flow = flowOf(1, 2, 3, 4, 5)
        .filter { it > 2 }
        .map { it * 10 }
        .onEach { println("Value after map: $it") }

    flow.collect { println("Collected: $it") }
}


//**Terminal Operators**: `count`, `reduce`, `fold`//

suspend fun count(){
    val count = flowOf(1, 2, 3, 4, 5).count { it > 2 }
    println(count)
// Output: 3

}

suspend fun reduce(){
    val sum = flowOf(1, 2, 3, 4, 5).reduce { acc, value -> acc + value }
    println(sum)
// Output: 15

}

suspend fun fold(){
    val sum = flowOf(1, 2, 3, 4, 5).fold(10) { acc, value -> acc + value }
    println(sum)
// Output: 25

}
//**Flattening Operators**: `flatMapConcat`, `flatMapMerge`, `flatMapLatest`//
suspend fun flatMapConcat(){
    val flow = flowOf(1, 2, 3).flatMapConcat { value ->
        flowOf("$value A", "$value B")
    }

    flow.collect { println(it) }

}

suspend fun flatMapMerge () {
    val flow = flowOf(1, 2, 3)

        .flatMapMerge { value ->
            flow {
                emit("$value A")
                delay(100)
                emit("$value B")
            }
        }

    flow.collect { println(it) }

}
suspend fun flatMapLatest() {
    val flow = flowOf(1, 2, 3).flatMapLatest { value ->
        flow {
            emit("$value A")
            delay(100)
            emit("$value B")
        }
    }

    flow.collect { println(it) }

}


//**Flow Control Operators**: `buffer`, `conflate`, `collectLatest`//
suspend fun buffer() {
    val flow = flow {
        repeat(6) {
            emit(it)
            println("Emitted: $it")
        }
    }.buffer()

    flow.collect { value ->
        delay(1000)
        println("Collected: $value")
    }
}
suspend fun  conflate() {
    val flow = flow {
        repeat(5) {
            emit(it)
            println("Emitted: $it")
        }
    }.conflate()

    flow.collect { value ->
        delay(1000)
        println("Collected: $value")
    }


}
suspend fun collectLatest() {
    val flow = flow {
        repeat(5) {
            emit(it)
            println("Emitted: $it")
        }
    }

    flow.collectLatest { value ->
        delay(1000)
        println("Collected: $value")
    }

}