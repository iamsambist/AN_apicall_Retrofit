package com.sunaa.apifetch.fetchdata.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun ScreenSimpleNavigation(){
    var showScreenDisplay by remember { mutableStateOf(true)}
    if(showScreenDisplay){
        ScreenDisplay(onNavigation = {
            showScreenDisplay = false
        })
    }else{
        ScreenDisplayItem(backBtnAction = {
            showScreenDisplay =true
        })
    }
}