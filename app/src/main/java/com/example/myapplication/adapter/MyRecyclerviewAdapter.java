package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.bean.Goods;
import com.example.myapplication.menuactivity.FruitActivity;

import java.util.List;

public class MyRecyclerviewAdapter extends RecyclerView.Adapter<MyRecyclerviewAdapter.ViewHolder> {

    private Context mContext;

    private List<Goods> mGoodsList;

    public MyRecyclerviewAdapter(List<Goods> mGoodsList) {
        this.mGoodsList = mGoodsList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView= (CardView) itemView;
            imageView=itemView.findViewById(R.id.im_content);
            textView=itemView.findViewById(R.id.tv_content);
        }
    }
    @NonNull
    @Override
    public MyRecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Goods goods = mGoodsList.get(position);
                Intent intent = new Intent(mContext, FruitActivity.class);
                intent.putExtra(FruitActivity.FRUIT_NAME, goods.getName());
                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID, goods.getImageIid());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerviewAdapter.ViewHolder holder, int position) {
        Goods goods=mGoodsList.get(position);
        holder.textView.setText(goods.getName());
        Glide.with(mContext).load(goods.getImageIid()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mGoodsList.size();
    }
}
