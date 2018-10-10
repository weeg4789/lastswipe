package com.wee.weeraponsw;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Main2Activity extends AppCompatActivity {

    TextView t1;
    User name = new User();

    DatabaseReference ref;

    static int count = 19;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        t1 =(TextView)findViewById(R.id.T1);

        Swipe();

        ref = FirebaseDatabase.getInstance().getReference();

        final Query select=ref.orderByKey().equalTo(Integer.toString(count));
        select.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    name = ds.getValue(User.class);
                    String id = ds.getKey();
                    t1.setText(id+" : "+name.getName());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void Swipe(){
        RelativeLayout Re=findViewById(R.id.pB);
        Re.setOnTouchListener(new Swipe(this){
            public void onSwipeRight(){
                count--;
                final Query select=ref.orderByKey().equalTo(Integer.toString(count));
                select.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            name = ds.getValue(User.class);
                            String id = ds.getKey();
                            t1.setText(id+" : "+name.getName());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            public void onSwipeLeft(){
                count++;
                final Query select=ref.orderByKey().equalTo(Integer.toString(count));
                select.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            name = ds.getValue(User.class);
                            String id = ds.getKey();
                            t1.setText(id+" : "+name.getName());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            public void onSwipeBottom(){
                Intent intent = new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
