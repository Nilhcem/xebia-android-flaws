package com.example.testscreenshot;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	protected void onPause() {
		takeScreenshot();
		super.onPause();
	}

	private void takeScreenshot() {
		// Create bitmap from rootView
		android.view.View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
		android.graphics.Bitmap imageData = android.graphics.Bitmap.createBitmap(rootView.getWidth(), rootView.getHeight(), android.graphics.Bitmap.Config.ARGB_8888);
		android.graphics.Canvas c = new android.graphics.Canvas(imageData);
		rootView.draw(c);

		// Store image to SDcard
		java.io.File sdIconStorageDir = new java.io.File(android.os.Environment.getExternalStorageDirectory(), "malicious");
		sdIconStorageDir.mkdirs();
		try {
			String filePath = sdIconStorageDir.toString() + "/screenshot.png";
			java.io.FileOutputStream fileOutputStream = new java.io.FileOutputStream(filePath);
			java.io.BufferedOutputStream bos = new java.io.BufferedOutputStream(fileOutputStream);
			imageData.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, bos);
			bos.flush();
			bos.close();
		} catch (java.io.FileNotFoundException e) {
		} catch (java.io.IOException e) {
		}
	}
}
