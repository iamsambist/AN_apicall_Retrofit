package com.sunaa.apifetch.fetchdata.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sunaa.apifetch.fetchdata.data.remote.JsonModel

@Composable
fun ScreenDisplay(
    viewModel: DataViewModel = hiltViewModel(),
    onNavigation: (() -> Unit)
) {
    val data by viewModel.data.observeAsState(emptyList()) // Provide a default empty list
    val loading by viewModel.loading.observeAsState(false) // Observe the loading state

    LaunchedEffect(Unit) {
        viewModel.fetchData()
    }
    Column(modifier = Modifier.fillMaxSize()) {

        // Show loading indicator while data is being fetched
        if (loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(WindowInsets.statusBars.asPaddingValues())
            ) {
                // Replace with your desired loading indicator
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(WindowInsets.statusBars.asPaddingValues())
            ) {

                Row(horizontalArrangement = Arrangement.Center) {
                    Button(onClick = {
                        onNavigation()
                    }) {
                        Text(text = "lets Go")
                    }
                }
                // Render your data here
                LazyColumn {
                    items(data ?: listOf(JsonModel(1, 1, "not Working", "same"))) { post ->
                        // Display each post
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(WindowInsets.statusBars.asPaddingValues())
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 5.dp)
                            ) {
                                Text(text = post.id.toString())
                                Text(text = post.title, modifier = Modifier.weight(4f))
                                Text(text = post.body) // Customize as needed
                                HorizontalDivider(thickness = 2.dp)
                            }
                        }
                    }
                }

            }

        }

    }


}