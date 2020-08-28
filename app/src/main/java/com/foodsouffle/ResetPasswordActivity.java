package com.foodsouffle;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    EditText mEditTextEmail;
    Button mButtonResetPassword;

    String email = "";
    FirebaseAuth mAuth;

    ProgressDialog mDialog;

    Toast toastPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        mAuth = FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);
        mEditTextEmail = findViewById(R.id.editTextEmail);
        mButtonResetPassword = findViewById(R.id.btnResetPassword);

        if (isOnline()) {
            toastPosition = Toast.makeText(this,"Welcome Back",Toast.LENGTH_LONG);
            toastPosition.setGravity(Gravity.TOP,0,80);
            toastPosition.show();
        } else {
            toastPosition = Toast.makeText(this,R.string.conexionError,Toast.LENGTH_LONG);
            toastPosition.setGravity(Gravity.TOP,0,80);
            toastPosition.show();
        }

        mButtonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.setMessage("Wait a moment while send you E-mail");
                mDialog.setCanceledOnTouchOutside(false);
                mDialog.show();
                email = mEditTextEmail.getText().toString();

                if (!email.isEmpty()){
                    resetPassword();
                }else{
                    Toast.makeText(ResetPasswordActivity.this,"Please enter a correct E-mail", Toast.LENGTH_LONG).show();
                }

                mDialog.dismiss();
            }
        });
    }

    private void resetPassword(){
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ResetPasswordActivity.this,"Awesome, \n Check you E-mail",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ResetPasswordActivity.this,"SORRY, we can't send the E-mail \n Try again",Toast.LENGTH_LONG).show();
                }
            }
        });
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
