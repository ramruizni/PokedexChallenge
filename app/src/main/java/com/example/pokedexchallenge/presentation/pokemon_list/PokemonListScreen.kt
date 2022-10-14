package com.example.pokedexchallenge.presentation.pokemon_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokedexchallenge.R
import com.example.pokedexchallenge.presentation.destinations.LoginScreenDestination
import com.example.pokedexchallenge.presentation.destinations.PokemonListScreenDestination
import com.example.pokedexchallenge.presentation.pokemon_list.pages.FavoritesPage
import com.example.pokedexchallenge.presentation.pokemon_list.pages.MainListPage
import com.example.pokedexchallenge.ui.composable.box.BoxBackground
import com.example.pokedexchallenge.ui.composable.box.BoxLoadingIndicator
import com.example.pokedexchallenge.ui.composable.box.BoxRefreshingIndicator
import com.example.pokedexchallenge.ui.composable.pagerTabIndicatorOffset
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
@Destination
fun PokemonListScreen(
    navigator: DestinationsNavigator,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val state = viewModel.state

    val tabData = listOf(
        stringResource(R.string.pokemon_list_tab_pokedex) to painterResource(id = R.drawable.ic_list),
        stringResource(R.string.pokemon_list_tab_favorites) to painterResource(id = R.drawable.ic_favorite_marked)
    )

    val pagerState = rememberPagerState(
        pageCount = tabData.size,
        initialOffscreenLimit = 1,
        infiniteLoop = false,
        initialPage = 0,
    )
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.onEvent(PokemonListEvent.RefreshFavorites)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (tabIndex == 0) stringResource(R.string.pokemon_list_tab_pokedex)
                        else stringResource(R.string.pokemon_list_tab_favorites),
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.onEvent(PokemonListEvent.Logout)
                        navigator.navigate(
                            direction = LoginScreenDestination(),
                            onlyIfResumed = true
                        ) {
                            popUpTo(PokemonListScreenDestination.route) {
                                inclusive = true
                            }
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_logout),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        BoxBackground(padding = innerPadding)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { index ->
                if (index == 0) {
                    MainListPage(
                        entries = state.entries,
                        navigator = navigator,
                        viewModel = viewModel
                    )
                } else {
                    FavoritesPage(
                        favorites = state.favoriteEntries,
                        navigator = navigator
                    )
                }
            }
            TabRow(
                selectedTabIndex = tabIndex,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)

                    )
                }
            ) {
                tabData.forEachIndexed { index, pair ->
                    Tab(selected = tabIndex == index, onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }, icon = {
                        Icon(
                            painter = pair.second, contentDescription = null,
                            modifier = Modifier
                                .padding(top = 4.dp, bottom = 10.dp)
                        )
                    }
                    )
                }
            }
        }

    }

    BoxRefreshingIndicator(isRefreshing = state.isRefreshing)
    BoxLoadingIndicator(isLoading = state.isLoading)
}