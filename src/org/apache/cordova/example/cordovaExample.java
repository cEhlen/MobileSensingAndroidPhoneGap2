/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package org.apache.cordova.example;

import org.apache.cordova.Config;
import org.apache.cordova.DroidGap;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

public class cordovaExample extends DroidGap
{
	public static final String RESULT = "RESULT_MESSAGE";
	
	public static final String GET_DATA = "GET_ACTIVITY_DATA";
	
	private BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String msg = intent.getStringExtra(parentActivity.EXTRA_MESSAGE);
			sendValue(msg);
		}
    };
	
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Set by <content src="index.html" /> in config.xml
        super.loadUrl(Config.getStartUrl());
        //super.loadUrl("file:///android_asset/www/index.html")
        
        IntentFilter filter = new IntentFilter();
        filter.addAction(GET_DATA);
        registerReceiver(receiver, filter);
        Intent startMain = new Intent(this, parentActivity.class);
        startActivity(startMain);
    }
    
    public void sendResult(String rslt) {
    	Intent intent = new Intent();
        intent.putExtra(RESULT, rslt);
        intent.setAction(parentActivity.RECIEVE_DATA);
        sendBroadcast(intent);
    }
    
	public void sendValue(String value1) {
		JSONObject data = new JSONObject();
		try {
			data.put("value1", value1);
		} catch (JSONException e) {
			Log.e("CommTest", e.getMessage());
		}
		String js = String.format("window.getResult('%s');",
				data.toString());
		this.sendJavascript(js);
	}
}

