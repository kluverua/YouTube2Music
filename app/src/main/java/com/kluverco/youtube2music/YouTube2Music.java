package com.kluverco.youtube2music;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class YouTube2Music extends Activity {

	private static final String YOUTUBE_MUSIC_LINK_TPL = "https://music.youtube.com/watch?v=";

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
			String youTubeMusicLink = YOUTUBE_MUSIC_LINK_TPL + extractVideoCode(sharedText);
			Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(youTubeMusicLink));
			this.startActivity(webIntent);
		}
	}

	String extractVideoCode(String fullLink) {
		return fullLink.replaceAll("youtu[.]be/", "").
				replaceAll("youtube[.]com/watch[?]v=", "").
				replaceAll("https://(www[.])?", "");
	}

}
