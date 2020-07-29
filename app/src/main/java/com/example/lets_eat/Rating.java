package com.example.lets_eat;

import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Rating extends AppCompatActivity {
    private ActivityRatingBinding mBinding;
    private FirebaseAuth mAuth;
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
                mDatabase = rootNode.getReference("review");


                //모든 값 가져오기
                String menuname = mmenuname.getText().toString();
                String review = mreview.getText().toString();
                String star = String.valueOf(mstar.getRating());

                Userhelper helperclass = new Userhelper(menuname, review, star);

                //review 안의 user
                //user 안의 push()로 만들어준 랜덤한 문자(개별적인 걸 위함)
                //그 안에 menuname, review, star의 값을 받아줌.
                mDatabase.child("user").push().setValue(helperclass);

                Toast.makeText(getApplication(), "리뷰가 저장됨", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}