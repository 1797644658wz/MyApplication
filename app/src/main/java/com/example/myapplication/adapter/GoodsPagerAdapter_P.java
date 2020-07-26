package com.example.myapplication.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myapplication.fragment.Tab1_P;
import com.example.myapplication.fragment.Tab2_P;
import com.example.myapplication.fragment.Tab3_P;

import java.util.ArrayList;

public class GoodsPagerAdapter_P extends FragmentPagerAdapter {

    private ArrayList<String> mTitleArray; // 声明一个标题文字队列

    // 碎片页适配器的构造函数，传入碎片管理器与标题队列
    public GoodsPagerAdapter_P(FragmentManager fm, ArrayList<String> titleArray) {
        super(fm);
        mTitleArray = titleArray;
    }

    // 获取指定位置的碎片Fragment
    public Fragment getItem(int position) {
        if (position == 0) { // 第一页展示书籍封面
            return new Tab1_P();
        } else if (position == 1) { // 第二页展示书籍详情
            return new Tab2_P();
        }else if (position == 2){
            return new Tab3_P();
        }
        return new Tab1_P();
    }

    // 获取碎片Fragment的个数
    public int getCount() {
        return mTitleArray.size();
    }

    // 获得指定碎片页的标题文本
    public CharSequence getPageTitle(int position) {
        return mTitleArray.get(position);
    }
}
