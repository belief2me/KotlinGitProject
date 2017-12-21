package com.btlm.kotlingitproject.module.app

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.*
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.utils.AppUtils
import com.btlm.kotlingitproject.utils.ClipboardUtils
import com.btlm.kotlingitproject.utils.ToastUtils
import com.btlm.kotlingitproject.widget.statusbar.StatusBarUtil
import com.yoyiyi.soleil.constant.Constants
import kotlinx.android.synthetic.main.activity_brower.*
import kotlinx.android.synthetic.main.common_toolbar.*
import kotlinx.android.synthetic.main.layout_loading.*

class BrowerActivity : AppCompatActivity() {

    private var mTitle : String? = null
    private var mUrl : String? = null
    private var mImg : String? = null

    companion object {
        fun startActivity(context : Context, url : String,title : String,img : String ){
            val intent = Intent(context,BrowerActivity::class.java)
            intent.putExtra(Constants.EXTRA_TITLE,title)
            intent.putExtra(Constants.EXTRA_URL,url)
            intent.putExtra(Constants.EXTRA_IMAGE,img)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brower)
        initVariables()
        initWedgit()
    }
    private fun initWedgit(){
        initToolbar()
        initWebView()
        StatusBarUtil.setColorNoTranslucent(this,AppUtils.getColor(R.color.colorPrimary))
        //强制影藏加载框
//        AppUtils.runOnUIDelayed(() -> mPwLoading.setVisibility(View.GONE), 650);
    }
    private fun initWebView(){
        val webChromeClient = WebClient()
        val webViewClient = WebClientBase()
        val webSettings = web_view.settings
        //设置js支持
        webSettings.javaScriptEnabled = true
        // 设置支持javascript脚本
        webSettings.javaScriptEnabled = true
        //设置支持javascript脚本
        webSettings.javaScriptCanOpenWindowsAutomatically = false
        //设置缓存
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE
        webSettings.domStorageEnabled = true
        webSettings.setGeolocationEnabled(true)
        //关键点
        webSettings.useWideViewPort = true
        //全屏
        webSettings.loadWithOverviewMode = true
        // 设置显示缩放按钮
        webSettings.builtInZoomControls = true
        // 支持缩放
        webSettings.displayZoomControls = false
        webSettings.setAppCacheEnabled(true)
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        web_view.isDrawingCacheEnabled = true
        web_view.settings.blockNetworkImage = true
        web_view.setWebViewClient(webViewClient)
        web_view.requestFocus(View.FOCUS_DOWN)
        web_view.settings.defaultTextEncodingName = "UTF-8"
        web_view.setWebChromeClient(webChromeClient)
        web_view.loadUrl(mUrl)
    }
    private fun initToolbar(){
        toolbar?.let {
            it.setNavigationIcon(R.drawable.ic_clip_back_white)
            it.title = if(TextUtils.isEmpty(mTitle)) "详情" else mTitle
            setSupportActionBar(it)
            it.setNavigationOnClickListener {
                finish()
            }
        }
    }

    private fun initVariables(){
        intent?.let {
            mTitle = intent.getStringExtra(Constants.EXTRA_TITLE)
            mUrl = intent.getStringExtra(Constants.EXTRA_URL)
            mImg = intent.getStringExtra(Constants.EXTRA_IMAGE)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_brower,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when(id){
            R.id.menu_open -> {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(mUrl)
                startActivity(intent)
            }
            R.id.menu_share -> showShare()
            R.id.menu_copy -> {
                ClipboardUtils.copyText(mUrl)
                ToastUtils.showSingleLongToast("复制成功")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showShare(){

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode === KeyEvent.KEYCODE_BACK && web_view!!.canGoBack()){
            //返回前一个界面
            web_view.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        super.onDestroy()
        web_view?.destroy()
    }

    internal inner class WebClient : WebChromeClient(){
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            if(newProgress >= 40){
                pw_loading.visibility = View.GONE
            }else{
                pw_loading.visibility = View.VISIBLE
            }
            web_view?.settings?.blockNetworkImage = false
        }
    }
    internal inner class WebClientBase : WebViewClient(){
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            pw_loading?.visibility = View.GONE
            web_view?.settings?.blockNetworkImage = false
            val h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED)
            val w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED)
            web_view?.measure(w,h)
        }

        override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
            super.onReceivedError(view, request, error)
            pw_loading?.visibility = View.GONE
            val errorHtml = "<html><body><h2>找不到网页</h2></body></html>"
            web_view?.loadDataWithBaseURL(null, errorHtml, "text/html", "UTF-8", null)
        }
    }
}
