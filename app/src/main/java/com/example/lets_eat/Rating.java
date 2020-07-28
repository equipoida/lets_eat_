package com.example.lets_eat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.lets_eat.databinding.ActivityRatingBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Rating extends AppCompatActivity {
    private ActivityRatingBinding mBinding;
    RatingBar ratingBar;
    EditText editText;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityRatingBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        ratingBar = mBinding.ratingBar;
        editText = mBinding.editText;
        final Button menuchoice = mBinding.menuchoice;
        final Button submit = mBinding.submit;

        //final Intent intent = new Intent(this, MainActivity.class);
        menuchoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), view);
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.popup, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        menuchoice.setText(menuItem.getTitle());
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("review");

                reference.setValue("First data storage");
            }
        });
    }

    public void mOnClick(View view) {
        //Intent intent = new Intent (this,subActivity.class);
        //intent.putExtra("menu", menuchoice.getText());
        //intent.putExtra("review",edittext.getText());
        //startActivityForResult(intent,0);

        Toast.makeText(getApplication(), "리뷰가 저장됨", Toast.LENGTH_SHORT).show();
        finish();

    }
}