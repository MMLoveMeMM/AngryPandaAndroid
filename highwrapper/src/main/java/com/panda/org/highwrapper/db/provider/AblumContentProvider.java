package com.panda.org.highwrapper.db.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import java.util.HashMap;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.panda.org.highwrapper.db.helper.AblumDatabaseHelper;
import com.panda.org.highwrapper.db.table.AblumTable;

public class AblumContentProvider extends ContentProvider {

	private static final int TABLES = 1;
	private static final int TABLE_ID = 2;
	private static UriMatcher sUriMatcher = null;

	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		sUriMatcher.addURI(AblumTable.AUTHORITY, AblumTable.TABLE_NAME, TABLES);
		sUriMatcher.addURI(AblumTable.AUTHORITY, AblumTable.TABLE_NAME + "/#",
				TABLE_ID);

	};

	AblumDatabaseHelper mOpenHelper;

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		mOpenHelper = new AblumDatabaseHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		switch (sUriMatcher.match(uri)) {
		case TABLES:
			qb.setTables(AblumTable.TABLE_NAME);
			break;
		case TABLE_ID:
			qb.setTables(AblumTable.TABLE_NAME);
			qb.appendWhere(AblumTable.ABLUM_ID + "="
					+ uri.getPathSegments().get(1));
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		Cursor c = qb.query(db, projection, selection, selectionArgs, null,
				null, sortOrder);

		return c;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		switch (sUriMatcher.match(uri)) {
		case TABLES:
			return AblumTable.CONTENT_TYPE;
		case TABLE_ID:
			return AblumTable.CONTENT_ITEM_TYPE;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues initialValues) {
		// TODO Auto-generated method stub
		// ��ʱ�е㷱��,�����
		ContentValues values;
		if (initialValues != null) {
			Log.i("##not null", "initialValues");
			values = new ContentValues(initialValues);
		} else {
			Log.i("##null", "initialValues");
			values = new ContentValues();
		}

		if (values.containsKey(AblumTable.ABLUM_ID) == false) {
			values.put(AblumTable.ABLUM_ID, "");
		}
		if (values.containsKey(AblumTable.ABLUM_NAME) == false) {
			values.put(AblumTable.ABLUM_NAME, "title");
		}
		if (values.containsKey(AblumTable.ABLUM_ADD) == false) {
			values.put(AblumTable.ABLUM_ADD, "time");
		}

		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		long rowId = db.insert(AblumTable.TABLE_NAME, AblumTable.ABLUM_NAME,
				values);
		if (rowId > 0) {
			Uri myUri = ContentUris.withAppendedId(AblumTable.CONTENT_URI,
					rowId);

			getContext().getContentResolver().notifyChange(uri, null);

			return myUri;// ����ֵΪһ��uri
		}

		return null;

	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();

		int ret=db.delete(AblumTable.TABLE_NAME, selection, selectionArgs);
		getContext().getContentResolver().notifyChange(uri, null);

		return ret;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int ret = db.update(AblumTable.TABLE_NAME, values, selection,
				selectionArgs);
		return ret;
	}

}
