package com.english_1_3;

import com.english_1_3.R;

import adapter.Word_Adapter;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
//Class xuất full Info 1 từ ra màn hình
public class ShowActivity extends Activity implements OnClickListener{
	NotificationManager nm;
	private String str3;
	private int i;
	private TestAdapter testAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.word);
		//gọi database
		testAdapter=new TestAdapter(this);
		testAdapter.createDatabase();
		testAdapter.open();
		//Tạo Inten để nhận lệnh click
		Intent mydata= getIntent();
		String str=mydata.getStringExtra("mean");
		String mang[]=str.split("\n");
		for (int i = 0; i < mang.length; i++) {
			Log.e("Ten",mang[i]);
		}
		String str2=mydata.getStringExtra("sound");
		str3=mydata.getStringExtra("word");
		i=mydata.getExtras().getInt("importan");
		
		TextView tv=(TextView)findViewById(R.id.tvWordshow);
		TextView tv2=(TextView)findViewById(R.id.tvshowSound);
		//TextView tv3=(TextView)findViewById(R.id.tvfullshow);
		ListView lv=(ListView)findViewById(R.id.lvShowmean);
		lv.setAdapter(new Word_Adapter(ShowActivity.this, R.layout.tvword, mang));
		final ImageView img;

		img=(ImageView) findViewById(R.id.btHotw);
		if(i==1)
			img.setImageResource(R.drawable.star_full);
		else
			img.setImageResource(android.R.drawable.btn_star);
		final Wordp p1=testAdapter.getTestDataWord(str3);
		//Xử lý sự kiện clicki vào Icon Importan
		img.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(p1.getImportan()==1){
					p1.setImportan(0);
					testAdapter.updatetWord(p1);
					img.setImageResource(android.R.drawable.btn_star);
				}else if(p1.getImportan()==0){
					p1.setImportan(1);
					testAdapter.updatetWord(p1);
					img.setImageResource(R.drawable.star_full);			
				}
				Log.e("TEST", "id real = "+p1.getId()+
						"important? "+p1.getImportan()
						+"word ?= "+p1.getWord());
			}
		});
		//sự kiện với button Sound
		final Button btsound;
		btsound=(Button) findViewById(R.id.button1);
		btsound.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new com.english_1_3.TestTTS(str3,ShowActivity.this);
			}
		});
		tv.setText(str3);
		tv2.setText(str2);
		//tv3.setText(str);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
