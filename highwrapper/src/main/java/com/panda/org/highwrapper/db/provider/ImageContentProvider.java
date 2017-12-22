package com.panda.org.highwrapper.db.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

import com.panda.org.highwrapper.db.helper.AblumDatabaseHelper;
import com.panda.org.highwrapper.db.table.ImageTable;

public class ImageContentProvider extends ContentProvider {

	private static final int TABLES = 1;
	private static final int TABLE_ID = 2;
	private static UriMatcher sUriMatcher = null;

	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		sUriMatcher.addURI(ImageTable.AUTHORITY, ImageTable.TABLE_NAME, TABLES);
		sUriMatcher.addURI(ImageTable.AUTHORITY, ImageTable.TABLE_NAME + "/#",
				TABLE_ID);

	};
	
	private AblumDatabaseHelper mOpenHelper;
	
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
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();// ��һ������SQL��ѯ���ĸ�����
		switch (sUriMatcher.match(uri)) {
		case TABLES:
			qb.setTables(ImageTable.TABLE_NAME);
			break;
		case TABLE_ID:
			qb.setTables(ImageTable.TABLE_NAME);
			qb.appendWhere(ImageTable.ABLUM_ID + "="
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
			return ImageTable.CONTENT_TYPE;
		case TABLE_ID:
			return ImageTable.CONTENT_ITEM_TYPE;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues initialValues) {
		// TODO Auto-generated method stub
		ContentValues values;
		if (initialValues != null) {
			Log.i("##not null", "initialValues");
			values = new ContentValues(initialValues);
		} else {
			Log.i("##null", "initialValues");
			values = new ContentValues();
		}

		if (values.containsKey(ImageTable.ABLUM_ID) == false) {
			values.put(ImageTable.ABLUM_ID, "");
		}
		if (values.containsKey(ImageTable.IMAGE_NAME) == false) {
			values.put(ImageTable.IMAGE_NAME, "title");
		}
		if (values.containsKey(ImageTable.IMAGE_PATH) == false) {
			values.put(ImageTable.IMAGE_PATH, "path");
		}
		if (values.containsKey(ImageTable.TYPE) == false) {
			values.put(ImageTable.TYPE, "type");
		}
		if (values.containsKey(ImageTable.MEDIA_TYPE) == false) {
			values.put(ImageTable.MEDIA_TYPE, "4");
		}

		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		long rowId = db.insert(ImageTable.TABLE_NAME, ImageTable.IMAGE_NAME/*null*/,
				values);
		getContext().getContentResolver().notifyChange(uri, null);
		
		if (rowId > 0) {
			Uri myUri = ContentUris.withAppendedId(ImageTable.CONTENT_URI,
					rowId);

			return myUri;
		}

		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();

		int ret=db.delete(ImageTable.TABLE_NAME, selection, selectionArgs);
		getContext().getContentResolver().notifyChange(uri, null);

		return ret;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int ret = db.update(ImageTable.TABLE_NAME, values, selection,
				selectionArgs);
		return ret;
	}

}
