package com.pandorina.hal_fiyatlar.presentation.screens.news.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pandorina.hal_fiyatlar.domain.model.news.News
import com.pandorina.hal_fiyatlar.presentation.component.CoilImage
import com.pandorina.hal_fiyatlar.presentation.theme.black

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
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${news.title}",
                fontWeight = FontWeight.SemiBold,
                color = black,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${news.content}",
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = black,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${news.time}",
                fontWeight = FontWeight.Bold
            )
        }
    }
}