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

import com.foodsouffle.MapsActivity;
import com.foodsouffle.R;

public class Ubicanos extends Fragment {

    private UbicanosViewModel mViewModel;

    public static Ubicanos newInstance() {
        return new Ubicanos();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.ubicanos_fragment, container, false);

        final Button btn = view.findViewById(R.id.btnVerMapa);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapa = new Intent(getActivity(), MapsActivity.class);
                startActivity(mapa);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(UbicanosViewModel.class);
        // TODO: Use the ViewModel
    }

}