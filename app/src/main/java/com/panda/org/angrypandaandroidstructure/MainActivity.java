package com.panda.org.angrypandaandroidstructure;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.panda.org.angrypandaandroidstructure.data.Panda;
import com.panda.org.angrypandaandroidstructure.databinding.ActivityMainBinding;
import com.panda.org.angrypandaandroidstructure.retrofit.RetrofitActivity;
import com.panda.org.angrypandaandroidstructure.vm.VmModel;
import com.panda.org.highwrapper.ui.home.HomeActivity;
import com.panda.org.mediumwrapper.MediumActivity;
import com.panda.org.mediumwrapper.lifecycle.LifeRecyleActivity;


public class MainActivity extends Activity {

    private ActivityMainBinding binding;

    private VmModel vmModel;

    private Button mBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        vmModel=new VmModel(MainActivity.this,new Panda("angrypanda"));


        binding.setVm(vmModel);

        mBtn=(Button)findViewById(R.id.start);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(MainActivity.this,HomeActivity.class/*LifeRecyleActivity.class*//*MediumActivity.class*//*RetrofitActivity.class*/);
                startActivity(in);

            }
        });

    }

    public ActivityMainBinding getBinding(){
        return binding;
    }

}
