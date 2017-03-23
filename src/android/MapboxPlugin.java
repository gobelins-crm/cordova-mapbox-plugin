package ddi.gobelins.cordovaplugins;

import android.widget.Toast;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class MapboxPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);

            Toast.makeText(webView.getContext(), message, Toast.LENGTH_LONG).show();

            if (message != null && message.length() > 0) {
              callbackContext.success(message);
            } else {
              callbackContext.error("Expected one non-empty string argument.");
            }

            return true;
        }
        return false;
    }
}
