package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;
import com.example.myapplication.menuactivity.BDMapLbsTest;
import com.example.myapplication.menuactivity.CameraAlbumTest;
import com.example.myapplication.menuactivity.MediaplayerTest;
import com.example.myapplication.menuactivity.NotificationTest;
import com.example.myapplication.menuactivity.Sendmessage;
import com.example.myapplication.util.MenuUtil;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private DrawerLayout mDrawerLayout;
    private FirstFragment firstFragment;
    private PersonFragment personFragment;
    private OtherFragment otherFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager=getSupportFragmentManager();

        initview();

        FragmentTransaction transaction=fragmentManager.beginTransaction();
        firstFragment=new FirstFragment();
        transaction.add(R.id.ll_content,firstFragment).commit();

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout=findViewById(R.id.draw_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);

        ActionBar actionBar=getSupportActionBar();

        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_call:
                        Intent call=new Intent(Intent.ACTION_DIAL);
                        call.setData(Uri.parse("tel:"));
                        startActivity(call);
                        Toast.makeText(MainActivity.this, "进入拨号界面", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_location:
                        Intent location=new Intent(MainActivity.this, BDMapLbsTest.class);
                        startActivity(location);
                        Toast.makeText(MainActivity.this, "获取定位", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_mail:
                        Intent mail = new Intent(MainActivity.this, Sendmessage.class);
                        startActivity(mail);
                        Toast.makeText(MainActivity.this, "进入短信界面", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_music:
                        Intent music = new Intent(MainActivity.this, MediaplayerTest.class);
                        startActivity(music);
                        Toast.makeText(MainActivity.this, "进入音乐界面", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }
    private void initview() {
        RadioButton ll_first=findViewById(R.id.ll_first);
        ll_first.setOnClickListener(this);
        RadioButton ll_second=findViewById(R.id.ll_second);
        ll_second.setOnClickListener(this);
        RadioButton ll_third=findViewById(R.id.ll_third);
        ll_third.setOnClickListener(this);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        //显示菜单项左侧图标
        MenuUtil.setOverflowIconVisible(featureId,menu);
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.notice:
                Intent notice=new Intent(this, NotificationTest.class);
                startActivity(notice);
                break;
            case R.id.take_photo:
                Intent takephoto=new Intent(this, CameraAlbumTest.class);
                startActivity(takephoto);
                break;
            case R.id.setting:
                Intent setting=new Intent(Settings.ACTION_SETTINGS);
                startActivity(setting);
                Toast.makeText(this,"进入设置",Toast.LENGTH_LONG).show();
                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
    @Override
    public void onClick(View v) {
        hidefragment();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        switch (v.getId()){
            case R.id.ll_first:
                transaction.show(firstFragment).commit();
                break;
            case R.id.ll_second:
                if (personFragment==null){
                    personFragment=new PersonFragment();
                    transaction.add(R.id.ll_content,personFragment).commit();
                }else {
                    transaction.show(personFragment).commit();
                }
                break;
            case R.id.ll_third:
                if (otherFragment==null){
                    otherFragment=new OtherFragment();
                    transaction.add(R.id.ll_content,otherFragment).commit();
                }else {
                    transaction.show(otherFragment).commit();
                }
                break;
        }
    }

    private void hidefragment() {
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        if(firstFragment!=null&&firstFragment.isAdded()){
            transaction.hide(firstFragment);
        }
        if(personFragment!=null&&personFragment.isAdded()){
            transaction.hide(personFragment);
        }
        if(otherFragment!=null&&otherFragment.isAdded()){
            transaction.hide(otherFragment);
        }
        //提交
        transaction.commit();
    }
}
