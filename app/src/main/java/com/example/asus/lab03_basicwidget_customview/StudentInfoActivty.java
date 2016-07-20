package com.example.asus.lab03_basicwidget_customview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Asus on 7/20/2016.
 */
public class StudentInfoActivty extends Activity {
    TextView tvInfo;
    String nameStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_student_info_activity);
        tvInfo=(TextView)findViewById(R.id.tvInfoName);
        getDataFromIntent();
        tvInfo.setText(nameStudent+"");
    }

    private void getDataFromIntent() {
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra(MainActivity.DATA);
        nameStudent=bundle.getString(MainActivity.NAME);
    }
}
