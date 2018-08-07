package sneha.com.nytimes.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import sneha.com.nytimes.R;
import sneha.com.nytimes.models.Result;

public class DetailActivity extends AppCompatActivity {

    TextView textViewDetail;
    Result result;
    WebView webViewDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        result = (Result) getIntent().getExtras().getSerializable("result");

        webViewDescription = findViewById(R.id.webViewDescription);
        webViewDescription.loadUrl(result.getUrl());
    }
}
