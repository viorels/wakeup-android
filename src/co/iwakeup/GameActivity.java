package co.iwakeup;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GameActivity extends Activity {
	WebView gameWebView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		
		gameWebView = (WebView) findViewById(R.id.webview);
//		gameWebView.loadUrl("http://whackamine.meteor.com");
		gameWebView.loadUrl("http://www.inmensia.com/files/minesweeper1.0.html");

		WebSettings webSettings = gameWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
	
		gameWebView.setWebViewClient(new WebViewClient());
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    // Check if the key event was the Back button and if there's history
	    if ((keyCode == KeyEvent.KEYCODE_BACK) && gameWebView.canGoBack()) {
	        gameWebView.goBack();
	        return true;
	    }
	    // If it wasn't the Back key or there's no web page history, bubble up to the default
	    // system behavior (probably exit the activity)
	    return super.onKeyDown(keyCode, event);
	}

}
