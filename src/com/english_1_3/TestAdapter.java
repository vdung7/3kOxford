package com.english_1_3;

import java.io.IOException; 
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context; 
import android.database.Cursor; 
import android.database.SQLException; 
import android.database.sqlite.SQLiteDatabase; 
import android.util.Log; 
//Class chính thao tac1 với dữ liệu
public class TestAdapter  
{ 
	protected static final String TAG = "DataAdapter"; 

	private final Context mContext; 
	private SQLiteDatabase mDb; 
	private DataBaseHelper mDbHelper; 
	public TestAdapter(Context context)  
	{ 
		this.mContext = context; 
		mDbHelper = new DataBaseHelper(mContext); 
	} 
	//khởi tạo 1 TestAdapter
	public TestAdapter createDatabase() throws SQLException  
	{ 
		try  
		{ 
			mDbHelper.createDataBase(); 
		}  
		catch (IOException mIOException)  
		{ 
			Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase"); 
			throw new Error("UnableToCreateDatabase"); 
		} 
		return this; 
	} 
	//Mở sau khi đã đươc khởi tạo
	public TestAdapter open() throws SQLException  
	{ 
		try  
		{ 
			mDbHelper.openDataBase(); 
			mDbHelper.close(); 
			mDb = mDbHelper.getWritableDatabase(); 
		}  
		catch (SQLException mSQLException)  
		{ 
			Log.e(TAG, "open >>"+ mSQLException.toString()); 
			throw mSQLException; 
		} 
		return this; 
	} 
	///close database
	public void close()  
	{ 
		mDbHelper.close(); 
	} 
	//Get all data from database
	public ArrayList<Wordp> getTestData() 
	{ 
		ArrayList<Wordp> list=new ArrayList<Wordp>();
		String sql ="SELECT * FROM data"; 
		Cursor mCur = mDb.rawQuery(sql, null); 
		if (mCur!=null) 
		{ 
			mCur.moveToNext(); 
		} 
		while(mCur.isAfterLast()==false){
			Wordp resut=new Wordp();
			resut.setId(mCur.getInt(mCur.getColumnIndex("id")));
			resut.setWord(mCur.getString(mCur.getColumnIndex("word")));
			resut.setMean(mCur.getString(mCur.getColumnIndex("mean")));
			resut.setType(mCur.getString(mCur.getColumnIndex("type")));
			resut.setSound(mCur.getString(mCur.getColumnIndex("sound")));
			resut.setVidu(mCur.getString(mCur.getColumnIndex("vidu")));
			resut.setImportan(mCur.getInt(mCur.getColumnIndex("importan")));
			resut.setLearn(mCur.getInt(mCur.getColumnIndex("learn")));
			resut.setHistory(mCur.getInt(mCur.getColumnIndex("history")));
			mCur.moveToNext();
			list.add(resut);
		}
		return list;
	}
	//Get data voi word
	public Wordp getTestDataWord(String p) 
	{ 
		Wordp resut=new Wordp();
		String sql ="select * from "+"data"+" where "+"word"+" = ?";//"SELECT id, word FROm data"; 
		Cursor mCur = mDb.rawQuery(sql, new String[]{p});

		if (mCur!=null) 
		{ 
			mCur.moveToNext(); 
		} 
		resut.setId(mCur.getInt(mCur.getColumnIndex("id")));
		resut.setWord(mCur.getString(mCur.getColumnIndex("word")));
		resut.setMean(mCur.getString(mCur.getColumnIndex("mean")));
		resut.setType(mCur.getString(mCur.getColumnIndex("type")));
		resut.setSound(mCur.getString(mCur.getColumnIndex("sound")));
		resut.setVidu(mCur.getString(mCur.getColumnIndex("vidu")));
		resut.setLearn(mCur.getInt(mCur.getColumnIndex("learn")));
		resut.setHistory(mCur.getInt(mCur.getColumnIndex("history")));
		resut.setImportan(mCur.getInt(mCur.getColumnIndex("importan")));
		return resut;
	}
	//Get data Important
	public ArrayList<Wordp> getTestImportant(int id) 
	{ 
		ArrayList<Wordp> list =new ArrayList<Wordp>();
		String sql ="select * from "+"data"+" where "+"importan"+" = "+String.valueOf(id);//"SELECT id, word FROm data"; 
		Cursor mCur = mDb.rawQuery(sql,null);
		if (mCur!=null) 
		{ 
			mCur.moveToNext(); 
		} 
		while(mCur.isAfterLast()==false){
			Wordp resut=new Wordp();
			resut.setId(mCur.getInt(mCur.getColumnIndex("id")));
			resut.setWord(mCur.getString(mCur.getColumnIndex("word")));
			resut.setMean(mCur.getString(mCur.getColumnIndex("mean")));
			resut.setType(mCur.getString(mCur.getColumnIndex("type")));
			resut.setSound(mCur.getString(mCur.getColumnIndex("sound")));
			resut.setVidu(mCur.getString(mCur.getColumnIndex("vidu")));
			resut.setLearn(mCur.getInt(mCur.getColumnIndex("learn")));
			resut.setHistory(mCur.getInt(mCur.getColumnIndex("history")));
			resut.setImportan(mCur.getInt(mCur.getColumnIndex("importan")));
			mCur.moveToNext();
			list.add(resut);
		}
		return list;
	}
	// Get data Learn
	public ArrayList<Wordp> getTestLearn(int id) 
	{ 
		ArrayList<Wordp> list=new ArrayList<Wordp>();
		String sql ="select * from "+"data"+" where "+"learn"+" = "+String.valueOf(id);//"SELECT id, word FROm data"; 
		Cursor mCur = mDb.rawQuery(sql,null);
		if (mCur!=null) 
		{ 
			mCur.moveToNext(); 
		} 
		while(mCur.isAfterLast()==false){
			Wordp resut=new Wordp();
			resut.setId(mCur.getInt(mCur.getColumnIndex("id")));
			resut.setWord(mCur.getString(mCur.getColumnIndex("word")));
			resut.setMean(mCur.getString(mCur.getColumnIndex("mean")));
			resut.setType(mCur.getString(mCur.getColumnIndex("type")));
			resut.setSound(mCur.getString(mCur.getColumnIndex("sound")));
			resut.setVidu(mCur.getString(mCur.getColumnIndex("vidu")));
			resut.setLearn(mCur.getInt(mCur.getColumnIndex("learn")));
			resut.setHistory(mCur.getInt(mCur.getColumnIndex("history")));
			resut.setImportan(mCur.getInt(mCur.getColumnIndex("importan")));
			mCur.moveToNext();
			list.add(resut);
		}
		return list;
	}
	//Get data History
	public ArrayList<Wordp> getTestHistory(int id) 
	{ 
		ArrayList<Wordp> list=new ArrayList<Wordp>();
		String sql ="select * from "+"data"+" where "+"history"+" = "+String.valueOf(id);//"SELECT id, word FROm data"; 
		Cursor mCur = mDb.rawQuery(sql,null);
		if (mCur!=null) 
		{ 
			mCur.moveToNext(); 
		} 
		while(mCur.isAfterLast()==false){
			Wordp resut=new Wordp();
			resut.setId(mCur.getInt(mCur.getColumnIndex("id")));
			resut.setWord(mCur.getString(mCur.getColumnIndex("word")));
			resut.setMean(mCur.getString(mCur.getColumnIndex("mean")));
			resut.setType(mCur.getString(mCur.getColumnIndex("type")));
			resut.setSound(mCur.getString(mCur.getColumnIndex("sound")));
			resut.setVidu(mCur.getString(mCur.getColumnIndex("vidu")));
			resut.setLearn(mCur.getInt(mCur.getColumnIndex("learn")));
			resut.setHistory(mCur.getInt(mCur.getColumnIndex("history")));
			resut.setImportan(mCur.getInt(mCur.getColumnIndex("importan")));
			mCur.moveToNext();
			list.add(resut);
		}
		return list;
	}
	//list lấy ra danh sách cách từ
	public ArrayList<String> getTestWord() 
	{ 
		ArrayList<String> list=new ArrayList<String>();
		String sql ="SELECT * FROM data"; 
		Cursor mCur = mDb.rawQuery(sql, null); 
		if (mCur!=null) 
		{ 
			mCur.moveToNext(); 
		} 
		while(mCur.isAfterLast()==false){
			String s="";
			s=""+mCur.getString(mCur.getColumnIndex("word"));
			mCur.moveToNext();
			list.add(s);
		}
		return list;
	}
	//update 1 từ vào database
	public void updatetWord(Wordp p) 
	{ 
		ContentValues values = new ContentValues();
		values.put("id",p.getId());
		values.put("word", p.getWord());
		values.put("type", p.getType());
		values.put("sound",p.getSound());
		values.put("mean",p.getMean());
		values.put("vidu",p.getVidu());
		values.put("learn",p.getLearn());
		values.put("history",p.getHistory());
		values.put("importan",p.getImportan());

		mDb.update("data", values,"id"+" = "+p.getId(), null);
	}
	//get all database from table irregular
	public ArrayList<Regular> getRegular(){
		ArrayList<Regular> list=new ArrayList<Regular>();
		String sql ="SELECT * FROM irregular"; 
		Cursor mCur = mDb.rawQuery(sql, null); 
		if (mCur!=null) 
		{ 
			mCur.moveToNext(); 
		} 
		while(mCur.isAfterLast()==false){
			Regular resut=new Regular();
			resut.setId(mCur.getInt(mCur.getColumnIndex("_id")));
			resut.setInfinitive(mCur.getString(mCur.getColumnIndex("infinitive")));
			resut.setPast_simple(mCur.getString(mCur.getColumnIndex("past_simple")));
			resut.setPast_paticiple(mCur.getString(mCur.getColumnIndex("past_paticiple")));
			resut.setVi_content(mCur.getString(mCur.getColumnIndex("vi_content")));
			resut.setExample(mCur.getString(mCur.getColumnIndex("example")));
			mCur.moveToNext();
			list.add(resut);
		}
		return list;
	}
} 

