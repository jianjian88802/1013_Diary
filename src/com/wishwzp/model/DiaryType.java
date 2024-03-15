package com.wishwzp.model;

public class DiaryType {

	private int diaryTypeId;
	private String typeName;
	private int diaryCount;
	
	public DiaryType() {
		super();
	}
	public DiaryType(String typeName) {
		super();
		this.typeName = typeName;
	}
	public int getDiaryTypeId() {
		return diaryTypeId;
	}
	public void setDiaryTypeId(int diaryTypeId) {
		this.diaryTypeId = diaryTypeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getDiaryCount() {
		return diaryCount;
	}
	public void setDiaryCount(int diaryCount) {
		this.diaryCount = diaryCount;
	}
	
	
}
