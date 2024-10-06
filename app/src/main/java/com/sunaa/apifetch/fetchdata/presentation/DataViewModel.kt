package com.sunaa.apifetch.fetchdata.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunaa.apifetch.fetchdata.data.remote.JsonModel
import com.sunaa.apifetch.fetchdata.data.repo.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {

    private val _data = MutableLiveData<List<JsonModel>?>()
    val data: LiveData<List<JsonModel>?> = _data

    private val _singleData = MutableLiveData<JsonModel?>()
    val singleData: LiveData<JsonModel?> = _singleData


    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _dataId = MutableStateFlow("")
    val dataId: StateFlow<String> = _dataId.asStateFlow()


    fun onValueChange(newvalue: String) {
        _dataId.value = newvalue
    }

    fun fetchData() {
        _loading.value = true // Set loading to true before fetching data
        viewModelScope.launch {
            try {
                val response = dataRepository.getData()
                if (response.isSuccessful) {
                    _data.value = response.body()
                } else {
                    // Handle error
                }
            } catch (e: Exception) {
                // Handle exception
            } finally {
                _loading.value = false // Set loading to false after fetching data
            }
        }
    }

    fun fetchData(id: Int) {
        viewModelScope.launch {
            try {
                val response = dataRepository.getData(id)
                if (response.isSuccessful) {
                    _singleData.value = response.body()
                    Log.i("data", singleData.value.toString())
                } else {
                    // Handle error
                }
            } catch (e: Exception) {
                // Handle exception
            }
        }
    }
}