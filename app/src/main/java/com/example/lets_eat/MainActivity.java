package com.example.lets_eat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;
import com.example.lets_eat.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private EditText email_login;
    private EditText pwd_login;
   FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final Intent intent = new Intent(this, SubActivity.class);
        final Intent intent2 = new Intent(this, Join.class);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){

            //이미 로그인 되었다면 이 액티비티를 종료함

            finish();

            //그리고 profile 액티비티를 연다.

            startActivity(new Intent(getApplicationContext(),SubActivity.class)); //추가해 줄 ProfileActivity

        }
        email_login=binding.textId;
        pwd_login=binding.textPassword;
        binding.login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //로그인 버튼을 누르면, 학식메뉴 나오게하기
                //이곳에서 로그인 정보 확인하는 코드 필요
               String email=email_login.getText().toString().trim();
                String pwd=pwd_login.getText().toString().trim();
                //firebase.auth().setPersistence(firebase.auth.Auth.Persistence.LOCAL);

                firebaseAuth.signInWithEmailAndPassword(email,pwd)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){

                                    startActivity(intent);
                                }else{
                                    Toast.makeText(MainActivity.this,"로그인 오류",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
        binding.join.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //회원가입 버튼 누르면, 회원가입 액티비티 실행
                startActivity(intent2);
            }
        });


        // MyFireBaseMessagingService 관련
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if(!task.isSuccessful()) {
                            Log.w("FCM Log", "getInstanceId failed", task.getException());
                            return;
                        }
                        String token = task.getResult().getToken();
                        Log.d("FCM Log", "FCM 토큰: " + token);
                        Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
