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

		// ���Uri
		Uri uriNumber2Contacts = Uri.parse("content://com.android.contacts/" + "data/phones/filter/" + number);
		// ��ѯUri���������ݼ�
		Cursor cursorCantacts = context.getContentResolver().query(uriNumber2Contacts, null, null, null, null);
		// �������ϵ�˴���
		if (cursorCantacts.getCount() > 0) {
			// �ƶ�����һ������
			cursorCantacts.moveToFirst();
			// ��ø���ϵ�˵�contact_id
			Long contactID = cursorCantacts.getLong(cursorCantacts.getColumnIndex("contact_id"));
			// ���contact_id��Uri
			Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactID);
			// ��ͷ��ͼƬ��InputStream
			InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(),uri);
			// ��InputStream���bitmap
			return BitmapFactory.decodeStream(input);
		}else {
			return null;
		}
		
	}
}
