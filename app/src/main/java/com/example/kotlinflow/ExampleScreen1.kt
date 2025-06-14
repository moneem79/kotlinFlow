package com.example.kotlinflow

import MyviewModel
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.collectLatest


@Composable
fun ExampleScreen1(viewModel: MyviewModel = viewModel()) {

    val textFieldValue = remember { mutableStateOf(-5) }


    LaunchedEffect(Unit) {
        viewModel.sharedFlow.collectLatest { value ->
            textFieldValue.value = value
            Log.d("MY_Tag", value.toString())
        }
    }



    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(text = textFieldValue.value.toString())

        }
    }
}