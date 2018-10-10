package com.wee.weeraponsw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText edittext;
    Button btn;

    FirebaseDatabase database;
    DatabaseReference ref;

    User add = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edittext = (EditText)findViewById(R.id.edittext);
        btn =(Button)findViewById(R.id.btn);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("19");

        Swipe();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_click();
            }
        });
    }

    public void btn_click(){
        add.setName(edittext.getText().toString());

        ref.setValue(add);
    }

    public void Swipe(){
        RelativeLayout Re=findViewById(R.id.pA);
        Re.setOnTouchListener(new Swipe(this){
            public void onSwipeTop(){
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }


}


