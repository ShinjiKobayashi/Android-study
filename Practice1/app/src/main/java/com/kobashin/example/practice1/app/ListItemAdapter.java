package com.kobashin.example.practice1.app;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by skobayashi1 on 15/04/01.
 */
public class ListItemAdapter extends ArrayAdapter<User>{
    private LayoutInflater mLayoutInflater;
    private Resources mResources;
    private OnCheckboxClickListener mOnCheckboxClickListener = null;
    private List<User> items;

    public interface OnCheckboxClickListener{
        void onChenged(boolean checked, int userId);
    }

    public ListItemAdapter(Context context, int resource,
            List<User> objects) {
        super(context, resource, objects);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mResources = context.getResources();
        items = objects;
    }

    public void setOnCheckboxClickListener(OnCheckboxClickListener listener){
        mOnCheckboxClickListener = listener;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = null;
        if (convertView == null) {
            view = mLayoutInflater.inflate(R.layout.list_items, parent, false);
        } else {
            view = convertView;
        }

        final User user = getItem(position);
        ((TextView) view.findViewById(R.id.nameTextView)).setText(user.getName());
        ((TextView) view.findViewById(R.id.mailTextView)).setText(user.getMailAddr());
        ((ImageView) view.findViewById(R.id.imageView)).setImageDrawable(
                mResources.getDrawable(user.getIconId()));

        // We must call setOnCheckedChangeListener before setChecked.
        // Because, old onCheckedChanged is called by setChecked if we call setChecked first.
        ((CheckBox) view.findViewById(R.id.checkbox)).setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        User user = items.get(position);
                        user.setIsChecked(isChecked);
                        items.set(position, user);
                        if (mOnCheckboxClickListener != null) {
                            mOnCheckboxClickListener.onChenged(isChecked, user.getId());
                        }
                    }
                });
        ((CheckBox) view.findViewById(R.id.checkbox)).setChecked(user.isChecked());
        return view;
    }
}
