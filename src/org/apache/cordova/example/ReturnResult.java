package org.apache.cordova.example;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class ReturnResult extends CordovaPlugin {
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if("returnResult".equals(action)) {
			String rslt = args.getString(0);
			((cordovaExample)this.cordova.getActivity()).sendResult(rslt);
			return true;
		}
		return false;
	}

}
