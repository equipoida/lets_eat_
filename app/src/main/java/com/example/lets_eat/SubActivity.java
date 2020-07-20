package com.example.lets_eat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final Intent intent2 = new Intent(this, confusion.class);
        final Intent intent3 = new Intent(this, notification_list.class);
        final Intent intent4 = new Intent(this, Rating.class);
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "추천메뉴", Toast.LENGTH_SHORT).show();

                return true;
            case R.id.item2:
                Toast.makeText(this, "혼잡도", Toast.LENGTH_SHORT).show();
                startActivity(intent2);
                return true;
            case R.id.item3:
                Toast.makeText(this, "알림", Toast.LENGTH_SHORT).show();
                startActivity(intent3);
                return true;
            case R.id.item4:
                Toast.makeText(this, "리뷰", Toast.LENGTH_SHORT).show();
                startActivity(intent4);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

}
