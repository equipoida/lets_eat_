package com.example.lets_eat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lets_eat.databinding.ActivityPersonBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class Person extends AppCompatActivity {
    private ActivityPersonBinding binding;
    private static final String TAG = "revokeActivity";
    static private TextView information;
    private ChipNavigationBar chipnavigationbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPersonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //로그인 되어있는 email 정보 보여주기
        information = binding.emailInformation;
        information.setText(MainActivity.email);
        chipnavigationbar = binding.chipbar;
        chipnavigationbar.setItemSelected(R.id.chipbar,true);
        bottonMenu();


        final Intent intent9 = new Intent(this, MainActivity.class);
        binding.btnLogout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(intent9);
                    }
                }
        );
        binding.btnRevoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                ((FirebaseUser) user).delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "User account deleted.");
                                    Toast.makeText(Person.this,"user account deleted!",Toast.LENGTH_SHORT).show();
                                    startActivity(intent9);
                                }
                            }
                        });

            }
        });
    }
    private void bottonMenu() {
        chipnavigationbar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            final Intent intent6 = new Intent(Person.this, Recommendation.class);
            final Intent intent5 = new Intent(Person.this, confusion.class);
            final Intent intent3 = new Intent(Person.this, notification_list.class);
            final Intent intent4 = new Intent(Person.this, Rating.class);
            final Intent intent8 = new Intent(Person.this, Person.class);
            final Intent intent9 = new Intent(Person.this, SubActivity.class);
            @Override
            public void onItemSelected(int i) {
                switch(i){
                    case R.id.item0:
                        Toast.makeText(Person.this, "홈", Toast.LENGTH_SHORT).show();
                        startActivity(intent9);
                        break;
                    case R.id.item1:
                        Toast.makeText(Person.this, "추천메뉴", Toast.LENGTH_SHORT).show();
                        startActivity(intent6);
                        break;
                    case R.id.item2:
                        Toast.makeText(Person.this, "한줄 건의함", Toast.LENGTH_SHORT).show();
                        startActivity(intent5); //혼잡도 창으로 가기
                        break;
                    case R.id.item3:
                        Toast.makeText(Person.this, "알림", Toast.LENGTH_SHORT).show();
                        startActivity(intent3); //알림 창으로 가기
                        break;
                    case R.id.item4:
                        Toast.makeText(Person.this, "리뷰", Toast.LENGTH_SHORT).show();
                        startActivity(intent4); //리뷰 창으로 가기
                        break;
                    case R.id.item5:
                        Toast.makeText(Person.this, "계정 정보", Toast.LENGTH_SHORT).show();
                        startActivity(intent8); //계정 정보 창으로 가기
                        break;

                }
            }
        });
    }

}