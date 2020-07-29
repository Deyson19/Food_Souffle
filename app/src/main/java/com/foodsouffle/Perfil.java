package com.foodsouffle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Perfil extends AppCompatActivity {


    TextView mTextViewName,mTextViewMail;

    Button mButtonSignOut;
    FirebaseAuth mAuth;
    DatabaseReference mDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        mAuth= FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference();

        mButtonSignOut = findViewById(R.id.btnSignOut);
        mTextViewName = findViewById(R.id.textViewName);
        mTextViewMail = findViewById(R.id.textViewMail);

        mButtonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        getUserInfo();
    }

    private void getUserInfo() {
        String id = mAuth.getCurrentUser().getUid();
        mDataBase.child("Users").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String name = dataSnapshot.child("name").getValue().toString();
                    String mail = dataSnapshot.child("mail").getValue().toString();

                    mTextViewName.setText(name);
                    mTextViewMail.setText(mail);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}