package ir.selfino.android.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import ir.selfino.android.Function.Controller;
import ir.selfino.android.Function.Url.UrlSelf;
import ir.selfino.android.R;

import static ir.selfino.android.Function.Controller.TAG;

public class CreditActivity extends AppCompatActivity
{
    private WebView webView;
    private ImageView imgBack;
    private ProgressBar pbr;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
        imgBack=(ImageView)findViewById(R.id.img_back_credit);
        imgBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                onBackPressed();
            }
        });
        pbr=(ProgressBar)findViewById(R.id.pbr_increase_credit);
        webView=(WebView)findViewById(R.id.web_increase_credit);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);

        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                pbr.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url)
            {
                pbr.setVisibility(View.GONE);
                super.onPageFinished(view, url);
                if(webView.getUrl().equals(UrlSelf.getReserveUrl()))
                {
                    setResult(RESULT_OK,getIntent());
                    onBackPressed();
                }else
                Controller.MyWeb.evaluateJavascript("(function () {" +
                        "try {" +
                        "var object = '{';" +
                        "object = object + '\"ok\":true,';" +
                        "object = object + '\"message\":\"' + document.querySelector('span#Label1').textContent.toString() + '\"';" +
                        "object = object + '}';" +
                        "return object.toString();" +
                        "}" +
                        "catch(err){" +
                        "var object='{';" +
                        "object=object+'\"ok\":false,';" +
                        "object=object+'\"message\":\"'+err.message+'\"';" +
                        "object=object+'}';" +
                        "return object.toString();" +
                        "}" +
                        "}" +
                        "());", new ValueCallback<String>()
                {
                    @Override
                    public void onReceiveValue(String s)
                    {
                        try
                        {
                            s = s.substring(1, s.length() - 1);
                            s = s.replace("\\", "");
                            Log.d(TAG, "onReceiveSelf: " + s);
                            JSONObject jsonObject = new JSONObject(s);
                            if (jsonObject.getBoolean("ok"))
                            {
                                if(jsonObject.getString("message").contains("انجام شد")||jsonObject.getString("message").contains("موفقیت"))
                                {
                                    Intent intent=new Intent();
                                    setResult(RESULT_OK,getIntent());
                                    onBackPressed();
                                }

                            } else
                            {
                                Log.d(TAG, "onLoginError : " + jsonObject.getString("message"));
                            }

                        } catch (JSONException e)
                        {
                            Log.d(TAG, "onLoginJsonError: " + e.getMessage());
                        }
                    }
                });
            }
        });
        webView.loadUrl(UrlSelf.getEtebarUrl());
    }
}
