package com.pandorina.hal_fiyatlari.presentation.screens.news

import android.util.Log
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.pandorina.hal_fiyatlari.R
import com.pandorina.hal_fiyatlari.domain.model.news.News
import com.pandorina.hal_fiyatlari.presentation.component.*
import com.pandorina.hal_fiyatlari.presentation.navigation.NavGraph
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
                                            navController.navigate(NavGraph.WebScreen.route)
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

@Preview
@Composable
fun NewsItem(
    news: News = News(
        time = "23:49",
        path = "https://www.sondakika.com/politika/haber-zelenskiy-ukrayna-guvenlik-servisi-baskani-ve-15082164/",
        image = "https://i.sdacdn.com/haber/2022/07/17/zelenskiy-ukrayna-guvenlik-servisi-baskani-ve-15082164_local_sd.jpg",
        title = "Zelenskiy, Ukrayna Güvenlik Servisi Başkanı ve Ukrayna Başsavcısını görevden aldı",
        content = "Ukrayna Devlet Başkanı Vladimir Zelenskiy, Ukrayna Güvenlik Servisi Başkanı Ivan Bakanov ve Ukrayna Başsavcısı Irina Venediktova'yı yayınladığı kararname ile görevden aldığını duyurarak, \"Vatana ihanet ve işbirliği faaliyetlerine ilişkin 651 ceza davası kaydedildi\" dedi."
    ),
    onClickNewsItem: (String, String) -> Unit = { title, url -> }
) {
    Card(
        modifier = Modifier.padding(8.dp).clickable {
            onClickNewsItem(
                news.path ?: "https://www.sondakika.com/",
                news.title ?: "")
        }
    ) {
        Column(
            Modifier.padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                CoilImage(
                    imageUrl = news.image
                )
            }
            Text(
                text = "${news.title}",
                fontWeight = FontWeight.SemiBold,
                color = black,
                fontSize = 18.sp
            )
            Text(
                text = "${news.content}",
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = black,
            )
            Text(
                text = "${news.time}",
                fontWeight = FontWeight.Bold
            )
        }
    }
}