
package com.example.lets_eat;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lets_eat.databinding.ActivityConfusionBinding;
import com.example.lets_eat.databinding.ActivityRatingBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class confusion extends AppCompatActivity {
    private ActivityConfusionBinding mBinding;
    private FirebaseAuth mAuth;
    FirebaseDatabase rootNode;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= ActivityConfusionBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        // 빈 데이터 리스트 생성.
        final ArrayList<String> items = new ArrayList<String>() ;
        // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items) ;
        final ListView listview = (ListView) findViewById(R.id.listview) ;
        listview.setAdapter(adapter) ;
        // 선택된 곳에 따른 화면이동
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseRef = database.getReference("suggestion");

        // Read from the database
        databaseRef.child("suggestion").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();
                // 클래스 모델이 필요?
                for (DataSnapshot suggest : dataSnapshot.getChildren()) {
                    //MyFiles filename = (MyFiles) fileSnapshot.getValue(MyFiles.class);
                    //하위키들의 value를 어떻게 가져오느냐???
                    String str = suggest.child("suggestion").getValue(String.class);
                    Log.i("TAG: value is ", str);
                    items.add(str);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        // listview 생성 및 adapter 지정.


        mBinding.button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                mDatabase = rootNode.getReference("suggestion");


                //모든 값 가져오기
                String suggestion= mBinding.editTextTextPersonName.getText().toString();

                suggest suggestions = new suggest(suggestion);

                mDatabase.child("suggestion").push().setValue(suggestions);
                // 아이템 추가.
                items.add(mBinding.editTextTextPersonName.getText().toString());
                // listview 갱신
                adapter.notifyDataSetChanged();
                mBinding.editTextTextPersonName.getText().clear();
            }
        }) ;
    }
}

