package com.example.animalhospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class CallActivity extends AppCompatActivity {

EditText edit_name, edit_phone, edit_consulting; // 위젯과 연결 할 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        edit_name = findViewById(R.id.edit_name);
        edit_phone = findViewById(R.id.edit_phone);
        edit_consulting = findViewById(R.id.edit_consulting);
        // 전화걸기
        findViewById(R.id.btn_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:010-1234-5678"));
                startActivity(intent);
            }
        });

        // 상담 메세지 보내기

        findViewById(R.id.btn_sms).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CheckBox check = findViewById(R.id.check);
                if(check.isChecked() == false) {
                    Toast.makeText(CallActivity.this, "개인정보보호방침에 동의해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 이름, 전화 번호, 상담 내용을 읽어 들인다.
                String str_name = edit_name.getText().toString().trim();
                String str_phone = edit_phone.getText().toString().trim();
                String str_consulting = edit_consulting.getText().toString().trim();

                if(str_name.length() == 0 || str_phone.length() == 0 || str_consulting.length() == 0) {
                    Toast.makeText(CallActivity.this, "빈칸을 모두 입력해 주세요.", Toast.LENGTH_SHORT).show();
                }

                else {
                    String msg = "보낸사람:" + str_name + "\n연락처:" + str_phone
                            + "\n상담내용:" + str_consulting;
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("smsto:+82 1084318767"));  // This ensures only SMS apps respond
                    intent.putExtra("sms_body", msg);
                    startActivity(intent);
                }
            }
        });

    }

}




















