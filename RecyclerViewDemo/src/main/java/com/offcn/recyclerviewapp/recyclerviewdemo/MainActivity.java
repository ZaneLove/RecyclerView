package com.offcn.recyclerviewapp.recyclerviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {
    private List<Integer> mDatas;
    private MyRecyclerView myRecyclerView;
    private ImageView img_content;
    private GalleryAdapter adapter;
    private String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_scroll_recyclerview_layout);
        //初始化数据
        initData();
        //得到控件
        myRecyclerView = (MyRecyclerView) findViewById(R.id.id_recyclerview_horizontal);
        img_content = (ImageView) findViewById(R.id.id_content);

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        myRecyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器
        adapter = new GalleryAdapter(this,mDatas);
        //给Adapter设置自定义的Item回调事件
        adapter.setOnItemClickListener(new GalleryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_SHORT).show();
                if(position >= mDatas.size()) {
                    position = position % mDatas.size();
                }
                img_content.setImageResource(mDatas.get(position));
            }
        });
        myRecyclerView.setAdapter(adapter);
        myRecyclerView.setItemScrollChangeListener(new MyRecyclerView.OnItemScrollChangeListener() {
            @Override
            public void onChange(View view, int position) {
                if(position >= mDatas.size()) {
                    position = position % mDatas.size();
                }
                img_content.setImageResource(mDatas.get(position));
            }
        });
    }

    private void initData() {
        mDatas = new ArrayList<Integer>(Arrays.asList(
                R.drawable.a,R.drawable.b,R.drawable.c,
                R.drawable.d,R.drawable.e,R.drawable.f,
                R.drawable.g,R.drawable.h,R.drawable.l
        ));
    }
}