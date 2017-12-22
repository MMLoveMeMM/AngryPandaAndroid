package com.panda.org.highwrapper.db.table;

import android.net.Uri;

public class VideoTable {

	public static final String TABLE_NAME = "records";
	public static final String AUTHORITY = "com.org.main.videocontentprovider";
	public static final Uri CONTENT_URI = Uri.parse("content://"
			+ AUTHORITY + "/"+TABLE_NAME);

	public static final String CONTENT_TYPE = "vnd.Android.cursor.dir/vnd.hx.title";
	public static final String CONTENT_ITEM_TYPE = "vnd.Android.cursor.item/vnd.hx.title";
	
	public static final String DEFAULT_SORT_ORDER = "created DESC";
	
	public static final int VERSION = 1;

	public static final String VIDEO_NAME = "title";
	public static final String VIDEO_PATH="path";
	public static final String MEDIA_TYPE = "media_type";
	public static final String TYPE="type";
	
	public static final String CREATE_RECORDS_TABLE_SQL = "create table "+TABLE_NAME+" ("
            + "id integer primary key autoincrement, "
            + VIDEO_NAME+" varchar(100), "
            + VIDEO_PATH+" varchar(200), "
            + TYPE+" varchar(20),"
            + MEDIA_TYPE+" integer)";
	
}
