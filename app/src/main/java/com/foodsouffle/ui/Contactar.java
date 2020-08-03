package com.foodsouffle.ui;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.foodsouffle.R;

public class Contactar extends Fragment {

    private ContactarViewModel mViewModel;


    public static Contactar newInstance() {
        return new Contactar();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.contactar_fragment, container, false);

        final EditText editTextTo = root.findViewById(R.id.editTextTextMailto);
        final EditText editTextSubject = root.findViewById(R.id.editTextTextSub);
        final EditText editTextMessage = root.findViewById(R.id.editTextTextMensaje);
        final Button sendEmail = root.findViewById(R.id.btnEnviar);

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String to = "deyson19@mail.com";
                String subject = editTextSubject.getText().toString();
                String message = editTextMessage.getText().toString();

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL,new String[]{to});
                email.putExtra(Intent.EXTRA_SUBJECT,subject);
                email.putExtra(Intent.EXTRA_TEXT,message);
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email,"Selecciona tu servidor de correo:"));
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ContactarViewModel.class);
        // TODO: Use the ViewModel
    }

}