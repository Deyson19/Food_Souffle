package com.foodsouffle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
                AlertDialog.Builder alerBuilder = new AlertDialog.Builder(Perfil.this);
                alerBuilder.setTitle("Cerrar sesión");
                alerBuilder.setMessage("¿Estas seguro que deseeas cerrar sesión?");
                alerBuilder.setCancelable(false);


                alerBuilder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       logoutPressed();
                    }
                });
                alerBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(Perfil.this,"Que bueno que te quedas",Toast.LENGTH_LONG).show();
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = alerBuilder.create();
                alertDialog.show();
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
    public final void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(),MenuNavigation.class);
        startActivity(intent);
    }

    public final void logoutPressed(){
        mAuth.signOut();
        Intent intent = new Intent(Perfil.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}