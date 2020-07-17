package com.example.lets_eat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lets_eat.databinding.ActivityJoinBinding;

import java.util.List;

public class Join extends AppCompatActivity {
    private ActivityJoinBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJoinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final Intent intent = new Intent(this, MainActivity.class);

        binding.joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //회원 정보 입력하고, 회원가입 버튼을 누르면
                //초기 화면(로그인 화면)으로 돌아감.
                //입력된 데이터들을 DB에 저장 필요
                finish();
            }
        });

    }
}