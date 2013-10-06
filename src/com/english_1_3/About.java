package com.english_1_3;

import android.app.Activity;
import android.os.Bundle;

import com.searchboxsdk.android.StartAppSearch;
import com.startapp.android.publish.StartAppAd;

public class About extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		// for advertisements
		StartAppSearch.init(this);
		StartAppSearch.showSearchBox(this);
	}
}
