package adapter;

import java.util.ArrayList;

import com.english_1_3.Activity_ViewReguler;
import com.english_1_3.R;
import com.english_1_3.Regular;
import com.english_1_3.ShowActivity;
import com.english_1_3.TestAdapter;
import com.english_1_3.Wordp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Reguler_Adapter extends ArrayAdapter<Regular>{
	private TestAdapter testAdapter;
	private Activity context;
	private ArrayList<Regular> regulerList;
	private TextView tvWord,tvMean;
	private int layoutID;
	public Reguler_Adapter(Activity context,int layoutID,ArrayList<Regular> list) {
		super(context,R.layout.row_iregular,list);
		this.context=context;
		this.regulerList = list;
		this.layoutID=layoutID;
	}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView=inflater.inflate(layoutID, null);
		//goi databse;
		testAdapter=new TestAdapter(context);
		testAdapter.createDatabase();
		testAdapter.open();
		
		Regular regular=regulerList.get(position);
		tvWord=(TextView) convertView.findViewById(R.id.tvNameRl);
		tvMean=(TextView) convertView.findViewById(R.id.tvMeanRl);
		
		tvWord.setText(Html.fromHtml("<strong><font color='#3D3D3D'>" + regular.getInfinitive()+"</font></strong>"+" - "
				+"<strong><font color='#009900'>" + regular.getPast_simple()+"</font></strong>"+" - "
				+"<strong><font color='#4D00FF'>" + regular.getPast_paticiple()+"</font></strong>"
				));
		tvWord.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent mIntent = new Intent(context, Activity_ViewReguler.class);
				mIntent.putExtra("infi",regulerList.get(position).getInfinitive());
				mIntent.putExtra("simple",regulerList.get(position).getPast_simple());
				mIntent.putExtra("past",regulerList.get(position).getPast_paticiple());
				mIntent.putExtra("example",regulerList.get(position).getExample());
				mIntent.putExtra("mean",regulerList.get(position).getVi_content());
				context.startActivity(mIntent);
			}
		});
		tvMean.setText(regular.getVi_content());
		tvMean.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent mIntent = new Intent(context,Activity_ViewReguler.class);
				mIntent.putExtra("infi",regulerList.get(position).getInfinitive());
				mIntent.putExtra("simple",regulerList.get(position).getPast_simple());
				mIntent.putExtra("past",regulerList.get(position).getPast_paticiple());
				mIntent.putExtra("example",regulerList.get(position).getExample());
				mIntent.putExtra("mean",regulerList.get(position).getVi_content());
				context.startActivity(mIntent);
			}
		});
		return convertView;
	}
}
