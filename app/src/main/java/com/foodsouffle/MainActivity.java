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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    EditText mEditTextName;
    EditText mEditTextMail;
    EditText mEditTextPassword;
    Button  mButtonRegister , mButtonLogin;

    //VARIABLES QUE VAMOS A PASAR DATOS
    String name = "";
    String mail = "";
    String password = "";


    private FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mEditTextName = findViewById(R.id.edtTextName);
        mEditTextMail = findViewById(R.id.edtTextMail);
        mEditTextPassword = findViewById(R.id.edtTextPwd);
        mButtonRegister = findViewById(R.id.btnRegister);
        mButtonLogin = findViewById(R.id.btnLogin);

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //recupero los datos que ingrese el usuario
                name = mEditTextName.getText().toString();
                mail = mEditTextMail.getText().toString();
                password = mEditTextPassword.getText().toString();

                if(!name.isEmpty() && !mail.isEmpty() && !password.isEmpty()){
                    if(password.length()>=5){
                        registerUser();
                    }else{
                        Toast.makeText(MainActivity.this,"Tu contraseña debe tener al menos 5 caracteres",Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(MainActivity.this,"Debes llenar todos los campos",Toast.LENGTH_LONG).show();
                }
            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }

    //Si los campos del registro son validos entonces procedemos a insertarlos a nuestra base de datos
    private void registerUser(){
        mAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Map<String,Object> map = new HashMap<>();
                    map.put("name",name);
                    map.put("mail",mail);
                    map.put("password",password);

                    String id = mAuth.getCurrentUser().getUid();
                    /*Inserto los campos en la base de datos, en nuestro caso sería una coleccion que hemos creado
                    y se llama Users
                     */
                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Intent i = new Intent(MainActivity.this,ProfileActivity.class);
                                startActivity(i);
                                finish();
                            }else{
                                Toast.makeText(MainActivity.this,"No se pudiero crear los datos",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(MainActivity.this,"No se ha podido registar el usuario",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //Recuperamos el estado del usuario para que no tenga que volver a iniciar sesion cuando entre a la app
    protected void onStart(){
        super.onStart();

        if(mAuth.getCurrentUser() !=  null){
            Intent intent=new Intent(this,ProfileActivity.class);
            startActivity(intent);
            finish();
        }
    }
    public final void onBackPressed(){
        isDestroyed();
    }
}
