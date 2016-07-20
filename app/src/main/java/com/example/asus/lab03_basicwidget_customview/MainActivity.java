package com.example.asus.lab03_basicwidget_customview;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String NAME="name";
    public static final String IQ="iq";
    public static final String GENDER="gender";
    public static final String SPECIAL="special";
    public static final String DOBIRTH="dob";
    public static final String FACEBOOK="fb";
    public static final String DATA="data";
    EditText editName;
    SeekBar seekBarIQ;
    RadioGroup radioGender;
    RadioButton radioMale,radioFemale;
    CheckBox checkBoxSpecial;
    TextView tvDOB;
    ImageView imageViewAvatar;
    Switch switchFacebook;
    Button btSubmit;
    String name;
    int IQvalue;
    boolean isMale=true;
    boolean isSpecial=false;
    String DOB;
    boolean isHaveFacebook=true;
    int year,month,day;
    DatePickerDialog.OnDateSetListener onDateSetListener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
            year=selectedYear;
            month=selectedMonth;
            day=selectedDay;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
        setCurrentDate();
        registerEvent();
        imageViewAvatar.setImageResource(R.drawable.anh_techkid);
        getDataFromForm();

    }

    private void setCurrentDate() {
        Calendar calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DATE);
        tvDOB.setText(day+"/"+month+"/"+year);
    }

    private void getDataFromForm() {
        name=editName.getText()+"";
        IQvalue=seekBarIQ.getProgress();
        isMale=radioMale.isChecked();
        isSpecial=checkBoxSpecial.isChecked();
        DOB=tvDOB.getText()+"";
        isHaveFacebook=switchFacebook.isChecked();
    }

    private void registerEvent() {
        radioGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.radio_male:
                        isMale=true;
                        break;
                    case R.id.radio_female:
                        isMale=false;
                        break;
                }
            }
        });
        checkBoxSpecial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                checkBoxSpecial.setChecked(isChecked);
                isSpecial=isChecked;
            }
        });
        btSubmit.setOnClickListener(this);
        tvDOB.setOnClickListener(this);
    }

    private void setupView() {
        editName=(EditText)findViewById(R.id.edittext_name);
        seekBarIQ=(SeekBar)findViewById(R.id.seekbar_IQ);
        radioGender=(RadioGroup)findViewById(R.id.radio_group_gender);
        radioMale=(RadioButton)findViewById(R.id.radio_male);
        radioFemale=(RadioButton)findViewById(R.id.radio_female);
        checkBoxSpecial=(CheckBox)findViewById(R.id.checkbox_special);
        tvDOB=(TextView)findViewById(R.id.textview_DOB);
        imageViewAvatar=(ImageView)findViewById(R.id.image_avatar);
        switchFacebook=(Switch)findViewById(R.id.switch_facebook);
        btSubmit=(Button)findViewById(R.id.button_submit);
        seekBarIQ.setMax(170);
        btSubmit.setBackgroundResource(R.drawable.custom_button);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch(id){
            case R.id.textview_DOB:
                onCreateDialog().show();
                tvDOB.setText(day+"/"+month+"/"+year);
                break;
            case R.id.button_submit:
                sendDataToDetailActivity();
                break;
        }

    }
    private void sendDataToDetailActivity(){
        getDataFromForm();
        Intent intent=new Intent(MainActivity.this,StudentInfoActivty.class);
        Bundle argument=new Bundle();
        argument.putString(NAME,name);
        argument.putInt(IQ,IQvalue);
        argument.putBoolean(GENDER,isMale);
        argument.putBoolean(SPECIAL,isSpecial);
        argument.putString(DOBIRTH,DOB);
        argument.putBoolean(FACEBOOK,isHaveFacebook);
        intent.putExtra(DATA,argument);
        startActivity(intent);
    }

    DatePickerDialog onCreateDialog(){
        return new DatePickerDialog(MainActivity.this,onDateSetListener,year,month,day);
    }
}
