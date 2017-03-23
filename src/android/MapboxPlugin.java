package ddi.gobelins.cordovaplugins;

import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMapOptions;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class MapboxPlugin extends CordovaPlugin {

    private MapView mapView;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);

            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Mapbox.getInstance(webView.getContext(), "pk.eyJ1IjoibG91aXNibCIsImEiOiJjaWpvY3NwczgwMDV6dm9seHU5ZjFoNXF5In0.H105hDH2k6Xf43K7bOwb6g");

                    MapboxMapOptions options = new MapboxMapOptions()
                            .styleUrl(Style.OUTDOORS)
                            .camera(new CameraPosition.Builder()
                                    .target(new LatLng(43.7383, 7.4094))
                                    .zoom(12)
                                    .build());

                    mapView = new MapView(webView.getContext(), options);

                    mapView.onStart();
                    mapView.onCreate(null);

                    int webViewWidth = webView.getView().getWidth();
                    int webViewHeight = webView.getView().getHeight();

                    final FrameLayout layout = (FrameLayout) webView.getView().getParent();

                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(webViewWidth, webViewHeight);

                    mapView.setLayoutParams(params);

                    layout.addView(mapView);
                }
            });

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
