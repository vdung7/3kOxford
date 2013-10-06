package adapter;

import java.util.ArrayList;

import com.english_1_3.R;
import com.english_1_3.ShowActivity;
import com.english_1_3.TestAdapter;
import com.english_1_3.Wordp;

import android.R.color;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
//Adpater cho list Learn và list Onday
public class Learn_Adapter extends ArrayAdapter<Wordp>
{
	private TestAdapter testAdapter;
	private TextView tv1,tv2,tv3,tv4;
	private Activity context;
	private int layoutID;
	private ArrayList<Wordp> searchlist;
	public Learn_Adapter(Activity context,int layoutID,ArrayList<Wordp> searchlist) {
		super(context,R.layout.row_learn,searchlist);
		this.context=context;
		this.searchlist = searchlist;
		this.layoutID=layoutID;
	}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {	
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflater.inflate(layoutID, null);
		//Gọi database
		testAdapter=new TestAdapter(context);
		testAdapter.createDatabase();
		testAdapter.open();
		Wordp p=searchlist.get(position);
		tv1=(TextView) convertView.findViewById(R.id.tvWordL);
		tv1.setText(" "+p.getWord().toString());
		if(p.getHistory()==1){
			tv1.setTextColor(Color.GRAY);
		}
		//Set sự kiện click cho TextView word
		tv1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent mIntent = new Intent(context, ShowActivity.class);
				mIntent.putExtra("mean", searchlist.get(position).getMean());
				mIntent.putExtra("sound", searchlist.get(position).getSound());
				mIntent.putExtra("word", searchlist.get(position).getWord());
				context.startActivity(mIntent);
			}
		});
		
		//set Text cho TextView Type
		tv2 =(TextView) convertView.findViewById(R.id.tvTypeL);
		tv2.setText(p.getType().toString());
		//set Text cho TextView câu ví dụ
		tv3 =(TextView) convertView.findViewById(R.id.tvVDL);
		tv3.setText(p.getVidu().toString());
		//set Sự kiện click cho TextView ví dụ
		tv3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent mIntent = new Intent(context, ShowActivity.class);
				mIntent.putExtra("mean", searchlist.get(position).getMean());
				mIntent.putExtra("sound", searchlist.get(position).getSound());
				mIntent.putExtra("word", searchlist.get(position).getWord());
				context.startActivity(mIntent);
			}
		});
		tv4 =(TextView) convertView.findViewById(R.id.tvSoundL);
		tv4.setText(" \t"+p.getSound().toString().trim());
		tv4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent mIntent = new Intent(context, ShowActivity.class);
				mIntent.putExtra("mean", searchlist.get(position).getMean());
				mIntent.putExtra("sound", searchlist.get(position).getSound());
				mIntent.putExtra("word", searchlist.get(position).getWord());
				context.startActivity(mIntent);
			}
		});
		// Set sự kiện cho CheckBox
		final CheckBox cb;
		cb=(CheckBox) convertView.findViewById(R.id.btCb);
		if(searchlist.get(position).getLearn()==1)
			cb.setChecked(true);
		else
			cb.setChecked(false);
		cb.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Wordp p1=searchlist.get(position);
				if(searchlist.get(position).getLearn()==1){
					p1.setLearn(0);
					testAdapter.updatetWord(p1);
					cb.setChecked(false);
				}else if(searchlist.get(position).getLearn()==0){
					p1.setLearn(1);
					testAdapter.updatetWord(p1);
					cb.setChecked(true);
				}
				Log.e("TEST", "position prg = "+position);
				Log.e("TEST", "id real = "+p1.getId()+
						"important? "+p1.getLearn());
			}
		});
		//Set sự kiện cho Button Sound
		final Button btSoudL;
		btSoudL=(Button) convertView.findViewById(R.id.btSoundL);
		btSoudL.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new com.english_1_3.TestTTS(searchlist.get(position).getWord(),context);
			}
		});
		return convertView;
	}

}