package com.panda.org.highwrapper.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.panda.org.highwrapper.db.table.AblumTable;
import com.panda.org.highwrapper.db.table.ImageTable;
import com.panda.org.highwrapper.db.table.VideoTable;

public class AblumDatabaseHelper extends SQLiteOpenHelper {

	public static String DATABASE_NAME = "gallery.db";
	public static final Integer VERSION = 1;
	
	public AblumDatabaseHelper(Context context){
		super(context, DATABASE_NAME, null, VERSION);
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		db.execSQL(AblumTable.CREATE_ABLUM_TABLE_SQL);
		db.execSQL(ImageTable.CREATE_IMAGE_TABLE_SQL);
		db.execSQL(VideoTable.CREATE_RECORDS_TABLE_SQL);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
