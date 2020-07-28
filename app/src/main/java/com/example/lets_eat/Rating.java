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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Rating extends AppCompatActivity {
    private ActivityRatingBinding mBinding;
    RatingBar ratingBar;
    EditText editText;

    DatabaseReference mDatabase;
    mDatabase = FirebaseDatabase.getInstance().getReference();NewPostActivity.java
    private void writeNewUser(String menuname,String review,double star) {
        User user = new User();

        mDatabase.child("users").child(userId).setValue(user);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityRatingBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mDatabase.child("users").child(userId).child("username").setValue(name);


        ratingBar = mBinding.ratingBar;
        editText = mBinding.editText;
        final Button menuchoice = mBinding.menuchoice;
        final Button submit = mBinding.submit;

        //final Intent intent = new Intent(this, MainActivity.class);
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
                reference = rootNode.getReference("review");

                reference.setValue("First data storage");
            }
        });
    }
    @IgnoreExtraProperties
    public class Post {

        public String uid;
        public String author;
        public String title;
        public String body;
        public int starCount = 0;
        public Map<String, Boolean> stars = new HashMap<>();

        public Post() {
            // Default constructor required for calls to DataSnapshot.getValue(Post.class)
        }

        public Post(String uid, String author, String title, String body) {
            this.uid = uid;
            this.author = author;
            this.title = title;
            this.body = body;
        }

        @Exclude
        public Map<String, Object> toMap() {
            HashMap<String, Object> result = new HashMap<>();
            result.put("uid", uid);
            result.put("author", author);
            result.put("title", title);
            result.put("body", body);
            result.put("starCount", starCount);
            result.put("stars", stars);

            return result;
        }

    }
    public void mOnClick(View view) {
        //Intent intent = new Intent (this,subActivity.class);
        //intent.putExtra("menu", menuchoice.getText());
        //intent.putExtra("review",edittext.getText());
        //startActivityForResult(intent,0);

        Toast.makeText(getApplication(), "리뷰가 저장됨", Toast.LENGTH_SHORT).show();
        finish();

    }
    private void writeNewPost(String userId, String username, String title, String body) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key = mDatabase.child("posts").push().getKey();
        Post post = new Post(userId, username, title, body);
        Map<String, Object> postValues = post.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/posts/" + key, postValues);
        childUpdates.put("/user-posts/" + userId + "/" + key, postValues);

        mDatabase.updateChildren(childUpdates);
    }
    private void onStarClicked(DatabaseReference postRef) {
        postRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                Post p = mutableData.getValue(Post.class);
                if (p == null) {
                    return Transaction.success(mutableData);
                }

                if (p.stars.containsKey(getUid())) {
                    // Unstar the post and remove self from stars
                    p.starCount = p.starCount - 1;
                    p.stars.remove(getUid());
                } else {
                    // Star the post and add self to stars
                    p.starCount = p.starCount + 1;
                    p.stars.put(getUid(), true);
                }

                // Set value and report transaction success
                mutableData.setValue(p);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b,
                                   DataSnapshot dataSnapshot) {
                // Transaction completed
                Log.d(TAG, "postTransaction:onComplete:" + databaseError);
            }
        });
    }
    @IgnoreExtraProperties
    public class User {

        public String menuname;
        public double star;
public String review;
        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String menuname,String review,double star) {
            this.menuname= menuname;
            this.review=review;
            this.star=star;

        }

    }
}