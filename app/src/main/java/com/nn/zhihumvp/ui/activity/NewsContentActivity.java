package com.nn.zhihumvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.nn.zhihumvp.R;
import com.nn.zhihumvp.app.Config;
import com.nn.zhihumvp.base.BaseActivity;
import com.nn.zhihumvp.contract.NewsContentContract;
import com.nn.zhihumvp.model.vo.NewsContentVO;
import com.nn.zhihumvp.presenter.NewsContentPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsContentActivity extends BaseActivity implements NewsContentContract.View {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.web_view)
    WebView webView;

    private String id = "";

    private NewsContentPresenter newsContentPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        _initToolbar(toolbar);
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra(Config.BUNDLE);
            id = bundle != null ? bundle.getString("id") : "";
        }
        newsContentPresenter = new NewsContentPresenter(this);
        newsContentPresenter.loadNewsContent(id);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(false);
        webSettings.setSupportZoom(false);
        webSettings.setSupportMultipleWindows(false);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setLoadsImagesAutomatically(true);
    }

    @Override
    public void showLoadHint() {
        _showProgressDialog();
    }

    @Override
    public void hideLoadHint() {
        _hideProgressDialog();
    }

    @Override
    public void showData(@NonNull NewsContentVO newsContent, boolean isRefresh) {
        String html = newsContent.getHtml();
        String css = newsContent.getCss();
        StringBuffer sb = new StringBuffer("<link rel=\"stylesheet\" href=\" " + css + " \"></link>");
        html = html.replace("<div class=\"img-place-holder\"></div>", "<div class=\"img-place-holder\"><img height=\"200\" width=\"400\" src=\"" + newsContent.getImage() + "\"></div>");
        sb.append(html);
        webView.loadDataWithBaseURL(null, sb.toString(), "text/html", "utf-8", null);
        toolbar.setTitle(newsContent.getTitle());
    }

    @Override
    public void showError(String error, boolean isRefresh) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        newsContentPresenter.onDetachView();
        super.onDestroy();
    }
}
