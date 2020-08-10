
package com.example.lets_eat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lets_eat.databinding.ActivityConfusionBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;

public class confusion extends AppCompatActivity {
    private static ActivityConfusionBinding mBinding;
    private FirebaseAuth mAuth;
    FirebaseDatabase rootNode;
    DatabaseReference mDatabase;
    private ChipNavigationBar chipnavigationbar;
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
        chipnavigationbar = mBinding.chipbar;
        chipnavigationbar.setItemSelected(R.id.chipbar,true);
        bottonMenu();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseRef = database.getReference("suggestion");

        // Read from the database
        databaseRef.child("suggestion").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();
                // 클래스 모델이 필요?
                for (DataSnapshot notification : dataSnapshot.getChildren()) {
                    //MyFiles filename = (MyFiles) fileSnapshot.getValue(MyFiles.class);
                    //하위키들의 value를 어떻게 가져오느냐???
                    String str = notification.child("suggestion").getValue(String.class);
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
    private void bottonMenu() {
        chipnavigationbar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            final Intent intent6 = new Intent(confusion.this, Recommendation.class);
            final Intent intent5 = new Intent(confusion.this, confusion.class);
            final Intent intent3 = new Intent(confusion.this, notification_list.class);
            final Intent intent4 = new Intent(confusion.this, Rating.class);
            final Intent intent8 = new Intent(confusion.this, PersonActivity.class);
            final Intent intent9 = new Intent(confusion.this, SubActivity.class);
            @Override
            public void onItemSelected(int i) {
                switch(i){
                    case R.id.item0:
                        Toast.makeText(confusion.this, "홈", Toast.LENGTH_SHORT).show();
                        startActivity(intent9);
                        break;
                    case R.id.item1:
                        Toast.makeText(confusion.this, "추천메뉴", Toast.LENGTH_SHORT).show();
                        startActivity(intent6);
                        break;
                    case R.id.item2:
                        Toast.makeText(confusion.this, "한줄 건의함", Toast.LENGTH_SHORT).show();
                        startActivity(intent5); //혼잡도 창으로 가기
                        break;
                    case R.id.item3:
                        Toast.makeText(confusion.this, "알림", Toast.LENGTH_SHORT).show();
                        startActivity(intent3); //알림 창으로 가기
                        break;
                    case R.id.item4:
                        Toast.makeText(confusion.this, "리뷰", Toast.LENGTH_SHORT).show();
                        startActivity(intent4); //리뷰 창으로 가기
                        break;
                    case R.id.item5:
                        Toast.makeText(confusion.this, "계정 정보", Toast.LENGTH_SHORT).show();
                        startActivity(intent8); //계정 정보 창으로 가기
                        break;

                }
            }
        });
    }

}

