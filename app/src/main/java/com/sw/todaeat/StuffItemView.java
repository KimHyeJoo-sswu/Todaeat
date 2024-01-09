package com.sw.todaeat;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StuffItemView extends LinearLayout  {
    TextView nameText;
    TextView dateText;

    public StuffItemView(Context context) {
        super(context);
        init(context);
    }

    public StuffItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.stuff_item, this, true);

        nameText = (TextView) findViewById(R.id.nameText);
        dateText = (TextView) findViewById(R.id.dateText);
    }

    public void setName(String name) {
        nameText.setText(name);
    }

    public void setDate(String date) {
        dateText.setText(date);
    }

}