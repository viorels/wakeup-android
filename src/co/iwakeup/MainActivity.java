package co.iwakeup;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
	WebView mainWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.webview);

        mainWebView = (WebView) findViewById(R.id.webview);
        mainWebView.loadUrl("http://iwakeup.herokuapp.com/wakeup1.htm");

		WebSettings webSettings = mainWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
	
		mainWebView.setWebViewClient(new WebViewClient());
		
	    setAlarm();
    }

	private void setAlarm() {
		//Create an offset from the current time in which the alarm will go off.
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.SECOND, 5);
	
	    //Create a new PendingIntent and add it to the AlarmManager
	    Intent intent = new Intent(this, AlarmReceiverActivity.class);
	    PendingIntent pendingIntent = PendingIntent.getActivity(this,
	        12345, intent, PendingIntent.FLAG_CANCEL_CURRENT);
	    AlarmManager am =
	        (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
	    am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
	            pendingIntent);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    // Check if the key event was the Back button and if there's history
	    if ((keyCode == KeyEvent.KEYCODE_BACK) && mainWebView.canGoBack()) {
	    	mainWebView.goBack();
	        return true;
	    }
	    // If it wasn't the Back key or there's no web page history, bubble up to the default
	    // system behavior (probably exit the activity)
	    return super.onKeyDown(keyCode, event);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
