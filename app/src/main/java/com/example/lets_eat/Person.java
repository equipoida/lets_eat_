package com.example.lets_eat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.lets_eat.databinding.ActivityMainBinding;
import com.example.lets_eat.databinding.ActivityPersonBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Person extends AppCompatActivity {
    private ActivityPersonBinding binding;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPersonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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
    }

}