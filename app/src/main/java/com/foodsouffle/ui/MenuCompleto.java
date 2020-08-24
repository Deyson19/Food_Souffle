package com.foodsouffle.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.foodsouffle.AdapterList;
import com.foodsouffle.ListPojo;
import com.foodsouffle.R;

import java.util.ArrayList;

public class MenuCompleto extends AppCompatActivity {

    ListView listView;
    ArrayList<ListPojo> list;
    AdapterList adapterList;

    int value [] = {3500,4000,3500,2500,2000,1500,5500,3500};
    String unityCost = "Por tan solo: \n";
    String coin = " COP";
    //String  aguacate,mango,manzana,papaya,guayaba,banano,sandia,pera;
    String aguacate = unityCost + value[0] +coin;
    String mango = unityCost + value[1] +coin;
    String manzana = unityCost + value[2]+coin;
    String papaya = unityCost + value[3]+coin;
    String guayaba = unityCost + value[4]+coin;
    String banano = unityCost + value[5]+coin;
    String sandia = unityCost + value[6]+coin;
    String pera = unityCost + value[7]+coin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_completo);

        listView = findViewById(R.id.list_view);
        listShow();

        adapterList = new AdapterList(this,list);
        listView.setAdapter(adapterList);
    }
    private void listShow(){
        list = new ArrayList<ListPojo>();

        list.add(new ListPojo("Aguacate",R.string.aguacate, aguacate, R.drawable.aguacate));
        list.add(new ListPojo("Aguacate",R.string.aguacate, aguacate, R.drawable.aguacate));
        list.add(new ListPojo("Aguacate",R.string.aguacate, aguacate, R.drawable.aguacate));
        list.add(new ListPojo("Aguacate",R.string.aguacate, aguacate, R.drawable.aguacate));
        list.add(new ListPojo("Aguacate",R.string.aguacate, aguacate, R.drawable.aguacate));
        list.add(new ListPojo("Aguacate",R.string.aguacate, aguacate, R.drawable.aguacate));
        list.add(new ListPojo("Aguacate",R.string.aguacate, aguacate, R.drawable.aguacate));
        list.add(new ListPojo("Aguacate",R.string.aguacate, aguacate, R.drawable.aguacate));
        list.add(new ListPojo("Aguacate",R.string.aguacate, aguacate, R.drawable.aguacate));

    }
}