package ru.surfstudio.telemedicine.ui.screen.webview

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.Toolbar
import android.webkit.WebView
import org.jetbrains.anko.find
import ru.surfstudio.android.core.mvp.activity.BaseLdsActivityView
import ru.surfstudio.android.custom.view.placeholder.StandardPlaceHolderView
import ru.surfstudio.telemedicine.R
import ru.surfstudio.telemedicine.ui.base.configurator.ActivityScreenConfigurator
import ru.surfstudio.telemedicine.ui.common.widget.placeholder.PlaceHolderViewImpl
import javax.inject.Inject

/**
 *  Заготовка экрана с вебвью, без логики
 *  Активити с экрана с вебвью
 **/
class WebViewActivityView : BaseLdsActivityView<WebViewScreenModel>() {

    @Inject
    lateinit var presenter: WebViewPresenter

    private lateinit var placeholder: StandardPlaceHolderView
    private lateinit var webView: WebView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?,
                          persistentState: PersistableBundle?,
                          viewRecreated: Boolean) {
        super.onCreate(savedInstanceState, persistentState, viewRecreated)
        findViews()
    }

    override fun getPlaceHolderView(): PlaceHolderViewImpl = find(R.id.placeholder)

    override fun renderInternal(screenModel: WebViewScreenModel) {
        toolbar.title = screenModel.title
    }

    override fun getContentView(): Int = R.layout.activity_webview

    private fun findViews() {
        placeholder = find(R.id.placeholder)
        webView = find(R.id.activity_webview_wv)
        toolbar = find(R.id.toolbar)
    }

    override fun createConfigurator(): ActivityScreenConfigurator = WebViewScreenConfigurator(intent)

    override fun getPresenters() = arrayOf(presenter)

    override fun getScreenName() = "Web View Screen"
}