package com.panda.org.highwrapper.db.testdb;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.View.OnClickListener;
import com.panda.org.highwrapper.R;
import com.panda.org.highwrapper.db.table.AblumTable;
import com.panda.org.highwrapper.db.table.ImageTable;
import com.panda.org.highwrapper.db.table.VideoTable;

public class DBActivity extends Activity {

    private final static String TAG=DBActivity.class.getSimpleName();
    private Button insert;
    private Button query;
    private Button update;
    private Button delete;

    private Button inserti;
    private Button queryi;
    private Button updatei;
    private Button deletei;

    private Button insertv;
    private Button queryv;
    private Button updatev;
    private Button deletev;

    private Button querymu;

    private Uri mUri;
    private Uri mUrii;
    private Uri mUriv;

    private int cnt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        mUri = Uri
                .parse("content://com.panda.org.highwrapper.db.ablumcontentprovider/albumset");
        mUrii = ImageTable.CONTENT_URI/*Uri
				.parse("content://com.org.main.imagecontentprovider/image")*/;
        mUriv = Uri
                .parse("content://com.panda.org.highwrapper.db.videocontentprovider/records");

        final ContentResolver cr = getContentResolver();

        insert = (Button) findViewById(R.id.insert);
        insert.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ContentValues values = new ContentValues();
                values.put(AblumTable.ABLUM_ID, "10"+cnt++);
                values.put(AblumTable.ABLUM_NAME, "liuzhibao");
                values.put(AblumTable.ABLUM_ADD, "20150210");
                cr.insert(mUri, values);

            }

        });

        inserti = (Button) findViewById(R.id.inserti);
        inserti.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ContentValues values = new ContentValues();
                values.put(ImageTable.ABLUM_ID, "10"+cnt++);
                values.put(ImageTable.IMAGE_NAME, "liuzhibao");
                values.put(ImageTable.IMAGE_PATH, "20150210");
                values.put(ImageTable.MEDIA_TYPE, 4);
                values.put(ImageTable.TYPE, "0000");
                cr.insert(mUrii, values);

            }

        });

        insertv = (Button) findViewById(R.id.insertv);
        insertv.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ContentValues values = new ContentValues();
                values.put(VideoTable.VIDEO_NAME, "sworkd");
                values.put(VideoTable.VIDEO_PATH, "pakkk");
                values.put(VideoTable.MEDIA_TYPE, "4");
                values.put(VideoTable.TYPE, "1111");
                cr.insert(mUriv, values);

            }

        });

        query = (Button) findViewById(R.id.query);
        query.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Cursor cursor=cr.query(mUri, null, null, null, null);
                cursor.moveToPosition(-1);
                while(cursor.moveToNext()){

                    String ablumname=cursor.getString(cursor.getColumnIndex(AblumTable.ABLUM_NAME));

                    String ablumid=cursor.getString(cursor.getColumnIndex(AblumTable.ABLUM_ID));

                    Log.d(TAG,"ablumname : "+ablumname+" ablumid : "+ablumid);

                }

                cursor.close();

            }

        });

        queryi = (Button) findViewById(R.id.queryi);
        queryi.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Cursor cursor=cr.query(mUrii, null, null, null, null);
                cursor.moveToPosition(-1);
                while(cursor.moveToNext()){

                    String ablumname=cursor.getString(cursor.getColumnIndex(ImageTable.IMAGE_NAME));

                    String ablumid=cursor.getString(cursor.getColumnIndex(ImageTable.ABLUM_ID));

                    Log.d(TAG,"ablumname : "+ablumname+" ablumid : "+ablumid);

                }

                cursor.close();

            }

        });

        queryv = (Button) findViewById(R.id.queryv);
        queryv.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Cursor cursor=cr.query(mUriv, null, null, null, null);
                cursor.moveToPosition(-1);
                while(cursor.moveToNext()){

                    String ablumname=cursor.getString(cursor.getColumnIndex(VideoTable.VIDEO_NAME));

                    String ablumid=cursor.getString(cursor.getColumnIndex(VideoTable.VIDEO_PATH));

                    Log.d(TAG,"ablumname : "+ablumname+" ablumid : "+ablumid);

                }

                cursor.close();

            }

        });

        update=(Button)findViewById(R.id.update);
        update.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ContentValues values = new ContentValues();
                values.put(AblumTable.ABLUM_ID, "102");
                values.put(AblumTable.ABLUM_NAME, "meettings");
                values.put(AblumTable.ABLUM_ADD, "20161203");

                String selects=AblumTable.ABLUM_ID+"=?";
                String selectargs[]=new String[]{
                        "101"
                };

                cr.update(mUri, values, selects, selectargs);

            }

        });

        updatei=(Button)findViewById(R.id.updatei);
        updatei.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ContentValues values = new ContentValues();
                values.put(ImageTable.ABLUM_ID, "102");
                values.put(ImageTable.IMAGE_NAME, "meettings");
                values.put(ImageTable.IMAGE_PATH, "333");

                String selects=ImageTable.ABLUM_ID+"=?";
                String selectargs[]=new String[]{
                        "101"
                };

                cr.update(mUrii, values, selects, selectargs);

            }

        });

        updatev=(Button)findViewById(R.id.updatev);
        updatev.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ContentValues values = new ContentValues();
                values.put(VideoTable.VIDEO_NAME, "102");
                values.put(VideoTable.VIDEO_PATH, "meettings");
                values.put(VideoTable.MEDIA_TYPE, "4");
                values.put(VideoTable.TYPE, "333");

                String selects=VideoTable.TYPE+"=?";
                String selectargs[]=new String[]{
                        "4"
                };

                cr.update(mUriv, values, selects, selectargs);

            }

        });

        delete=(Button)findViewById(R.id.delete);
        delete.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String selects= AblumTable.ABLUM_ID+"=?";
                String selectargs[]=new String[]{
                        "102"
                };
                cr.delete(mUri, selects, selectargs);

            }

        });

        deletei=(Button)findViewById(R.id.deletei);
        deletei.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String selects=ImageTable.ABLUM_ID+"=?";
                String selectargs[]=new String[]{
                        "102"
                };
                cr.delete(mUrii, selects, selectargs);

            }

        });

        deletev=(Button)findViewById(R.id.deletev);
        deletev.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String selects=VideoTable.VIDEO_NAME+"=?";
                String selectargs[]=new String[]{
                        "102"
                };
                cr.delete(mUriv, selects, selectargs);

            }

        });

        querymu=(Button)findViewById(R.id.querymu);
        querymu.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String selection="title like '%s%'";
                Cursor cursor=cr.query(VideoTable.CONTENT_URI, null, selection, null, "id");

                cursor.moveToPosition(-1);
                while(cursor.moveToNext()){

                    String name=cursor.getString(cursor.getColumnIndex(VideoTable.VIDEO_NAME));
                    System.out.println("video name : "+name);

                }


            }

        });


        mHandlerOberver = new HandlerOberver();
        mUriContentOberver=new UriContentOberver(this,mHandlerOberver);



    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        getContentResolver().registerContentObserver(mUri, true, mUriContentOberver);
        getContentResolver().registerContentObserver(mUrii, true, mUriContentOberver);
        getContentResolver().registerContentObserver(mUriv, true, mUriContentOberver);

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

        getContentResolver().unregisterContentObserver(mUriContentOberver);

    }

    private HandlerOberver mHandlerOberver;
    public class HandlerOberver extends Handler {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            // update app ui information
            Toast.makeText(DBActivity.this, "update app ui here .", Toast.LENGTH_SHORT).show();

        }

    }

    private UriContentOberver mUriContentOberver;
    public class UriContentOberver extends ContentObserver {

        private Handler mHandler;
        private Context mContext;

        public UriContentOberver(Context context, Handler handler) {
            super(handler);
            mHandler = handler;
            mContext = context;
        }

        @Override
        public void onChange(boolean selfChange) {
            // TODO Auto-generated method stub
            super.onChange(selfChange);

            Toast.makeText(mContext, "onChange gt 01.", Toast.LENGTH_SHORT).show();

            mHandler.obtainMessage().sendToTarget();
        }

    }
}
