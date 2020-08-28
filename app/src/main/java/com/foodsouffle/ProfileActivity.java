package com.foodsouffle;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

public class ProfileActivity extends AppCompatActivity {

    TextView mTextViewName;

    Button mButtonEntrar;
    FirebaseAuth mAuth;
    DatabaseReference mDataBase;

    Toast toastPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth= FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference();

        mButtonEntrar = findViewById(R.id.btnEntrar);
        mTextViewName = findViewById(R.id.textViewName);


        if (isOnline()) {
            toastPosition = Toast.makeText(this,"Welcome Back",Toast.LENGTH_LONG);
            toastPosition.setGravity(Gravity.TOP,0,80);
            toastPosition.show();
        } else {
            toastPosition = Toast.makeText(this,R.string.conexionError,Toast.LENGTH_LONG);
            toastPosition.setGravity(Gravity.TOP,0,80);
            toastPosition.show();
        }


        mButtonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,MenuNavigation.class);
                startActivity(intent);
            }
        });
        getUserInfo();
    }

    private void getUserInfo(){
        String id = mAuth.getCurrentUser().getUid();
        mDataBase.child("Users").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String name = dataSnapshot.child("name").getValue().toString();

                    mTextViewName.setText(name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Attention:");
        builder.setMessage("What do you want to do?");
        builder.setIcon(R.drawable.ic_warning);
        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                isDestroyed();
            }
        });
        builder.setNeutralButton("Nothing", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(ProfileActivity.this,"Awesome, thanks for staying.",Toast.LENGTH_LONG).show();
            }
        });

        builder.setNegativeButton("Back to Menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent exit = new Intent(ProfileActivity.this,MenuNavigation.class);
                startActivity(exit);
            }
        });
        builder.create().show();
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
}
