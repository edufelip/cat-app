package com.edufelip.catapp.ui.screens.home

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.edufelip.catapp.domain.model.Cat
import com.edufelip.catapp.ui.navigation.Routes.DetailsRoute
import com.edufelip.catapp.ui.theme.CatTheme
import com.edufelip.catapp.ui.viewmodels.HomeViewModel

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    backPressedDispatcher: OnBackPressedDispatcher,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val state = homeViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.getCatList()
    }

    HomeContent(
        navHostController = navHostController,
        state = state.value,
        onItemClick = {
            DetailsRoute.navigate(navHostController)
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    navHostController: NavHostController,
    state: HomeUI,
    onItemClick: () -> Unit,
) {

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            when {
                state.isLoading -> {
                    LinearProgressIndicator()
                }

                state.error -> {
                    Text(text = "Sorry, there was an error")
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 8.dp)
                    ) {
                        items(state.data.size) {
                            CatItem(
                                state.data[it],
                                onItemClick = onItemClick
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CatItem(
    cat: Cat,
    onItemClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick.invoke()
            },
        shape = RoundedCornerShape(30),
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(modifier = Modifier.padding(horizontal = 12.dp), text = cat.name ?: "")
            if (cat.tags?.isNotEmpty() == true) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    items(cat.tags.size) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 8.dp),
                            shape = RoundedCornerShape(50),
                            colors = CardColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = CardDefaults.cardColors().contentColor,
                                disabledContainerColor = CardDefaults.cardColors().disabledContainerColor,
                                disabledContentColor = CardDefaults.cardColors().disabledContentColor,
                            )
                        ) {
                            Text(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                text = cat.tags?.get(it) ?: "",
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    val navHostController = rememberNavController()
    CatTheme {
        HomeContent(
            navHostController = navHostController,
            state = HomeUI(
                isLoading = false,
                error = false,
                data = listOf(
                    Cat(
                        id = "1",
                        name = "Cat 1",
                        tags = listOf("Cute", "Fluffy", "Tabby")
                    ),
                    Cat(
                        id = "2",
                        name = "Cat 2",
                        tags = listOf("Cute", "Fluffy", "Tabby")
                    )
                )
            ),
            onItemClick = {}
        )
    }
}