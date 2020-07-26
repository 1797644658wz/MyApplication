package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.adapter.GoodsPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;


public class FirstFragment extends Fragment {

    Context mContext;
    private ViewPager vp_content;//定义一个翻页视图对象
    private TabLayout tab_title;//定义一个标签布局对象
    private ArrayList<String> mTitleArray=new ArrayList<String>();//标题文字队列
    private View view;


    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext=getActivity();
        if (null != view) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (null != parent) {
                parent.removeView(view);
            }
        } else {
            view = inflater.inflate(R.layout.first_fragment, null);
            mTitleArray.add("商品");
            mTitleArray.add("详情");
            mTitleArray.add("售后");
            initTablayout();
            initabviewpager();

        }
        return view;
    }

    private void initabviewpager() {
        //从布局文件中获取名叫vp_content的翻页视图
        vp_content=view.findViewById(R.id.vp_content);
        //构建一个商品信息翻页适配器
        GoodsPagerAdapter adapter=new GoodsPagerAdapter(getChildFragmentManager(),mTitleArray);
        vp_content.setAdapter(adapter);
        //添加页面变更监听
        vp_content.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                tab_title.getTabAt(position).select();
            }
        });
    }

    private void initTablayout() {
        //从布局文件中获取名叫tab_title的标签布局
        tab_title=view.findViewById(R.id.tab_title);
        //添加指定文字标签
        tab_title.addTab(tab_title.newTab().setText(mTitleArray.get(0)));
        tab_title.addTab(tab_title.newTab().setText(mTitleArray.get(1)));
        tab_title.addTab(tab_title.newTab().setText(mTitleArray.get(2)));
        tab_title.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            //在标签选择时触发
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp_content.setCurrentItem(tab.getPosition());
            }
            //标签取消时触发
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            //在标签重复被选择时触发
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });


    }

}
