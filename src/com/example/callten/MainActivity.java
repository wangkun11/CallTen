package com.example.callten;

import java.util.ArrayList;
import java.util.List;

import com.example.util.CalculateRate;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	ListView listView;
	List<RecordEntity> recordList = new ArrayList<RecordEntity>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		listView=(ListView) findViewById(R.id.rateRecord);
		getCallRecords();
		
		
		listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getRecordString()));
		
		
	}
	private ArrayList<String> getRecordString() {
		ArrayList<String> records=new ArrayList<String>();
		for (int i = 0; i < recordList.size(); i++) {
			if (recordList.get(i).name.equals("陌生人")) {
				continue;
			}
			records.add(recordList.get(i).toString());			
		}
		return records;
	}
	private void getCallRecords() {
		ContentResolver contentResolver = this.getContentResolver();   
		Cursor cursor=null;
	    try {  
	       cursor = contentResolver.query(  
	                // CallLog.Calls.CONTENT_URI, Columns, null,  
	                // null,CallLog.Calls.DATE+" desc");  
	                CallLog.Calls.CONTENT_URI, null, null, null,  
	                CallLog.Calls.DATE + " desc");  
	        if (cursor == null) {
	        	return ;
	        }  
	         
	        for (int i = 0; i < 500; i++) {
	        	cursor.moveToNext();
	        	RecordEntity record = new RecordEntity(); 
	            record.name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
	            if (record.name==null) {
	            	record.name="陌生人";
				}
	            record.number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));  	            
	            record.duration = cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DURATION));  
	            record.count=1;
	            // Log.d("通话记录：", record.toString());  
  
	            boolean flag=false;
	            for (int j = 0; j < recordList.size(); j++) {
					if (record.name.equals(recordList.get(j).name)) {
						//姓名相同则，频次加1，总通话时间累加
						recordList.get(j).count++;
						recordList.get(j).duration+=record.duration;
						flag=true;
						break;
					}
				}
	            if (!flag) {
	            	recordList.add(record);  
				}          			
			}
	        CalculateRate.setRate(recordList);	        
	        for (int i = 0; i < recordList.size(); i++) {
				Log.d("通话记录：", recordList.get(i).toString());
			}
	    } finally {  
	        if (cursor != null) {  
	            cursor.close();  
	        }  
	    }
	}
	
}
