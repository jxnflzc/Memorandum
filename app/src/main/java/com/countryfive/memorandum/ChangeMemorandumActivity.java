package com.countryfive.memorandum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ChangeMemorandumActivity extends AppCompatActivity {
    private EditText changeMemorandumName, changeMemorandumInfo;
    private TextView txtNewDate;
    private Button btnChange, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_memorandum);

        changeMemorandumName = findViewById(R.id.EdtMemorandumNameChange);
        changeMemorandumInfo = findViewById(R.id.EdtMemorandumInfoChange);
        btnChange = findViewById(R.id.BtnChange);
        btnDelete = findViewById(R.id.BtnDelete);
        txtNewDate = findViewById(R.id.TxtNewDate);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
        //获取当前时间
        Date curDate = new Date(System.currentTimeMillis());
        final String newDate = dateFormatter.format(curDate);
        final String newTime = timeFormatter.format(curDate);

        changeMemorandumInfo.setBackgroundResource(R.color.colorGray);

        getInformation();

        Intent intent = getIntent();
        final String id = String.valueOf(intent.getIntExtra("id", 1));
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Memorandum memorandum = new Memorandum();
                //修改备忘录名称
                if(!TextUtils.isEmpty(changeMemorandumName.getText()))
                    memorandum.setMemorandumName(changeMemorandumName.getText().toString());
                else
                    memorandum.setMemorandumName(null);
                //修改备忘录内容
                if(!TextUtils.isEmpty(changeMemorandumInfo.getText()))
                    memorandum.setMemorandumInfo(changeMemorandumInfo.getText().toString());
                else
                    memorandum.setMemorandumInfo(null);
                //修改备忘录保存时间
                memorandum.setSaveDate(newDate);
                memorandum.setSaveTime(newTime);

                memorandum.updateAll("id = ?", id);
                Toast.makeText(ChangeMemorandumActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();

                Intent gotoMainActivity = new Intent(ChangeMemorandumActivity.this, MainActivity.class);
                gotoMainActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
                startActivity(gotoMainActivity);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSupport.deleteAll(Memorandum.class, "id = ?", id);
                Toast.makeText(ChangeMemorandumActivity.this, "删除成功！", Toast.LENGTH_SHORT).show();

                Intent gotoMainActivity = new Intent(ChangeMemorandumActivity.this, MainActivity.class);
                gotoMainActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
                startActivity(gotoMainActivity);
            }
        });
    }

    protected void getInformation(){
        Intent intent = getIntent();
        String id = String.valueOf(intent.getIntExtra("id", 1));

        List<Memorandum> memorandums = DataSupport.where("id = ?", id).find(Memorandum.class);

        changeMemorandumName.setText(memorandums.get(0).getMemorandumName());
        changeMemorandumInfo.setText(memorandums.get(0).getMemorandumInfo());
        txtNewDate.setText(memorandums.get(0).getSaveDate() + " " + memorandums.get(0).getSaveTime());
    }
}
