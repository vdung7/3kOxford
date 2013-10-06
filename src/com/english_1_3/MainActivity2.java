package com.english_1_3;

import java.util.ArrayList;

import adapter.Learn_Adapter;
import adapter.Reguler_Adapter;
import adapter.Search_Adapter;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.searchboxsdk.android.StartAppSearch;
import com.startapp.android.publish.StartAppAd;

//Class chính hiện trên màn hình
public class MainActivity2 extends Activity {
	private static final int LIST3000 = 0;
	private static final int LEARN = 1;
	private static final int IMPORTANT = 2;
	private static final int ONDAY = 3;
	private static final int REGULAR = 4;
	private int inteval;

	public static ListView listView;
	public static TestAdapter testAdapter;

	public static ArrayList<Wordp> plist=new ArrayList<Wordp>();
	public static ArrayList<Wordp> pi=new ArrayList<Wordp>();
	public static ArrayList<Wordp> po=new ArrayList<Wordp>();
	public static ArrayList<Wordp> pl=new ArrayList<Wordp>();
	public static ArrayList<Regular> pr=new ArrayList<Regular>();

	private DrawerLayout mDrawerLayout;
	private static int currentview=LIST3000;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mplanetTitles;
	private EditText etSearch;
	private EditText edit;
	private AlarmManager am;
	private Button btstar,btstop;
	private Button btHelp1,btHelpAlarm1;

	// for advertisements
	private StartAppAd startAppAd = new StartAppAd(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// use for advertisements
		StartAppSearch.init(this);

		//Opend database
		testAdapter =new TestAdapter(this);
		testAdapter.createDatabase();
		testAdapter.open();
		//
		plist=testAdapter.getTestData();
		// Thiet lap Navication Drawer
		mTitle = mDrawerTitle = getTitle();
		//settitle cho Drawer
		mplanetTitles = getResources().getStringArray(R.array.planets_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout1);
		mDrawerList = (ListView) findViewById(R.id.left_drawer1);

		//set layout cho drawer
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadwn, Gravity.START);
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, mplanetTitles));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		int a=mDrawerList.getSelectedItemPosition();


		// make ActionBar in bottom and top
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		//make ActionBar with EditText Search
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		Acb_Search();
		//set Drawer into ActionBar
		mDrawerToggle = new ActionBarDrawerToggle(
				this,                   //host Activity 
				mDrawerLayout,          //DrawerLayout object 
				R.drawable.ic_drawer,   //nav drawer image to replace 'Up' caret 
				R.string.drawer_open,   //"open drawer" description for accessibility 
				R.string.drawer_close   //"close drawer" description for accessibility 
				)
		{
			public void onDrawerClosed(View view) {
				//getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				//getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
			}
		};
		//Xứ lý sự kiện Click cho mỗi DrawerItem
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			selectItem(0);
		}
	}

	//function Resume
	@Override
	protected void onResume() {
		super.onResume();

		// for advertisements
		startAppAd.onResume();

		int c = getCurrentView();
		switch (c) {
		case LIST3000:{
			//().setCustomView(R.layout.search);
			Acb_Search();
			plist=testAdapter.getTestData();
			listView.setAdapter(new Search_Adapter(MainActivity2.this,R.layout.row_search,plist));
			break;
		}
		case IMPORTANT:{
			Acb_Search();
			pi=testAdapter.getTestImportant(1);
			listView.setAdapter(new Search_Adapter(MainActivity2.this,R.layout.row_search,pi));
			break;
		}
		case LEARN:{
			Acb_Search();
			pl=testAdapter.getTestData();
			listView.setAdapter(new Learn_Adapter(MainActivity2.this,R.layout.row_learn,pl));
			break;
		}
		case ONDAY:{
			Acb_Onday(); 
			po=testAdapter.getTestLearn(1);
			listView.setAdapter(new Learn_Adapter(MainActivity2.this,R.layout.row_learn,po));
			break;
		}
		case REGULAR:{
			Acb_Search(); 
			pr=testAdapter.getRegular();
			listView.setAdapter(new Reguler_Adapter(MainActivity2.this,R.layout.row_iregular,pr));
			break;
		}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		//menu.findItem(R.id.about).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	//Hàm bắt sự kiện Menu
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
	//Hàm băt sự kiện Drawer Item
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			switch(position){
			case 0:
				setCurrentView(LIST3000);
				mDrawerLayout.closeDrawer(mDrawerList);//set gia tri cho biến định vị gia trị Item
				menuList(); //gọi hàm Menulist
				return;
			case 1:
				setCurrentView(IMPORTANT);
				mDrawerLayout.closeDrawer(mDrawerList);//set gia tri cho biến định vị gia trị Item
				menuImportan(); //gọi hàm MenuImporan
				return;
			case 2:
				setCurrentView(LEARN);
				mDrawerLayout.closeDrawer(mDrawerList);//set gia tri cho biến định vị gia trị Item
				menuLearn(); //gọi gàm MenuLearn
				return;
			case 3:
				setCurrentView(ONDAY);
				mDrawerLayout.closeDrawer(mDrawerList);
				menuOnday(); 
				return;
			case 4:
				setCurrentView(REGULAR);
				mDrawerLayout.closeDrawer(mDrawerList);
				menuRegular(); 
				return;
			case 5:
				menuAbout();
				mDrawerLayout.closeDrawer(mDrawerList);
				return;
			}
		}


	}

	//set Current
	public void setCurrentView(int i){
		this.currentview = i;
	}
	public int getCurrentView(){
		return currentview;
	}
	// Menu View All list 3000 word
	public void menuList() {
		//getActionBar().setCustomView(R.layout.search);
		Acb_Search();
		//get Text from Edittext Search
		plist=testAdapter.getTestData();
		listView.setAdapter(new Search_Adapter(this,R.layout.row_search,plist));

		return;
	}

	//Menu Importan
	private void menuImportan() {
		//getActionBar().setCustomView(R.layout.search);
		//get Text from Edittext Search
		Acb_Search();
		pi=testAdapter.getTestImportant(1);
		listView.setAdapter(new Search_Adapter(this,R.layout.row_search,pi));
		return;
	}

	//Menu Learn 
	protected void menuLearn() {
		//get Text from Edittext Search
		//getActionBar().setCustomView(R.layout.search);
		Acb_Search();
		pl=testAdapter.getTestData();
		listView.setAdapter(new Learn_Adapter(this,R.layout.row_learn,pl));

		return;
	}

	//Activity Onday
	protected void menuOnday() {
		//Intent Main3 = new Intent(MainActivity2.this, Onday_Activity.class);
		//startActivity(Main3);
		Acb_Onday();
		return;
	}

	//Create even Regular
	public void menuRegular() {
		Acb_Search();
		pr=testAdapter.getRegular();
		listView.setAdapter(new Reguler_Adapter(this, R.layout.row_iregular, pr));
	}

	//Xử lý sự kiện 
	// Watch About
	public void menuAbout() {
		Intent Main4 = new Intent(MainActivity2.this, About.class);
		startActivity(Main4);
		return;
	}

	private void Acb_Search() {
		getActionBar().setCustomView(R.layout.search);

		//get Text from Edittext Search
		btHelp1=(Button)findViewById(R.id.btHelp1);
		btHelp1.setOnClickListener(new OnClickListener() {
			AlertDialog alertDialog;
			@Override
			public void onClick(View v) {
				int c = getCurrentView();
				// TODO Auto-generated method stub
				//Toast.makeText(MainActivity2.this, "oncler", Toast.LENGTH_SHORT).show();
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
				final FrameLayout frameView = new FrameLayout(MainActivity2.this);
				builder.setView(frameView);
				builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});


				// là row_search với arraylist là temp vừa lấy được
				switch (c) {
				case IMPORTANT:
				case LIST3000:
				{
					builder.setTitle("Search & Menu");
					break;
				}
				///Nếu Đang ở Navigation với List item=3. thì set lại adapter cho listView với layout 
				// là row_learn với arraylist là temp vừa lấy được
				case LEARN:
				{
					builder.setTitle("Chọn Từ Để Học");
					break;
				}
				}
				alertDialog = builder.create();
				LayoutInflater inflater = alertDialog.getLayoutInflater();
				View dialoglayout;
				switch (c) {
				case IMPORTANT:
				case LIST3000:
				{
					dialoglayout=inflater.inflate(R.layout.dialog_search_menu,frameView);	
					break;
				}
				///Nếu Đang ở Navigation với List item=3. thì set lại adapter cho listView với layout 
				// là row_learn với arraylist là temp vừa lấy được
				case LEARN:
				{
					dialoglayout=inflater.inflate(R.layout.dialog_chontu,frameView);	
					break;
				}
				}

				alertDialog.show();			
			}
		});
		etSearch=(EditText) findViewById(R.id.etSearch);
		etSearch.addTextChangedListener(new TextWatcher() {
			//Event when changed word on EditTex
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				//Lọc  array list voi nhưng phần tử là 1 word có word chứa ký tự trong EditTex
				int c = getCurrentView();
				if(c==IMPORTANT){
					ArrayList<Wordp> temp=new ArrayList<Wordp>();
					int textlength = etSearch.getText().length();
					pi=testAdapter.getTestImportant(1);
					temp.clear();
					for (int i = 0; i < pi.size(); i++)
					{
						if (textlength <= pi.get(i).getWord().length())
						{
							if(etSearch.getText().toString().equalsIgnoreCase(
									(String)
									pi.get(i).getWord().subSequence(0,
											textlength)))
							{
								temp.add(pi.get(i));
							}
						}
					}
					///Nếu Đang ở Navigation với List item=3. thì set lại adapter cho listView với layout 
					// là row_learn với arraylist là temp vừa lấy được
					listView.setAdapter(new Search_Adapter(MainActivity2.this,R.layout.row_search,temp));
				}
				else if(c==LIST3000 || c==LEARN){

					ArrayList<Wordp> temp=new ArrayList<Wordp>();
					int textlength = etSearch.getText().length();
					temp.clear();
					for (int i = 0; i < plist.size(); i++)
					{
						if (textlength <= plist.get(i).getWord().length())
						{
							if(etSearch.getText().toString().equalsIgnoreCase(
									(String)
									plist.get(i).getWord().subSequence(0,
											textlength)))
							{
								temp.add(plist.get(i));
							}
						}
					}
					//Nếu Đang ở Navigation với List item=1 hay 2. thì set lại adapter cho listView với layout 
					// là row_search với arraylist là temp vừa lấy được
					switch (c) {
					case LIST3000:
					{
						listView.setAdapter(new Search_Adapter(MainActivity2.this,R.layout.row_search,temp));
						break;
					}
					///Nếu Đang ở Navigation với List item=3. thì set lại adapter cho listView với layout 
					// là row_learn với arraylist là temp vừa lấy được
					case LEARN:
						listView.setAdapter(new Learn_Adapter(MainActivity2.this,R.layout.row_learn,temp));
						break;
					}
				}
				else if(c==REGULAR){
					ArrayList<Regular> temp=new ArrayList<Regular>();
					int textlength = etSearch.getText().length();
					temp.clear();
					for (int i = 0; i < pr.size(); i++)
					{
						if (textlength <= pr.get(i).getInfinitive().length())
						{
							if(etSearch.getText().toString().equalsIgnoreCase(
									(String)
									pr.get(i).getInfinitive().subSequence(0,
											textlength)))
							{
								temp.add(pr.get(i));
							}
						}
					}
					listView.setAdapter(new Reguler_Adapter(MainActivity2.this,R.layout.row_iregular,temp));
				}
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}

	private void Acb_Onday() {
		getActionBar().setCustomView(R.layout.alarm);
		po=testAdapter.getTestLearn(1);
		listView.setAdapter(new Learn_Adapter(this,R.layout.row_learn,po));
		edit=(EditText)findViewById(R.id.edIntervalID);
		inteval=0;
		Log.e("number", ""+edit.getText().toString());
		//Tạo sự kiện thông báo thông qua AlarmManager
		am =(AlarmManager) getSystemService(Context.ALARM_SERVICE);
		btstar=(Button)findViewById(R.id.Btnstart);
		btstar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(!edit.getText().toString().equals("")){
					inteval=Integer.parseInt(edit.getText().toString());
					if(inteval>0){
						Log.e("number", ""+edit.getText().toString());
						Toast.makeText(getApplicationContext(), "Started", Toast.LENGTH_LONG).show();
						setRepeatingAlarm(inteval);
					}
					else {
						Toast.makeText(getApplicationContext(), "Nhập vào phút lớn hơn 0 !", Toast.LENGTH_LONG).show();
					}
				}
				else {
					Toast.makeText(getApplicationContext(), "Nhập vào phút lớn hơn 0 !", Toast.LENGTH_LONG).show();
				}
			}
		});
		btHelpAlarm1=(Button) findViewById(R.id.btHelpAlarm);
		btHelpAlarm1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog alertDialog;
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
				final FrameLayout frameView = new FrameLayout(MainActivity2.this);
				builder.setView(frameView);
				builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
				builder.setTitle("Thiết Lập Thời Gian");
				alertDialog = builder.create();
				LayoutInflater inflater = alertDialog.getLayoutInflater();
				View dialoglayout;
				dialoglayout=inflater.inflate(R.layout.dialog_settime,frameView);	

				alertDialog.show();
			}
		});

		//bt Stop
		btstop=(Button) findViewById(R.id.btStop);
		btstop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				stopAlarm();
				stopService(new Intent(MainActivity2.this,TimeAlarm.class));
				Settings.System.putString(getContentResolver(),
						Settings.System.NEXT_ALARM_FORMATTED,"");
				Toast.makeText(getApplicationContext(), "Stopped!", Toast.LENGTH_LONG).show();
			}
		});
	}
	//Listen even Click Item in Drawer
	private void selectItem(int position) {

		Fragment fragment = new GalaxyFragment();
		Bundle args = new Bundle();
		args.putInt(GalaxyFragment.ARG_GALAXY_NUMBER, position);
		fragment.setArguments(args);

		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

		mDrawerList.setItemChecked(position, true);
		setTitle(mplanetTitles[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
	}
	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mDrawerTitle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	public void stopAlarm(){
		Intent intent = new Intent(this, TimeAlarm.class);
		PendingIntent pendingIntent = PendingIntent.getService(this, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);
		am.cancel(pendingIntent);
	}
	//hàm tạo vòng lặp thông báo
	public void setRepeatingAlarm(int a) {
		Intent intent = new Intent(this, TimeAlarm.class);
		//Gọi class TimeAlarm để thực hiện thông báo
		PendingIntent pendingIntent = PendingIntent.getService(this, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);
		am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
				(60 * 1000*a), pendingIntent);
	}
	//Class khởi tạo nền của Drawer khi khởi động phần mềm
	public static class GalaxyFragment extends Fragment {
		public static final String ARG_GALAXY_NUMBER = "galaxy_number";

		public GalaxyFragment() {

		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_planet, container, false);
			listView=(ListView) rootView.findViewById(R.id.listView1);
			listView.setAdapter(new Search_Adapter(getActivity(), R.layout.row_search, plist));

			return rootView;
		}
	}

	// for advertisements
	@Override
	public void onBackPressed() {
		startAppAd.onBackPressed();
		super.onBackPressed();
	}

	@Override
	public void onPause() {
		super.onPause();
		startAppAd.onPause();
	}
}
