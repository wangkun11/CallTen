package com.example.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.example.callten.RecordEntity;

public class CalculateRate {
	public static void setRate(List<RecordEntity> recordList){
		int totalCount=0;
		int totalDuration=0;
		for (int i = 0; i < recordList.size(); i++) {
			totalCount+=recordList.get(i).count;
			totalDuration+=recordList.get(i).duration;
		}
		int aveDuration=totalDuration/totalCount;
		//rate=个人的总通话时间+个人的总通话次数*整体平均通话时间
		for (int i = 0; i < recordList.size(); i++) {
			recordList.get(i).rate=(int)recordList.get(i).duration+recordList.get(i).count*aveDuration;
		}
		Comparator<RecordEntity> comparator = new Comparator<RecordEntity>() {
			public int compare(RecordEntity r1, RecordEntity r2) {
				// 先排热度
				if (r1.rate != r2.rate) {
					return r2.rate-r1.rate;
				} else {
					return r2.count-r1.rate;
				}
			}
		};
		Collections.sort(recordList,comparator);
	}
}
