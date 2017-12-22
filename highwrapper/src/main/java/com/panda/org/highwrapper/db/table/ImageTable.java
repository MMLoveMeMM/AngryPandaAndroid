package com.panda.org.highwrapper.db.table;

import android.net.Uri;

public class ImageTable {

	public static final String TABLE_NAME = "image";
	public static final String AUTHORITY = "com.panda.org.highwrapper.db.imagecontentprovider";
	public static final Uri CONTENT_URI = Uri.parse("content://"
			+ AUTHORITY + "/"+TABLE_NAME);

	public static final String CONTENT_TYPE = "vnd.Android.cursor.dir/vnd.hx.name";
	public static final String CONTENT_ITEM_TYPE = "vnd.Android.cursor.item/vnd.hx.name";
	
	public static final String DEFAULT_SORT_ORDER = "created DESC";
	
	public static final int VERSION = 1;

	public static final String IMAGE_NAME = "name";
	public static final String IMAGE_PATH="path";
	public static final String ABLUM_ID = "album_id";
	public static final String MEDIA_TYPE = "media_type";
	public static final String TYPE="type";
	
	public static final String CREATE_IMAGE_TABLE_SQL = "create table "+TABLE_NAME+"("
			+"id integer primary key autoincrement, "
			+ ABLUM_ID+" integer, "
			+ IMAGE_NAME+" varchar(100), "
            + IMAGE_PATH+" varchar(200), "
            + MEDIA_TYPE+" integer, "
            + TYPE+" varchar(20))";
	
}
