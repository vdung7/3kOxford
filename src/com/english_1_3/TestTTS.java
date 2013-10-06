package com.english_1_3;

import java.util.Locale;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;

public class TestTTS implements OnInitListener{
	private TextToSpeech mTts;
	private String message;
	public TestTTS(String message, Context context){
		this.message = message;
		mTts = new TextToSpeech(context, this);
		mTts.setLanguage(Locale.UK);
	}
	@Override
	 public void onInit(int i)
    {
    	  if (i==mTts.SUCCESS)
    	    {
    		  mTts.speak(message, TextToSpeech.QUEUE_FLUSH, null);
    	    }
    }

}
