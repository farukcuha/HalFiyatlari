package com.pandorina.hal_fiyatlar.presentation.component

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.webkit.*
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.launch

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun CustomWebView(modifier: Modifier = Modifier,
                  url:String,
                  onBack: (webView: WebView?) -> Unit,
                  isLoading: (Boolean)->Unit = {}){
    val webViewChromeClient = WebChromeClient()
    val webViewClient = object: WebViewClient(){
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            isLoading(true)
        }
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            isLoading(false)
        }
    }
    var webView: WebView? = null
    val coroutineScope = rememberCoroutineScope()
    AndroidView(modifier = modifier,factory = { ctx ->
        WebView(ctx).apply {
            this.webViewClient = webViewClient
            this.webChromeClient = webViewChromeClient
            webView = this
            loadUrl(url)
        }
    })
    BackHandler {
        coroutineScope.launch {
            onBack(webView)
        }
    }
}