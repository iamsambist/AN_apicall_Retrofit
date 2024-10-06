package com.sunaa.apifetch.fetchdata.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sunaa.apifetch.ui.theme.APIFetchTheme

@Composable
fun ScreenDisplayItem(
    backBtnAction: (() -> Unit),
    viewModel: DataViewModel = hiltViewModel()
) {
    val dataInput by viewModel.dataId.collectAsState()
    val datum by viewModel.singleData.observeAsState()
    Log.i("datum",datum.toString())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues())
    ) {
        Button(onClick = {
            backBtnAction()
        }) {
            Text(text = "let's Go back")
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Enter Id Between 1 to 100")
            Spacer(Modifier.height(15.dp))
            TextField(
                value = dataInput, onValueChange = { newText ->
                    if (newText.all { it.isDigit() }) {
                        viewModel.onValueChange(newText)
                    }
                },
                label = { Text(text = "Enter a Number") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )
            Spacer(Modifier.height(5.dp))
            Button(onClick = {
                viewModel.fetchData(dataInput.toInt())
            }) {
                Text(text = "Get Data")
            }
        }

        if (datum != null) {
            Text(text = "ID: ${datum?.id}")
            Text(text = "Title: ${datum?.title ?: "No Title"}")
            Text(text = "Body: ${datum?.body ?: "No Body"}")
        } else {
            Text(
                text = "No data available",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenDispalyItemPreview() {
    APIFetchTheme {
        ScreenDisplayItem(backBtnAction = {})
    }
}
