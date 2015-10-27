package com.tealeaf.plugin.plugins;
import com.tealeaf.TeaLeaf;
import com.tealeaf.logger;
import com.tealeaf.plugin.IPlugin;
import com.tealeaf.EventQueue;
import com.tealeaf.event.*;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class QRScannerPlugin implements IPlugin {

	private String TAG = "{admob}";
	private static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";
	private Activity mActivity;

	public void QRScannerPlugin() {}
	// public void onCreateApplication(Context applicationContext) {}


	public void onCreate(Activity activity, Bundle savedInstanceState) {
		mActivity = activity;
	}

	public void onResume() {}

	public void onStart() {}

	public void onPause() {}

	public void onStop() {}

	public void onDestroy() {
	}

	public void onNewIntent(Intent intent) {}

	public void setInstallReferrer(String referrer) {}

	public void onActivityResult(Integer request, Integer result, Intent data) {
		android.util.Log.d("Tiendv", "onActivityResult ===============");
	}

	public boolean consumeOnBackPressed() {
		return true;
	}

	public void onBackPressed() {}

	private static boolean getJsonBoolean(JSONObject jObject, String key) {
		if (jObject == null) {
			return false;
		}

		boolean res = false;
		try {
			res = jObject.getBoolean(key);
		} catch (Exception ex) {}
		return res;
	}

	private static String getJsonString(JSONObject jObject, String key) {
		if (jObject == null) {
			return "";
		}

		String res = "";
		try {
			res = jObject.getString(key);
		} catch (Exception ex) {}
		return res;
	}
	
	public void scanBarCode(String jsonData) {
		logger.log(TAG, "scanBarCode");
		
		try {
			Intent intent = new Intent(ACTION_SCAN);
			intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
			mActivity.startActivityForResult(intent, 0);
		} catch (ActivityNotFoundException anfe) {
			showDialog(mActivity, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
		}		
	}
	
	public void scanQRCode(String jsonData) {
		logger.log(TAG, "scanQRCode");
		try {
			Intent intent = new Intent(ACTION_SCAN);
			intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
			mActivity.startActivityForResult(intent, 0);
		} catch (ActivityNotFoundException anfe) {
			showDialog(mActivity, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
		}
	}
	
	private static AlertDialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {
		AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
		downloadDialog.setTitle(title);
		downloadDialog.setMessage(message);
		downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialogInterface, int i) {
				Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				try {
					act.startActivity(intent);
				} catch (ActivityNotFoundException anfe) {

				}
			}
		});
		downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialogInterface, int i) {
			}
		});
		return downloadDialog.show();
	}
	
	// public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		// if (requestCode == 0) {
			// if (resultCode == RESULT_OK) {
				// String contents = intent.getStringExtra("SCAN_RESULT");
				// String format = intent.getStringExtra("SCAN_RESULT_FORMAT");

				// Toast toast = Toast.makeText(this, "Content:" + contents + " Format:" + format, Toast.LENGTH_LONG);
				// toast.show();
			// }
		// }
	// }	
}
