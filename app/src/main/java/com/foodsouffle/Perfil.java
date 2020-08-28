package com.foodsouffle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
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

    Toast toastPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        mAuth= FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference();

        mButtonSignOut = findViewById(R.id.btnSignOut);
        mTextViewName = findViewById(R.id.textViewName);
        mTextViewMail = findViewById(R.id.textViewMail);

        if (isOnline()) {
            toastPosition = Toast.makeText(this,"Hello",Toast.LENGTH_LONG);
            toastPosition.setGravity(Gravity.BOTTOM,0,0);
            toastPosition.show();
        } else {
            toastPosition = Toast.makeText(this,R.string.conexionError,Toast.LENGTH_LONG);
            toastPosition.setGravity(Gravity.BOTTOM,0,0);
            toastPosition.show();
        }

        mButtonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alerBuilder = new AlertDialog.Builder(Perfil.this);
                alerBuilder.setTitle("Log Out");
                alerBuilder.setMessage(R.string.exitMessage);
                alerBuilder.setCancelable(false);


                alerBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
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
    /**
     * Check for Internet Connection, return true if connected else false
     **/
    private boolean isOnline() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
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
        Intent intent = new Intent(Perfil.this,MenuNavigation.class);
        startActivity(intent);
    }

    public final void logoutPressed(){
        mAuth.signOut();
        Intent intent = new Intent(Perfil.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            if(item.getItemId()==R.id.exit){
                Intent exit = new Intent(Perfil.this,ProfileActivity.class);
                startActivity(exit);
                finish();
                isDestroyed();
            }else{
                if (item.getItemId()==R.id.back){
                    Intent i = new Intent(Perfil.this,MenuNavigation.class);
                    startActivity(i);
                    finish();
                    isDestroyed();
                }
            }
            return super.onOptionsItemSelected(item);
    }
}