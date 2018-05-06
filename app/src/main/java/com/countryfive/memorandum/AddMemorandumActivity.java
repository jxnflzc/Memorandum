package com.countryfive.memorandum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddMemorandumActivity extends AppCompatActivity {
    private EditText memorandumName,memorandumInfo;
    private TextView txtDate;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memorandum);

        memorandumInfo = findViewById(R.id.EdtMemorandumInfo);
        memorandumName = findViewById(R.id.EdtMemorandumName);
        btnSave = findViewById(R.id.BtnSave);
        txtDate = findViewById(R.id.TxtDate);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
        //获取当前时间
        Date curDate = new Date(System.currentTimeMillis());
        final String date = dateFormatter.format(curDate);
        final String time = timeFormatter.format(curDate);

        txtDate.setText(date + " " + time);

        memorandumInfo.setBackgroundResource(R.color.colorGray);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Memorandum memorandum = new Memorandum();
                //存储备忘录名称
                if(!TextUtils.isEmpty(memorandumName.getText()))
                    memorandum.setMemorandumName(memorandumName.getText().toString());
                else
                    memorandum.setMemorandumName("无名称");
                //存储备忘录内容
                if(!TextUtils.isEmpty(memorandumInfo.getText()))
                    memorandum.setMemorandumInfo(memorandumInfo.getText().toString());
                else
                    memorandum.setMemorandumInfo(null);
                //存储备忘录保存时间
                memorandum.setSaveDate(date);
                memorandum.setSaveTime(time);

                memorandum.save();
                Toast.makeText(AddMemorandumActivity.this, "保存成功！", Toast.LENGTH_SHORT).show();

                Intent gotoMainActivity = new Intent(AddMemorandumActivity.this, MainActivity.class);
                gotoMainActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
                startActivity(gotoMainActivity);
            }
        });
    }
}
