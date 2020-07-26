package com.example.myapplication.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.example.myapplication.R;
import com.example.myapplication.bean.Goods;
import java.util.ArrayList;
import java.util.List;

public class Tab1_P extends Fragment {
    private Context mContext;
    private View view;
    ArrayList<Goods> goodsArrayList;
    private List<String> contentList=new ArrayList<>();
    ArrayAdapter<String> adapter;

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
            view = inflater.inflate(R.layout.tab1_p_fragment, null);

            /*goodsArrayList=Goods.getDefaultList();
            GoodsAdapter adapter=new GoodsAdapter(getActivity(),goodsArrayList);
            ListView listView=view.findViewById(R.id.list_view);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Goods goods=goodsArrayList.get(position);
                    if (goods.getName().equals("西瓜")){
                        Toast.makeText(getActivity(),"你点击了第一项",Toast.LENGTH_SHORT).show();
                    }else if (goods.getName().equals("香蕉")){
                        Toast.makeText(getActivity(),"你点击了第二项",Toast.LENGTH_SHORT).show();
                    }
                }
            });*/

            ListView listView=view.findViewById(R.id.list_view);
            adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,contentList);
            listView.setAdapter(adapter);

            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS)!=
                    PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(getActivity(),new String[]{
                        Manifest.permission.READ_CONTACTS},1);
            }else {
                readCotacts();
            }
        }
        return view;
    }
    private void readCotacts() {
        Cursor cursor=null;
        try {
            cursor=getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.
                    Phone.CONTENT_URI,null,null,null,null);
            if (cursor!=null){
                while (cursor.moveToNext()){
                    //获取联系人名字
                    String dispalyName=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    //获取联系人手机号
                    String phonenumber =cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contentList.add(dispalyName+"/n"+phonenumber);
                }
                adapter.notifyDataSetChanged();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor!=null){
                cursor.close();
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    readCotacts();
                }else {
                    Toast.makeText(getActivity(), "You  denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
                default:
        }
    }
}
