package com.example.lets_eat;

import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lets_eat.databinding.ActivityConfusionBinding;

public class confusion extends AppCompatActivity {
    private ActivityConfusionBinding mBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= ActivityConfusionBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        ProgressBar bar1 = mBinding.progressBar1;
        ProgressBar bar2 = mBinding.progressBar2;

        bar1.setProgress(50);
        bar2.setProgress(50);

    }
}
