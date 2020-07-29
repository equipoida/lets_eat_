package com.example.lets_eat;

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
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.lets_eat.databinding.ActivityRatingBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Rating extends AppCompatActivity {
    private ActivityRatingBinding mBinding;
    RatingBar mstar;
    EditText mreview;
    Button mmenuname;

    FirebaseDatabase rootNode;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityRatingBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mstar = mBinding.ratingBar;
        mreview = mBinding.editText;
        mmenuname = mBinding.menuchoice;

        final Button menuchoice = mBinding.menuchoice;
        final Button submit = mBinding.submit;

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
                mDatabase = rootNode.getReference("users");

                //모든 값 가져오기
                String menuname = mmenuname.getText().toString();
                String review = mreview.getText().toString();
                //String star = mstar.getText().toString();

                Userhelper helperclass = new Userhelper(menuname, review);

                mDatabase.child(menuname).setValue(helperclass);
            }
        });
    }

    public void mOnClick(View view) {
        Toast.makeText(getApplication(), "리뷰가 저장됨", Toast.LENGTH_SHORT).show();
        finish();

    }

}