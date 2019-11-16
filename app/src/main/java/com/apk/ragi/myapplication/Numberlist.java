package com.apk.ragi.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Numberlist extends ArrayAdapter<Number> {

    private Activity context;
    private List<Number> numbers;
    public Numberlist(Activity context, List<Number> numbers){

        super(context,R.layout.list_layout,numbers);
        this.context=  context;
        this.numbers=numbers;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                LayoutInflater layoutInflater= context.getLayoutInflater();
                View listViewItem= layoutInflater.inflate(R.layout.list_layout,null, true);

        TextView textView=(TextView)listViewItem.findViewById(R.id.textView);
        Number number=numbers.get(position);
        textView.setText(String.valueOf(number.getNo()));
        return listViewItem;

    }
}
