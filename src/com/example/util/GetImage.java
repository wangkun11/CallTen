package com.example.util;

import java.io.InputStream;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;

public class GetImage {
	public static Bitmap get_image(Context context, String number) {

		// 获得Uri
		Uri uriNumber2Contacts = Uri.parse("content://com.android.contacts/" + "data/phones/filter/" + number);
		// 查询Uri，返回数据集
		Cursor cursorCantacts = context.getContentResolver().query(uriNumber2Contacts, null, null, null, null);
		// 如果该联系人存在
		if (cursorCantacts.getCount() > 0) {
			// 移动到第一条数据
			cursorCantacts.moveToFirst();
			// 获得该联系人的contact_id
			Long contactID = cursorCantacts.getLong(cursorCantacts.getColumnIndex("contact_id"));
			// 获得contact_id的Uri
			Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactID);
			// 打开头像图片的InputStream
			InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(),uri);
			// 从InputStream获得bitmap
			return BitmapFactory.decodeStream(input);
		}else {
			return null;
		}
		
	}
}
