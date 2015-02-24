package com.example.kobayashishinji.listviewsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kobayashi.shinji on 2015/02/24.
 */
public class CustomListAdapter extends ArrayAdapter<User> {
    LayoutInflater mLayoutInflater;

    public CustomListAdapter(Context context, int resource, List<User> object) {
        super(context, resource, object);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;
        if (convertView == null) {
            view = mLayoutInflater.inflate(R.layout.listitem, parent, false);
        } else {
            view = convertView;
        }

        User user = getItem(position);
        ((TextView) view.findViewById(R.id.title)).setText(user.getTitle());
        ((TextView) view.findViewById(R.id.message)).setText(user.getMessage());
        ((ImageView) view.findViewById(R.id.icon)).setImageDrawable(user.getIcon());
        return view;
    }
}
