package com.aug.swip;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private SwipListView mListView;
    private ArrayList<String> mList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initData() {
        for (int i = 0; i < 100; i++) {
            mList.add("item " + i);
        }
    }

    private void initView() {
        mListView = (SwipListView)findViewById(R.id.listview);
        ListViewAdapter adapter = new ListViewAdapter(this);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, mList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    class ListViewAdapter extends SwipAdapter {

        public ListViewAdapter(Context context) {
            super(context);
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return super.getView(position, convertView, parent);
        }

        protected View generateLeftView(final int position) {
            TextView view = new TextView(mContext);
            LinearLayout.LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(lp);
            view.setText(mList.get(position));
            view.setPadding(10, 30, 10, 30);
            return view;
        }

        protected View generateRightView(final int position) {
            TextView view = new TextView(mContext);
            LinearLayout.LayoutParams lp = new LayoutParams(200, 100);
            view.setLayoutParams(lp);
            view.setText("delete ");
            view.setGravity(Gravity.CENTER);
            view.setBackgroundColor(Color.parseColor("#12c2c2"));
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "del " + mList.get(position), Toast.LENGTH_SHORT).show();
                    mList.remove(position);
                    notifyDataSetChanged();
                }
            });
            return view;
        }
    }
}
