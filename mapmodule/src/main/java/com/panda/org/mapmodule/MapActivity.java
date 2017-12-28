package com.panda.org.mapmodule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MapActivity extends Activity {

    private Button mOpenBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mOpenBtn=(Button)findViewById(R.id.open);
        mOpenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MapActivity.this,OpenMapActivity.class);
                startActivity(in);
            }
        });

    }
}
