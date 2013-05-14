package org.apache.cordova.example;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class parentActivity extends Activity {
	
	public final static String EXTRA_MESSAGE = "myExtraMessage";
	public final static String RECIEVE_DATA = "PARENT_RECIEVE_DATA";
	
	private TextView tLog;
	
	private BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String msg = intent.getStringExtra(cordovaExample.RESULT);
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();			
			tLog.setText("Got Result");
			TextView result = (TextView) findViewById(R.id.textView2);
			result.setText(msg);
		}	
	};
	
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.parent_layout);
		
		tLog = (TextView) findViewById(R.id.textView1);
		
		IntentFilter filter = new IntentFilter();
        filter.addAction(RECIEVE_DATA);
        registerReceiver(receiver, filter);		
	}
	
	public void sendMessage(View view) {
		tLog.setText("Sent message");
		Intent intent = new Intent();
		intent.setAction(cordovaExample.GET_DATA);
		EditText editText = (EditText) findViewById(R.id.editText1);
		String message = editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		sendBroadcast(intent);
	}
}
