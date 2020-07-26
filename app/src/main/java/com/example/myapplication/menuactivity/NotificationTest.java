package com.example.myapplication.menuactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import java.io.File;
import java.util.List;

public class NotificationTest extends AppCompatActivity  {

    private ListView listView;
    private List<String> noticeList;
    private String contents[]={"firstnotice","secongnotice","thirdnotice","forcenotice"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_test);

        listView=findViewById(R.id.listview_notice);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contents);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent notice=new Intent(NotificationTest.this, MainActivity.class);
                        PendingIntent pi=PendingIntent.getActivity(NotificationTest.this,0,notice,0);
                        NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        if(Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O ) {  //Android O （8.0）以上版本需要渠道
                            //通知重要度，DEFAULT及以上，通知时手机默认会有振动
                            NotificationChannel notificationChannel = new NotificationChannel("channelid1", "channelname", NotificationManager.IMPORTANCE_HIGH);
                            manager.createNotificationChannel(notificationChannel);
                        }else {
                            NotificationCompat.Builder builder=new NotificationCompat.Builder(NotificationTest.this,"channelid1");
                            builder.setContentTitle("This is content title")
                                    .setContentText("This is content")
                                    .setStyle(new NotificationCompat.BigTextStyle().bigText("learn how to build notification,send and" +
                                            "sunc data,and use voice actions.Get the official Android IDE and developer tools to build app" +
                                            "s for Android"))
                                    .setWhen(System.currentTimeMillis())
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                                    .setContentIntent(pi)
                                    .setAutoCancel(true)
                                    .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")))
                                    .setVibrate(new long[]{0,1000,1000,1000})
                                    .setLights(Color.GREEN,1000,1000)
                                    .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.apple)))
                                    .build();
                            manager.notify(1,builder.build());
                        }
                        Toast.makeText(NotificationTest.this, "firstnotice", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }

}
