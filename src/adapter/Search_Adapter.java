package adapter;

import java.util.ArrayList;
import java.util.List;

import com.english_1_3.R;
import com.english_1_3.ShowActivity;
import com.english_1_3.TestAdapter;
import com.english_1_3.Wordp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Search_Adapter extends ArrayAdapter<Wordp>
{
	private TestAdapter testAdapter;
	private TextView tvword,tvtype,tvVidu,tvsound;
	private Activity context;
	private int layoutID;
	private ArrayList<Wordp> searchlist;
	//Khởi tạo contructer
	public Search_Adapter(Activity context,int layoutID,ArrayList<Wordp> searchlist) {
		super(context,R.layout.row_search,searchlist);
		this.context=context;
		this.searchlist = searchlist;
		this.layoutID=layoutID;
	}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {	
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflater.inflate(layoutID, null);
		//Lấy dữ liệu từ database
		testAdapter=new TestAdapter(context);
		testAdapter.createDatabase();
		testAdapter.open();
		Wordp p=searchlist.get(position);
		tvword=(TextView) convertView.findViewById(R.id.tvWordS);
		tvword.setText(" "+p.getWord().toString().trim());
		//Sự kiện khi mình click vào TextView Word
		tvword.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent mIntent = new Intent(context, ShowActivity.class);
				mIntent.putExtra("mean", searchlist.get(position).getMean());
				mIntent.putExtra("sound", searchlist.get(position).getSound());
				mIntent.putExtra("word", searchlist.get(position).getWord());
				mIntent.putExtra("importan", searchlist.get(position).getImportan());
				context.startActivity(mIntent);//Gọi Activity Show
			}
		});
		//SetTextType
		tvtype =(TextView) convertView.findViewById(R.id.tvTypeS);
		tvtype.setText(p.getType().toString());
		//setTextVidu
		tvVidu =(TextView) convertView.findViewById(R.id.tvVDS);
		tvVidu.setText(p.getVidu().toString());
		//Xử lý sự kiện khi minh2 click vào TextView Ví dụ
		tvVidu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent mIntent = new Intent(context, ShowActivity.class);
				mIntent.putExtra("mean", searchlist.get(position).getMean());
				mIntent.putExtra("sound", searchlist.get(position).getSound());
				mIntent.putExtra("word", searchlist.get(position).getWord());
				mIntent.putExtra("importan", searchlist.get(position).getImportan());
				context.startActivity(mIntent);//Gọi Activity Show
			}
		});
		tvsound =(TextView) convertView.findViewById(R.id.tvSoundS);
		tvsound.setText(" \t"+p.getSound().toString());
		tvsound.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent mIntent = new Intent(context, ShowActivity.class);
				mIntent.putExtra("mean", searchlist.get(position).getMean());
				mIntent.putExtra("sound", searchlist.get(position).getSound());
				mIntent.putExtra("word", searchlist.get(position).getWord());
				mIntent.putExtra("importan", searchlist.get(position).getImportan());
				context.startActivity(mIntent);
			}
		});
		// Sự kiện Click Importan
		final ImageView img;
		img=(ImageView) convertView.findViewById(R.id.btHot);
		if(searchlist.get(position).getImportan()==1)
			img.setImageResource(R.drawable.star_full);
		else
			img.setImageResource(android.R.drawable.btn_star);
		img.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Wordp p1=searchlist.get(position);
				if(searchlist.get(position).getImportan()==1){
					p1.setImportan(0);
					testAdapter.updatetWord(p1);//Update lại Importan của word trong database sau khi sửa chữa
					img.setImageResource(android.R.drawable.btn_star);
				}else if(searchlist.get(position).getImportan()==0){
					p1.setImportan(1);
					testAdapter.updatetWord(p1);//Update lại Importan của word trong database sau khi sửa chữa
					img.setImageResource(R.drawable.star_full);			
				}
				Log.e("TEST", "position prg = "+position);
				Log.e("TEST", "id real = "+p1.getId()+
						"important? "+p1.getImportan());
			}
		});
		//Sound
		final Button btSoudS;
		btSoudS=(Button) convertView.findViewById(R.id.btSoundS);
		btSoudS.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new com.english_1_3.TestTTS(searchlist.get(position).getWord(),context);
			}
		});
		return convertView;
	}

}