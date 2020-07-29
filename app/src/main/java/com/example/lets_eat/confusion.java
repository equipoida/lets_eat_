
package com.example.lets_eat;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lets_eat.databinding.ActivityConfusionBinding;

import java.util.ArrayList;

public class confusion extends AppCompatActivity {
    private ActivityConfusionBinding mBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= ActivityConfusionBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        // 빈 데이터 리스트 생성.
        final ArrayList<String> items = new ArrayList<String>() ;
        // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items) ;

        // listview 생성 및 adapter 지정.
        final ListView listview = (ListView) findViewById(R.id.listview) ;
        listview.setAdapter(adapter) ;
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        mBinding.button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                // 아이템 추가.
                items.add(mBinding.editTextTextPersonName.getText().toString());

                // listview 갱신
                adapter.notifyDataSetChanged();
            }
        }) ;
    }
}