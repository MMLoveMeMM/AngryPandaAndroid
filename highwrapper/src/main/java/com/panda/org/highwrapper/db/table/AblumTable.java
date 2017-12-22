package com.panda.org.highwrapper.db.table;

import android.net.Uri;
import android.provider.BaseColumns;

public class AblumTable {

		public static final String TABLE_NAME = "albumset";
		public static final String AUTHORITY = "com.org.main.ablumcontentprovider";
		public static final Uri CONTENT_URI = Uri.parse("content://"
				+ AUTHORITY + "/"+TABLE_NAME);

		public static final String CONTENT_TYPE = "vnd.Android.cursor.dir/vnd.hx.album_name";
		public static final String CONTENT_ITEM_TYPE = "vnd.Android.cursor.item/vnd.hx.album_name";
		
		public static final String DEFAULT_SORT_ORDER = "created DESC";
		
		public static final int VERSION = 1;

		public static final String ABLUM_NAME = "album_name";
		public static final String ABLUM_ID = "album_id";
		public static final String ABLUM_ADD = "album_create_time";
		
		public static final String CREATE_ABLUM_TABLE_SQL="create table "+TABLE_NAME+"("
				+"id integer primary key autoincrement, "
				+ ABLUM_NAME+" varchar(100), "
	            + ABLUM_ID+" integer, "
	            + ABLUM_ADD+" varchar(100))";

}
