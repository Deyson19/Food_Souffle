package com.foodsouffle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdapterList extends BaseAdapter {

    Context context;
    ArrayList<ListPojo> listPojos;

    public AdapterList(Context context, ArrayList<ListPojo> listPojos){
        this.context = context;
        this.listPojos = listPojos;
    }
    @Override
    public int getCount() {
        return listPojos.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        view = LayoutInflater.from(context).inflate(R.layout.custom_list,parent,false);

        final TextView title = view.findViewById(R.id.title);
        TextView description = view.findViewById(R.id.description);
        TextView value = view.findViewById(R.id.value);
        ImageView image = view.findViewById(R.id.image);

        title.setText(listPojos.get(position).getTitle());
        description.setText(listPojos.get(position).getDescription());
        value.setText(listPojos.get(position).getValue());
        image.setImageResource(listPojos.get(position).getImages());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Menú: "+position,Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
