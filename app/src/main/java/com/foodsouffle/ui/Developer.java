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
import android.widget.Toast;

import com.foodsouffle.MainActivity;
import com.foodsouffle.R;
import com.google.android.material.snackbar.Snackbar;

public class Developer extends Fragment {

    private DeveloperViewModel mViewModel;

    public static Developer newInstance() {
        return new Developer();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.developer_fragment, container, false);

        final Button btnSend = root.findViewById(R.id.btnSendToDeveloper);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String to = "deyandres19@gmail.com" + "deiandresvent@hotmail.com";
                    String optionsEmailClient = "Select email client:";

                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                    email.setType("message/rfc822");
                    startActivity(Intent.createChooser(email, optionsEmailClient));
                }catch (Exception e){
                    Toast.makeText(getContext(),"Please Install an E-Mail App",Toast.LENGTH_LONG).show();
                }
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DeveloperViewModel.class);
        // TODO: Use the ViewModel
    }

}