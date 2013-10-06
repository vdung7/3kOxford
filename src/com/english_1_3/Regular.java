package com.english_1_3;

public class Regular {
	private String Infinitive,past_simple,past_paticiple,vi_content,example;
	private int id;
	public Regular(){
		
	}
	public Regular(String infinitive, String past_simple,
			String past_paticiple, String vi_content, String example) {
		super();
		Infinitive = infinitive;
		this.past_simple = past_simple;
		this.past_paticiple = past_paticiple;
		this.vi_content = vi_content;
		this.example = example;
	}

	public String getInfinitive() {
		return Infinitive;
	}

	public void setInfinitive(String infinitive) {
		Infinitive = infinitive;
	}

	public String getPast_simple() {
		return past_simple;
	}

	public void setPast_simple(String past_simple) {
		this.past_simple = past_simple;
	}

	public String getPast_paticiple() {
		return past_paticiple;
	}

	public void setPast_paticiple(String past_paticiple) {
		this.past_paticiple = past_paticiple;
	}

	public String getVi_content() {
		return vi_content;
	}

	public void setVi_content(String vi_content) {
		this.vi_content = vi_content;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
