package com.example.callten;
public class RecordEntity{
    public String name;  //����
    public String number; //�ֻ���
    public long duration; //ʱ��
	
    public int count; //����
    public int rate; //�ȶ�
	@Override
	public String toString() {
		return "["+name+"," + number+","  + duration+"," + count+"," +rate+ "]";
	}
}