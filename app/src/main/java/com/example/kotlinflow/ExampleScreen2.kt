package com.example.kotlinflow

import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun ExampleScreen2(viewModel: mainViewMODel= viewModel()){
    val time =viewModel.countDownFlow.collectAsState(initial = 10)



    Box (modifier = Modifier.fillMaxSize()){
        Text(
            text = time.value.toString(),
            fontSize = 30.sp,
            modifier = Modifier.align(Alignment.Center)
        )


    }



}

