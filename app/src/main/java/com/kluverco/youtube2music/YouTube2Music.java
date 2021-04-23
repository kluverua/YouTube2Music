package com.kluverco.youtube2music;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class YouTube2Music extends Activity {

	private static final String YOUTUBEMUSICLINKTMP = "https://music.youtube.com/watch?v=";

	@Override
	public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		String action = intent.getAction();
		String type = intent.getType();
		if (Intent.ACTION_SEND.equals(action) && "text/plain".equals(type)) {
			handleSendText(intent);
		}
	}

	void handleSendText(Intent intent) {
		String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
		if (sharedText != null) {
			String youTubeMusicLink = YOUTUBEMUSICLINKTMP + extractVideoCode(sharedText);
			Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(youTubeMusicLink));
			this.startActivity(webIntent);
		}
	}

	String extractVideoCode(String fullLink) {
		//https://youtu.be/P9y2winModI
		//https://www.youtube.com/watch?v=7L6G4sQui_M
		return fullLink.replaceAll("youtu[.]be/", "").
				replaceAll("youtube[.]com/watch[?]v=", "").
				replaceAll("https://(www[.])?", "");
	}

}
