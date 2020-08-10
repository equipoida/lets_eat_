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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class Rating extends AppCompatActivity {
    private ActivityRatingBinding mBinding;
    private FirebaseAuth mAuth;
    RatingBar mstar;
    EditText mreview;
    Button mmenuname;
    private ChipNavigationBar chipnavigationbar;


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
        chipnavigationbar = mBinding.chipbar;
        chipnavigationbar.setItemSelected(R.id.chipbar,true);
        bottonMenu();
        /*
        // 빈 데이터 리스트 생성.
        final ArrayList<String> items = new ArrayList<String>() ;

        // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items) ;
        final ListView listview = (ListView) findViewById(R.id.listview) ;
        listview.setAdapter(adapter);


         */

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


                //값을 데이터베이스에 보내기
                String menuname = mmenuname.getText().toString();
                String review = mreview.getText().toString();
                String star = String.valueOf(mstar.getRating());

                Userhelper helperclass = new Userhelper(menuname, review, star);

                //review 안의 user
                //user 안의 push()로 만들어준 랜덤한 문자(개별적인 걸 위함)
                //그 안에 menuname, review, star의 값을 받아줌.
                mDatabase.child("user").push().setValue(helperclass);

                /*

                // 아이템 추가.
                items.add(mBinding.menuchoice.getText().toString());
                items.add(String.valueOf(mBinding.ratingBar.getRating()));
                //Recommendation의 listview 갱신
                //adapter.notifyDataSetChanged();


                 */


                Toast.makeText(getApplication(), "리뷰가 저장됨", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    private void bottonMenu() {
        chipnavigationbar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            final Intent intent6 = new Intent(Rating.this, Recommendation.class);
            final Intent intent5 = new Intent(Rating.this, confusion.class);
            final Intent intent3 = new Intent(Rating.this, notification_list.class);
            final Intent intent4 = new Intent(Rating.this, Rating.class);
            final Intent intent8 = new Intent(Rating.this, PersonActivity.class);
            final Intent intent9 = new Intent(Rating.this, SubActivity.class);
            @Override
            public void onItemSelected(int i) {
                switch(i){
                    case R.id.item0:
                        Toast.makeText(Rating.this, "홈", Toast.LENGTH_SHORT).show();
                        startActivity(intent9);
                        break;
                    case R.id.item1:
                        Toast.makeText(Rating.this, "추천메뉴", Toast.LENGTH_SHORT).show();
                        startActivity(intent6);
                        break;
                    case R.id.item2:
                        Toast.makeText(Rating.this, "한줄 건의함", Toast.LENGTH_SHORT).show();
                        startActivity(intent5); //혼잡도 창으로 가기
                        break;
                    case R.id.item3:
                        Toast.makeText(Rating.this, "알림", Toast.LENGTH_SHORT).show();
                        startActivity(intent3); //알림 창으로 가기
                        break;
                    case R.id.item4:
                        Toast.makeText(Rating.this, "리뷰", Toast.LENGTH_SHORT).show();
                        startActivity(intent4); //리뷰 창으로 가기
                        break;
                    case R.id.item5:
                        Toast.makeText(Rating.this, "계정 정보", Toast.LENGTH_SHORT).show();
                        startActivity(intent8); //계정 정보 창으로 가기
                        break;

                }
            }
        });
    }
}