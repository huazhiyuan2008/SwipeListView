package com.aug.swip;

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
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private Context mContext;
    private SwipListView mListView;
    @SuppressWarnings("unused")
    private SwipAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        initView();
    }

    private void initView() {
        mListView = (SwipListView)findViewById(R.id.listview);
        SwipAdapter adapter = new SwipAdapter();
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, "item onclick " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    class SwipAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout layout = new LinearLayout(mContext);
            convertView = layout;

            layout.addView(generateLeftView(position));
            layout.addView(generateRightView(position));
            return convertView;
        }

        private View generateLeftView(final int position) {
            TextView view = new TextView(mContext);
            LinearLayout.LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(lp);
            view.setText("item " + position);
            view.setPadding(10, 30, 10, 30);
            // view.setBackgroundColor(Color.parseColor("#f2c2c2"));
//            view.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(mContext, "left " + position, Toast.LENGTH_SHORT).show();
//                }
//            });
            return view;
        }

        private View generateRightView(final int position) {
            TextView view = new TextView(mContext);
            LinearLayout.LayoutParams lp = new LayoutParams(mListView.getRightViewWidth(), 100);
            view.setLayoutParams(lp);
            view.setText("delete " + position);
            view.setGravity(Gravity.CENTER);
            view.setBackgroundColor(Color.parseColor("#12c2c2"));
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "del " + position, Toast.LENGTH_SHORT).show();
                }
            });
            view.setFocusable(false);
            return view;
        }
    }
}
