package com.example.kotlinflow

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.kotlinflow.ui.theme.KotlinFlowTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinFlowTheme {
                val viewModel = viewModels<MyviewModel>()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier.fillMaxSize().padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {

                        Column {
                            val textfeildvalue = remember { mutableStateOf(-5) }
                            val textfeildvalue1 = remember { mutableStateOf(-5) }
                            Text(text = textfeildvalue.value.toString())
                            Text(text = textfeildvalue1.value.toString())


                            lifecycleScope.launch() {
                                viewModel.value.sharedFlow.collectLatest {
                                    textfeildvalue.value = it
                                    Log.d("MY_Tag", it.toString())
                                }
                            }
                            lifecycleScope.launch() {
                                    delay(5000)

                            viewModel.value.sharedFlow.collectLatest{
                                    textfeildvalue1.value = it
                                    Log.d("MY_Tag1", it.toString())
                            }





                        }
                    }
                }
            }}}}}
