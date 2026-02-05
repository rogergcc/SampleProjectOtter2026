package com.rogergcc.sampleprojectotter2026

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rogergcc.sampleprojectotter2026.ui.composables.CityHomeScreen
import com.rogergcc.sampleprojectotter2026.ui.theme.SampleProjectOtter2026Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SampleProjectOtter2026Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CityHomeScreen(
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SampleProjectOtter2026Theme {
        CityHomeScreen(
            modifier = Modifier.padding(16.dp),
            preview = true
        )
    }
}