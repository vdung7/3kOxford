package com.english_1_3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Activity_ViewReguler extends Activity{
	private TestAdapter testAdapter;
	private TextView tvInfi,tvSimple,tvPast,tvExample,tvVi;
	private Button btInfi,btSimple,btPast,btExample;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regular);
		//get data from intent
		Intent mydata= getIntent();
		
		final String infi=mydata.getStringExtra("infi");
		final String simple=mydata.getStringExtra("simple");
		final String past=mydata.getStringExtra("past");
		final String example=mydata.getStringExtra("example");
		final String mean=mydata.getStringExtra("mean");
		//create Textview
		tvInfi=(TextView) findViewById(R.id.tInfi);
		tvSimple=(TextView) findViewById(R.id.tSimple);
		tvPast=(TextView) findViewById(R.id.tPast);
		tvExample=(TextView) findViewById(R.id.tExample);
		tvVi=(TextView) findViewById(R.id.tMean);
		
		// add data to TextView
		tvInfi.setText(infi);
		tvSimple.setText(simple);
		tvPast.setText(past);
		tvExample.setText(example);
		tvVi.setText(mean);
		
		btInfi=(Button) findViewById(R.id.bInfi);
		btSimple=(Button) findViewById(R.id.bSimple);
		btPast=(Button) findViewById(R.id.bPast);
		btExample=(Button) findViewById(R.id.bExample);
		
		btInfi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new com.english_1_3.TestTTS(infi.trim(),Activity_ViewReguler.this);
			}
		});
		btSimple.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new com.english_1_3.TestTTS(simple.trim(),Activity_ViewReguler.this);
			}
		});
		btPast.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new com.english_1_3.TestTTS(past.trim(),Activity_ViewReguler.this);
			}
		});
		btExample.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new com.english_1_3.TestTTS(example.trim(),Activity_ViewReguler.this);
			}
		});
		
	}
}
