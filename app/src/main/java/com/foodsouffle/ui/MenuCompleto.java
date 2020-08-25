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

    int value [] = {17600,36500,16200,14200,13600,10000,10600,12600};
    String unityCost = "Por tan solo: \n";
    String coin = " COP";
    String menu1 = unityCost + value[0] +coin;
    String menu2= unityCost + value[1] +coin;
    String menu3 = unityCost + value[2]+coin;
    String menu4 = unityCost + value[3]+coin;
    String menu5 = unityCost + value[4]+coin;
    String menu6 = unityCost + value[5]+coin;
    String menu7 = unityCost + value[6]+coin;
    String menu8 = unityCost + value[7]+coin;
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

        list.add(new ListPojo("Menu 1",R.string.menu1, menu1, R.drawable.menu1));
        list.add(new ListPojo("Menu 2",R.string.menu2, menu2, R.drawable.menu2));
        list.add(new ListPojo("Menu 3",R.string.menu3, menu3, R.drawable.menu3));
        list.add(new ListPojo("Menu 4",R.string.menu4, menu4, R.drawable.menu4));
        list.add(new ListPojo("Menu 5",R.string.menu5, menu5, R.drawable.menu5));
        list.add(new ListPojo("Menu 6",R.string.menu6, menu6, R.drawable.menu6));
        list.add(new ListPojo("Menu 7",R.string.menu7, menu7, R.drawable.menu7));
        list.add(new ListPojo("Menu 8",R.string.menu8, menu8, R.drawable.menu8));
    }
}