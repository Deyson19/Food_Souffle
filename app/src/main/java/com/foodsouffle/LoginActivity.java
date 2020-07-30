package com.foodsouffle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {


    EditText mEditTextMail;
    EditText mEditTextPassword;
    Button mButtonLogin;
    Button mButtonResetPassword;

    String mail = "";
    String password = "";

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        //Buscamos aquellos campos o botones con esas id
        mEditTextMail = findViewById(R.id.edtTextMail);
        mEditTextPassword = findViewById(R.id.edtTextPwd);
        mButtonLogin = findViewById(R.id.btnLogin);
        mButtonResetPassword = findViewById(R.id.btnSendToResetPassword);


        //Le asignamos la debida funcion que deseamos hacer

        //Boton de iniciar sesión
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mail = mEditTextMail.getText().toString();
                password = mEditTextPassword.getText().toString();

                if (!mail.isEmpty() && !password.isEmpty()){
                    loginUser();
                }else{
                    Toast.makeText(LoginActivity.this,"Estos campos no pueden estar vacíos", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Boton que te lleva a recuperar contraseña
        mButtonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,ResetPasswordActivity.class);
                startActivity(i);
            }
        });
    }

    //Realizamos el inicio de seción de acuerdo a los requisitos:1 estar ya registrado.
    private void loginUser(){
        mAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent i = new Intent(LoginActivity.this,ProfileActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,"No se ha encontrado este usuario",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
