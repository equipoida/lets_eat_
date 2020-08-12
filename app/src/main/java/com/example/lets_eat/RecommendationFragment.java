package com.example.lets_eat;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecommendationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecommendationFragment extends Fragment {
    FirebaseDatabase rootNode;
    DatabaseReference mDatabase;
    Button btn_search;
    EditText menu;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public RecommendationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecommendationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecommendationFragment newInstance(String param1, String param2) {
        RecommendationFragment fragment = new RecommendationFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_recommendation, container, false);
        btn_search = (Button) view.findViewById(R.id.search);
        menu = (EditText) view.findViewById(R.id.menu);
        //if(R.id.ratingBar)


        // 빈 데이터 리스트 생성.
        final ArrayList<String> items = new ArrayList<String>();

        // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.
        final ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, items);
        final ListView listview = (ListView)view.findViewById(R.id.listview);
        listview.setAdapter(adapter);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference databaseRef = database.getReference("review");


        // Read from the database
        databaseRef.child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();

                for (DataSnapshot user : dataSnapshot.getChildren()) {
                    //MyFiles filename = (MyFiles) fileSnapshot.getValue(MyFiles.class);
                    // reviw 안에 있는 user 안의 review를 listview에 넣기
                    String str = user.child("menuname").getValue(String.class);
                    String strs = user.child("review").getValue(String.class);
                    String strss = user.child("star").getValue(String.class);
                    Log.i("TAG: value is ", str);
                    items.add(str + "\n리뷰: " + strs + "\n별점: " + strss);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });




        menu.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(keyCode==KeyEvent.KEYCODE_ENTER)
                {
                    btn_search.performClick();
                    return true;
                }
                return false;
            }
        });
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = menu.getText().toString();
                databaseRef.child("user").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        adapter.clear();
                        // 클래스 모델이 필요?
                        for (DataSnapshot user : dataSnapshot.getChildren()) {
                            //MyFiles filename = (MyFiles) fileSnapshot.getValue(MyFiles.class);
                            // reviw 안에 있는 user 안의 review를 listview에 넣기
                            String str = user.child("menuname").getValue(String.class);
                            String strs = user.child("review").getValue(String.class);
                            String strss = user.child("star").getValue(String.class);
                            if (str.contains(name)) {
                                Log.i("TAG: value is ", str);
                                items.add(str + "\n리뷰: " + strs + "\n별점: " + strss);
                            }
                            else if(name.length()==0){
                                items.add(str + "\n리뷰: " + strs + "\n별점: " + strss);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        // Inflate the layout for this fragment
        return view;
    }


}