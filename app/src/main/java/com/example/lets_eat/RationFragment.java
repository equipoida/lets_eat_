package com.example.lets_eat;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RatingBar mstar;
    EditText mreview;
    Button mmenuname;
    FirebaseDatabase rootNode;
    DatabaseReference mDatabase;
    public RationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RationFragment newInstance(String param1, String param2) {
        RationFragment fragment = new RationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_ration, container, false);
        mstar = (RatingBar)view.findViewById(R.id.ratingBar);
        mreview = (EditText)view.findViewById(R.id.editText);
        mmenuname = (Button)view.findViewById(R.id.menuchoice);
        final Button submit = (Button)view.findViewById(R.id.submit);
        mmenuname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getContext(), view);
                MenuInflater inflater = getActivity().getMenuInflater();
                inflater.inflate(R.menu.popup, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        mmenuname.setText(menuItem.getTitle());
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

                Toast.makeText(getContext(), "리뷰가 저장됨", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}