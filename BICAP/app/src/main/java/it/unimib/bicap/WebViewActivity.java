package it.unimib.bicap;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.bicap.R;

import it.unimib.bicap.utils.Constants;

public class WebViewActivity extends AppCompatActivity{
    private static String questionarioUrl;
    private static int questionarioPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        questionarioUrl = getIntent().getExtras().getString("url");
        this.setTitle(getIntent().getExtras().getString(Constants.TITOLO_QUESTIONARIO));
        questionarioPosition = getIntent().getExtras().getInt(Constants.QUESTIONARIO_POSITION);
        WebView mWebview = findViewById(R.id.qualtricsWebView);
        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript
        mWebview.setWebViewClient(new QuestionarioWebClient());
        mWebview.loadUrl(questionarioUrl);
        mWebview.loadUrl("http://www.google.com/");
    }

    @Override
    public void onBackPressed() {
        WebView mWebview = findViewById(R.id.qualtricsWebView);
        if (mWebview.canGoBack()) {
            mWebview.goBack();
        }else{
            new AlertDialog.Builder(this)
                    .setMessage(R.string.dialog_message)
                    .setCancelable(false)
                    .setPositiveButton(R.string.dialog_yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent resultIntent = new Intent();
                            setResult(Activity.RESULT_CANCELED, resultIntent);
                            finish();
                        }
                    })
                    .setNegativeButton(R.string.dialog_no, null)
                    .show();
        }
    }

    public class QuestionarioWebClient extends WebViewClient{

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if(url != questionarioUrl){
                //Questionario terminato
                Intent resultIntent = new Intent();
                setResult(Activity.RESULT_OK, resultIntent);
                resultIntent.putExtra(Constants.QUESTIONARIO_POSITION, questionarioPosition);
                finish();
            }
        }
    }

}