package com.aug.swip;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

public abstract class SwipAdapter extends BaseAdapter {
    public Context mContext;

    public SwipAdapter(Context context) {
        this.mContext = context;
    }

    protected abstract View generateLeftView(final int position);

    protected abstract View generateRightView(final int position);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layout = new LinearLayout(mContext);
        convertView = layout;

        layout.addView(generateLeftView(position));
        layout.addView(generateRightView(position));
        return convertView;
    }
}
