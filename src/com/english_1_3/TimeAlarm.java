package com.english_1_3;

import java.util.ArrayList;

import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.provider.Settings;
import android.provider.Settings.System;

import android.support.v4.app.NotificationCompat;
import android.util.Log;
//BoadcastReceiver thông báo ra màn hình
public class TimeAlarm extends Service{
	private TestAdapter testAdapter;
	NotificationManager notificationManager;
	private static int order = 0;
	ArrayList<Wordp> array=new ArrayList<Wordp>();
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		testAdapter =new TestAdapter(this);
		testAdapter.createDatabase();
		testAdapter.open();
		array=testAdapter.getTestLearn(1);//Lấy các từ được đánh dấu là học trong ngày
		if(array.size()>0){//nếu danh sách rỗng thì không thực hiện thông báo
			notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			if(order>=array.size()) order=0;//nếu order = size thì order = 0 để lặp lại vòng lặp notification
			//Thiết lập và khởi tạo 1 Notification
			NotificationCompat.Builder mbBuilder=new NotificationCompat.Builder(this)
			.setSmallIcon(R.drawable.ic_launcher)
			.setContentTitle(array.get(order).getWord()+"   "+array.get(order).getType())
			.setContentText(array.get(order).getVidu())
			.setAutoCancel(true);
			
			Intent mIntent;
			//Thiết lập âm thanh thông báo
			new TestTTS(array.get(order).getWord(), this);
			
			//Tạo 1 Intent để gán sự kiện khi click vào thông báo
			mIntent = new Intent(this, ShowActivity.class);
			mIntent.putExtra("mean", array.get(order).getMean());
			mIntent.putExtra("sound", array.get(order).getSound());
			mIntent.putExtra("word", array.get(order).getWord());
			PendingIntent launchIntent = PendingIntent.getActivity(this,0,mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			mbBuilder.setTicker(array.get(order).getWord());
			mbBuilder.setContentIntent(launchIntent);
			mbBuilder.setPriority(Notification.PRIORITY_DEFAULT);
			//Xử lý việc thông báo 1 Msg lên màn hình khóa
			array.get(order).setHistory(1);
			testAdapter.updatetWord(array.get(order));
			String messge =array.get(order).getWord() + " \t" 
							+ array.get(order).getType()+" \t"+array.get(order).getSound()
							+" \t"+array.get(order).getVidu();
			Settings.System.putString(getContentResolver(),
					Settings.System.NEXT_ALARM_FORMATTED, messge);
			//Do whatever you need right here
			//to enable
			//_keyguardLock.reenableKeyguard();	
			
			PowerManager pm1 = (PowerManager) getSystemService(Context.POWER_SERVICE);
			boolean isScreenOn = pm1.isScreenOn();
			if(isScreenOn==false)
			{
				
				WakeLock wl1 = pm1.newWakeLock(PowerManager.FULL_WAKE_LOCK |PowerManager.ACQUIRE_CAUSES_WAKEUP |PowerManager.ON_AFTER_RELEASE,"MyLock");
				
				wl1.acquire(10000);
				WakeLock wl_cpu = pm1.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"MyCpuLock");
				
				wl_cpu.acquire(10000);
			}
			order++;//tăng order=next sang từ khác trong danh sách
			notificationManager.notify(1, mbBuilder.build());///tạo thông báo
		}
		return super.onStartCommand(intent, flags, startId);
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}
