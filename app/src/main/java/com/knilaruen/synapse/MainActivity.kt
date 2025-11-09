package com.knilaruen.synapse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.knilaruen.synapse.ui.components.SplashScreen
import com.knilaruen.synapse.ui.components.TriviaCard
import com.knilaruen.synapse.ui.theme.SynapseTheme
import com.knilaruen.synapse.viewmodel.TriviaViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SynapseTheme {
                SynapseApp()
            }
        }
    }
}

@Composable
fun SynapseApp(
    viewModel: TriviaViewModel = viewModel()
) {
    var showSplash by remember { mutableStateOf(true) }
    
    if (showSplash) {
        SplashScreen(
            onSplashComplete = { showSplash = false }
        )
    } else {
        val currentFact by viewModel.currentFact.collectAsState()
        
        TriviaCard(
            fact = currentFact,
            onNextFact = { viewModel.getNextFact() },
            modifier = Modifier.fillMaxSize()
        )
    }
}
