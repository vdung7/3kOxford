package adapter;

import com.english_1_3.R;

import android.R.color;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Word_Adapter extends ArrayAdapter<String>{
	private Activity mActivity;
	private String [] lines;
	private int IDlayout;
	private TextView view;
	public Word_Adapter(Activity context, int resource, String[] objects) {
		super(context, resource, objects);
		this.mActivity=context;
		this.lines=objects;
		this.IDlayout=resource;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflater.inflate(IDlayout, null);
		view=(TextView)convertView.findViewById(R.id.tvWordline);
		view.setText(lines[position]);
		String line=lines[position];
		if(line.charAt(0)=='*'){
			view.setTypeface(null, Typeface.BOLD);
			view.setTextSize(20);
			}
		else if(line.charAt(0)=='-'){
			view.setTextColor(Color.BLUE);
		}
		else if(line.charAt(0)=='='){
			view.setTextColor(Color.RED);
			view.setTypeface(null, Typeface.ITALIC);
		}
		else if(line.charAt(0)=='!'){
			view.setTypeface(null, Typeface.BOLD);
			view.setTextSize(17);
		}
		return convertView;
	}
}
