package com.example.lets_eat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.lets_eat.databinding.ActivityConfusionBinding;
import com.example.lets_eat.databinding.ActivityRatingBinding;
import com.example.lets_eat.databinding.ActivityRecommendationBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Recommendation extends AppCompatActivity {
    private ActivityRecommendationBinding mBinding;
    FirebaseDatabase rootNode;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= ActivityRecommendationBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());


        //if(R.id.ratingBar)

        final ArrayList<String> items = new ArrayList<String>() ;
        //final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items) ;

    }


    public void mOnClick(View view) {
        //Intent intent = new Intent (this,subActivity.class);
        //intent.putExtra("menu", menuchoice.getText());
        //intent.putExtra("review",edittext.getText());
        //startActivityForResult(intent,0);

        finish();

    }

}