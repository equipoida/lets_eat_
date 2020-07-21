package com.example.lets_eat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;
import com.example.lets_eat.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final Intent intent = new Intent(this, SubActivity.class);
        final Intent intent2 = new Intent(this, Join.class);

        binding.login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //로그인 버튼을 누르면, 학식메뉴 나오게하기
                //이곳에서 로그인 정보 확인하는 코드 필요
                startActivity(intent);
            }
        });
        binding.join.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //회원가입 버튼 누르면, 회원가입 액티비티 실행
                startActivity(intent2);
            }
        });
    }
}
