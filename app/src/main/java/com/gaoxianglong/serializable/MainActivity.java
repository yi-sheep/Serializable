package com.gaoxianglong.serializable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextName,mEditTextAge,mEditTextMath,mEditTextEnglish,mEditTextChinese;
    private Button mButtonSave,mButtonLoad;
    private TextView mTextViewGrade;
    private static final String FILE_NAME = "myfile.data";
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        event();
    }

    private void event() {
        mButtonSave.setOnClickListener(v -> {
            int math = Integer.parseInt(mEditTextMath.getText().toString().trim());
            int english = Integer.parseInt(mEditTextEnglish.getText().toString().trim());
            int chinese = Integer.parseInt(mEditTextChinese.getText().toString().trim());
            Score score = new Score(math, english, chinese);
            String name = mEditTextName.getText().toString().trim();
            int age = Integer.parseInt(mEditTextAge.getText().toString().trim());
            Student student = new Student(name, age, score);
            // 序列化写入到文件中
            try {
                ObjectOutputStream oos = new ObjectOutputStream(openFileOutput(FILE_NAME, MODE_PRIVATE));
                oos.writeObject(student);
                oos.flush(); // 刷流，清空缓存
                oos.close(); // 关闭
                Toast.makeText(this,"写入成功",Toast.LENGTH_SHORT).show();
                mEditTextName.getText().clear();
                mEditTextAge.getText().clear();
                mEditTextMath.getText().clear();
                mEditTextEnglish.getText().clear();
                mEditTextChinese.getText().clear();
                mTextViewGrade.setText("一");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        mButtonLoad.setOnClickListener(v -> {
            try {
                ObjectInputStream ois = new ObjectInputStream(openFileInput(FILE_NAME));
                Student student = (Student) ois.readObject();
                Score score = student.getScore();
                mEditTextName.setText(student.getName());
                mEditTextAge.setText(String.valueOf(student.getAge()));
                mEditTextMath.setText(String.valueOf(score.getMath()));
                mEditTextEnglish.setText(String.valueOf(score.getEnglish()));
                mEditTextChinese.setText(String.valueOf(score.getChinese()));
                mTextViewGrade.setText(score.getGrade());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void init() {
        mEditTextName = findViewById(R.id.editTextName);
        mEditTextAge = findViewById(R.id.editTextAge);
        mEditTextMath = findViewById(R.id.editTextMath);
        mEditTextEnglish = findViewById(R.id.editTextEnglish);
        mEditTextChinese = findViewById(R.id.editTextChinese);
        mButtonSave = findViewById(R.id.buttonSave);
        mButtonLoad = findViewById(R.id.buttonLoad);
        mTextViewGrade = findViewById(R.id.textViewGrade);
    }
}
