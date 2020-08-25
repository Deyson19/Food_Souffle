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

import com.foodsouffle.R;

public class CompartirApp extends Fragment {

    private CompartirAppViewModel mViewModel;
    String enviar = "Hola, espero te encuentres bien. \n Te invito a que descargues esta aplicación para realizar pedidos desde casa. ";
    String linkApp = "Puedes descargarla desde este link: "+ "https://youtu.be/fVO6R-NQw-s?t=2780";
    String indicacion = "Por favor selecciona el medio por el cual desea compartir la aplicación";
    String errorApp = "Error al intentar compartir por este medio, porfavor comprueba que tengas instalada la aplicación oficial de";

    public static CompartirApp newInstance() {
        return new CompartirApp();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.compartir_fragment, container, false);

        final Button btnShare = view.findViewById(R.id.compartir);
        final Button btnShareFace = view.findViewById(R.id.facebook);
        final Button btnShareInsta = view.findViewById(R.id.instagram);
        final Button btnShareWhats = view.findViewById(R.id.whatsapp);

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                /*la variable con el link lo tenemos en el método principal.
                ahora lo que haremos es pasarle al usuario el link que va a compartir
                 */
                share.putExtra(Intent.EXTRA_TEXT,enviar+linkApp);
                startActivity(Intent.createChooser(share,indicacion));

            }
        });

        btnShareFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("text/plain");
                    //share.putExtra(Intent.EXTRA_SUBJECT,enviar);
                    share.putExtra(Intent.EXTRA_TEXT, enviar + linkApp);
                    share.setPackage("com.facebook.katana");
                    startActivity(share);
                }catch (Exception e){
                    Toast.makeText(getContext(),errorApp+" Facebook",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnShareInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("text/plain");
                    //share.putExtra(Intent.EXTRA_SUBJECT,enviar);
                    share.putExtra(Intent.EXTRA_TEXT,enviar+linkApp);
                    share.setPackage("com.instagram.android");
                    startActivity(share);
                }catch (Exception e){
                    Toast.makeText(getContext(),errorApp+"Instagram",Toast.LENGTH_LONG).show();
                }

            }
        });

        btnShareWhats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("text/plain");
                    // share.putExtra(Intent.EXTRA_SUBJECT,enviar);
                    share.putExtra(Intent.EXTRA_TEXT,enviar+linkApp);
                    share.setPackage("com.whatsapp");
                    startActivity(share);
                }catch (Exception e){
                    Toast.makeText(getContext(),errorApp+" WhatsApp",Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CompartirAppViewModel.class);
        // TODO: Use the ViewModel
    }

}