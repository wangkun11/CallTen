package com.example.callten;
public class RecordEntity{
    public String name;  //姓名
    public String number; //手机号
    public long duration; //时长
	
    public int count; //次数
    public int rate; //热度
	@Override
	public String toString() {
		return "["+name+"," + number+","  + duration+"," + count+"," +rate+ "]";
	}
}