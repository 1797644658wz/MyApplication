package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.bean.Goods;

import java.util.ArrayList;

public class GoodsAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Goods> mGoodsList;

    public GoodsAdapter(Context mContext, ArrayList<Goods> mGoodsList) {
        this.mContext = mContext;
        this.mGoodsList = mGoodsList;
    }

    @Override
    public int getCount() {
        return mGoodsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mGoodsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            holder=new ViewHolder();

            convertView= LayoutInflater.from(mContext).inflate(R.layout.listview_item,null);
            holder.im_content=convertView.findViewById(R.id.im_content);
            holder.tv_content=convertView.findViewById(R.id.tv_content);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        Goods goods=mGoodsList.get(position);
        holder.im_content.setImageResource(goods.getImageIid());
        holder.tv_content.setText(goods.getName());

        return convertView;
    }
    public final class ViewHolder{
        public ImageView im_content;
        public TextView tv_content;
    }
}
