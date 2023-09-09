package com.keepme.custompaginationjp.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.keepme.custompaginationjp.state.ListState
import com.keepme.custompaginationjp.viewmodel.ManuelPagingViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerScreen(navController: NavHostController) {

    Scaffold(
        topBar = {

        },
        content = {
            CustomerContent(it, navController)
        }
    )
}

@Composable
fun CustomerContent(paddingValues: PaddingValues, navController: NavHostController) {

    val viewModel = hiltViewModel<ManuelPagingViewModel>()
    val lazyColumnListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val shouldStartPaginate = remember {
        derivedStateOf {
            viewModel.canPaginate && (lazyColumnListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -9) >= (lazyColumnListState.layoutInfo.totalItemsCount - 6)
        }
    }

    val customerList = viewModel.customerList

    LaunchedEffect(key1 = shouldStartPaginate.value) {
        if (shouldStartPaginate.value && viewModel.listState == ListState.IDLE)
            viewModel.getNews()
    }


    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        state = lazyColumnListState) {

        items(
            items = customerList,
            key = { it.name!! }
        ) { customer ->

            Text(
                modifier = Modifier
                    .height(75.dp)
                    .clickable {
                        navController.navigate("details")
                    },
                text = customer.name!!,
                color = Color.Black
            )

            Divider()
        }

        item (
            key = viewModel.listState,
        ) {
            when(viewModel.listState) {
                ListState.LOADING -> {
                    Column(
                        modifier = Modifier
                            .fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = "Refresh Loading"
                        )

                        CircularProgressIndicator(color = Color.Black)
                    }
                }
                ListState.PAGINATING -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = "Pagination Loading")

                        CircularProgressIndicator(color = Color.Black)
                    }
                }
                ListState.PAGINATION_EXHAUST -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 6.dp, vertical = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Icon(imageVector = Icons.Rounded.Face, contentDescription = "")

                        Text(text = "Nothing left.")

                        TextButton(
                            modifier = Modifier
                                .padding(top = 8.dp),
                            onClick = {
                                coroutineScope.launch {
                                    lazyColumnListState.animateScrollToItem(0)
                                }
                            },
                            content = {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.KeyboardArrowUp,
                                        contentDescription = ""
                                    )

                                    Text(text = "Back to Top")

                                    Icon(
                                        imageVector = Icons.Rounded.KeyboardArrowUp,
                                        contentDescription = ""
                                    )
                                }
                            }
                        )
                    }

                }
                else -> {}
            }
        }

    }
}

