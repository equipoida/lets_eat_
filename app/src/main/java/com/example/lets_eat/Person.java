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

public class Person extends AppCompatActivity {
    private ActivityPersonBinding binding;
    private static final String TAG = "revokeActivity";
    static private TextView information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPersonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //로그인 되어있는 email 정보 보여주기
        information = binding.emailInformation;
        information.setText(MainActivity.email);

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
}