package com.keepme.custompaginationjp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepme.custompaginationjp.model.CustomersModel
import com.keepme.custompaginationjp.repository.Repository
import com.keepme.custompaginationjp.state.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManuelPagingViewModel @Inject constructor(
    private val repository: Repository,
): ViewModel() {

    val customerList = mutableStateListOf<CustomersModel.Data>()

    private var page by mutableIntStateOf(1)
    var canPaginate by mutableStateOf(false)
    var listState by mutableStateOf(ListState.IDLE)

    init {
        getNews()
    }

    fun getNews() = viewModelScope.launch {
        if (page == 1 || (page != 1 && canPaginate) && listState == ListState.IDLE) {
            listState = if (page == 1) ListState.LOADING else ListState.PAGINATING

            repository.getCustomers(page).collect {
                if (it.status == "success") {
                    canPaginate = it.customerList.size == 10

                    if (page == 1) {
                        customerList.clear()
                        customerList.addAll(it.customerList)
                    } else {
                        customerList.addAll(it.customerList)
                    }

                    listState = ListState.IDLE

                    if (canPaginate)
                        page++
                } else {
                    listState = if (page == 1) ListState.ERROR else ListState.PAGINATION_EXHAUST
                }
            }
        }
    }

    override fun onCleared() {
        page = 1
        listState = ListState.IDLE
        canPaginate = false
        super.onCleared()
    }
}