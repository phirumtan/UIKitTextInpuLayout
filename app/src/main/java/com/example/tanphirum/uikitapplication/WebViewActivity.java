package com.example.tanphirum.uikitapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {

    private WebView mWebview;
    private ProgressBar mProgressBar;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        mProgressBar = findViewById(R.id.pb);
        mWebview = findViewById(R.id.wv);

        mProgressBar.setMax(100);

        mWebview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                // Activities and WebViews measure progress with different scales.
                // The progress meter will automatically disappear when we reach 100%
                //activity.setProgress(progress * 1000);
                mProgressBar.setProgress(progress);
            }


        });
        mWebview.setWebViewClient(new WebViewClient() {

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(view.getContext(), "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgressBar.setVisibility(View.GONE);
            }
        });
        JavaScriptInterface javaInterface = new JavaScriptInterface(this);
        mWebview.addJavascriptInterface(javaInterface, "JSInterface");


        //mWebview.loadUrl("https://developers.google.com/");

        String summary = "<html><body>You scored <b>192</b> points.</body></html>";
        mWebview.loadData(htmlTest, "text/html", null);
        //mWebview.loadDataWithBaseURL("""my.sabay.com/");

    }

    public class JavaScriptInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        JavaScriptInterface(Context c) {
            mContext = c;
        }

        @android.webkit.JavascriptInterface
        public void changeActivity()
        {
            Toast.makeText(mContext, "Handle click event", Toast.LENGTH_SHORT).show();
        }
    }

    private String htmlTest = "<html>\n" +
            "<head>\n" +
            "<script type=\"text/javascript\">\n" +
            "function displaymessage()\n" +
            "{\n" +
            "JSInterface.changeActivity();\n" +
            "}\n" +
            "</script>\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "<form>\n" +
            "<input type=\"button\" value=\"Click me!\" onclick=\"displaymessage()\" />\n" +
            "</form>\n" +
            "</body>\n" +
            "</html>";

}
