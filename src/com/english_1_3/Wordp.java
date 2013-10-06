package com.english_1_3;

public class Wordp {
	private String word,mean,sound,type,vidu;
	private int id;
	private int learn,history,importan;
	public Wordp(String word, String mean, String sound, String type,
			int learn, int history, int importan,String vd) {
		super();
		this.word = word;
		this.mean = mean;
		this.sound = sound;
		this.type = type;
		this.learn = learn;
		this.history = history;
		this.importan = importan;
		this.vidu=vd;
	}
	public Wordp(){};
	
	public String getVidu() {
		return vidu;
	}
	public void setVidu(String vidu) {
		this.vidu = vidu;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getMean() {
		return mean;
	}
	public void setMean(String mean) {
		this.mean = mean;
	}
	public String getSound() {
		return sound;
	}
	public void setSound(String sound) {
		this.sound = sound;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLearn() {
		return learn;
	}
	public void setLearn(int learn) {
		this.learn = learn;
	}
	public int getHistory() {
		return history;
	}
	public void setHistory(int history) {
		this.history = history;
	}
	public int getImportan() {
		return importan;
	}
	public void setImportan(int importan) {
		this.importan = importan;
	}
	
}
