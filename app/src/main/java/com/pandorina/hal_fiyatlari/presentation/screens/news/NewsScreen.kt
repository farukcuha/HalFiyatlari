package com.pandorina.hal_fiyatlari.presentation.screens.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.pandorina.hal_fiyatlari.R
import com.pandorina.hal_fiyatlari.domain.model.news.News
import com.pandorina.hal_fiyatlari.presentation.component.*
import com.pandorina.hal_fiyatlari.presentation.navigation.NavigationRoutes
import com.pandorina.hal_fiyatlari.presentation.screens.news.components.NewsItem
import com.pandorina.hal_fiyatlari.presentation.theme.black
import com.pandorina.hal_fiyatlari.presentation.theme.white
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NewsScreen(
    navController: NavController
) {
    val viewModel: NewsViewModel = hiltViewModel()
    val uiState = viewModel.newsUiStateUiState.value

    val pagerState = rememberPagerState(initialPage = 0)
    viewModel.currentPage.update { pagerState.currentPage }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = stringResource(id = R.string.news),
                navigationIcon = {
                    MenuIcon(action = MenuAction.Back) {
                        navController.popBackStack()
                    }
                }
            )
        },
        content = {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                if (uiState.isLoading){
                    LoadingBar(modifier = Modifier.align(Alignment.Center))
                }

                uiState.categories?.let { categories ->
                    Column {
                        ScrollableTabRow(
                            selectedTabIndex = pagerState.currentPage,
                            edgePadding = 16.dp,
                            backgroundColor = white
                        ) {
                            categories.forEachIndexed { index, category ->
                                Tab(selected = pagerState.currentPage == index,
                                    onClick = {
                                        coroutineScope.launch {
                                            pagerState.scrollToPage(index)
                                        }
                                    },
                                    text = { Text(text = category.title ?: return@Tab) })
                            }
                        }
                        uiState.news?.let { news ->
                            HorizontalPager(state = pagerState, count = categories.size) {
                                LazyColumn{
                                    items(news.size){
                                        NewsItem(news = news[it]){ path, title ->
                                            navController.navigate(NavigationRoutes.WebScreen.route)
                                            webViewTitle = title
                                            webViewUrl = path
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

