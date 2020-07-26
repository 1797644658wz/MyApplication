package com.example.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myapplication.R;
import com.example.myapplication.adapter.MyRecyclerviewAdapter;
import com.example.myapplication.bean.Goods;

import java.util.List;

public class tab1 extends Fragment {

    private Context mContext;
    private View view;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MyRecyclerviewAdapter adapter;

    private List<Goods> goodsArrayList;
    private Goods[] fruits={new Goods(R.drawable.apple,"Apple"),new Goods(R.drawable.banana,"Banana"),
            new Goods(R.drawable.orange,"Orange"),new Goods(R.drawable.watermelon,"Watermelon"),
            new Goods(R.drawable.pear,"Pear"),new Goods(R.drawable.grape,"Group"),
            new Goods(R.drawable.pineapple,"Pineapple"),new Goods(R.drawable.strawberry,"Strawberry")
            ,new Goods(R.drawable.cherry,"Cherry"),new Goods(R.drawable.mango,"Mango")};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mContext = getActivity();
        if (null != view) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (null != parent) {
                parent.removeView(view);
            }
        } else {
            view = inflater.inflate(R.layout.tab1_fragment, null);

            goodsArrayList=Goods.getGoodsList(fruits);
            RecyclerView recyclerView=view.findViewById(R.id.recycler_view);
            GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),2);
            recyclerView.setLayoutManager(layoutManager);
            adapter=new MyRecyclerviewAdapter(goodsArrayList);
            recyclerView.setAdapter(adapter);

            swipeRefreshLayout=view.findViewById(R.id.swip_refresh);
            swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    refreshFruits();
                }
            });
        }
        return view;
    }

    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        goodsArrayList.clear();

                        goodsArrayList=Goods.getGoodsList(fruits);
                        RecyclerView recyclerView=view.findViewById(R.id.recycler_view);
                        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),2);
                        recyclerView.setLayoutManager(layoutManager);
                        adapter=new MyRecyclerviewAdapter(goodsArrayList);
                        recyclerView.setAdapter(adapter);

                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(mContext, "刷新成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }
}