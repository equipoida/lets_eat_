package com.example.lets_eat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.lets_eat.databinding.ActivityMainBinding;
import com.example.lets_eat.databinding.ItemBinding;

import java.util.ArrayList;
import java.util.List;

class Notification_list {
    Drawable micon;
    CharSequence mmainmessage;
    String msubmessage;

    public Notification_list(Drawable icon, CharSequence mainmessage, String submessage) {
        micon = icon;
        mmainmessage = mainmessage;
        msubmessage = submessage;
    }
}

class ViewHolder extends RecyclerView.ViewHolder {
    ItemBinding mBinding;

    ViewHolder(ItemBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }
}

class MyAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<Notification_list> mNotifications;

    MyAdapter(List<Notification_list> Notifications) {
        mNotifications = Notifications;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemBinding binding = ItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notification_list sale = mNotifications.get(position);
        holder.mBinding.icon.setImageDrawable(sale.micon);
        holder.mBinding.mainmessage.setText(sale.mmainmessage);
        holder.mBinding.submessage.setText(sale.msubmessage);
    }

    @Override
    public int getItemCount() {
        return mNotifications.size();
    }
}


public class notification_list extends AppCompatActivity {
    //ArrayList Notifications = new ArrayList<Notifications>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_notification_list); //setContentView(binding.getRoot());


        /*
        // 어댑터뷰 강의에서 가져온 코
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for(int i=0;i<packages.size();i++) {
            ApplicationInfo appInfo = packages.get(i);
            CharSequence label = appInfo.loadLabel(pm);
            Drawable icon = appInfo.loadIcon(pm);
            String packageName = appInfo.packageName;

            mSystems.add(new System(icon, label, packageName));
        }

        binding.reCyclerview.setAdapter(new MyAdapter(mSystems));
        binding.reCyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
         */
    }
}