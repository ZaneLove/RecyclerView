package com.offcn.recyclerviewapp.recyclerviewdemo;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private List<Integer> mDatas;
    private Context context;
    public GalleryAdapter(Context context,List<Integer> datas) {
        this.context = context;
        this.mDatas = datas;
    }

    //ItemClick的回调接口
    public interface OnItemClickListener {
        void onItemClick(View view,int position);
    }
    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    //创建ViewHolder
    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = View.inflate(context,R.layout.activity_index_gallery_item,null);
        ViewHolder holder = new ViewHolder(view);
        holder.mImg = (ImageView) view.findViewById(R.id.index_gallery_item_iamge);
        holder.mTxt = (TextView) view.findViewById(R.id.index_gallery_item_text);
        return holder;
    }

    //设置值,将数据绑定至ViewHolder
    @Override
    public void onBindViewHolder(final GalleryAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.mImg.setImageResource(mDatas.get(i % mDatas.size()));
        //如果设置了回调,则设置点击事件
        if(onItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(viewHolder.itemView,i);
                }
            });
        }
    }

    //获取总的条目数
    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    //必须继承RecyclerView.ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImg;
        TextView mTxt;
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}