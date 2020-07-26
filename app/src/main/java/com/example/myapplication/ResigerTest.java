package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class ResigerTest extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resiger_test);

        initview();

    }

    private void initview() {
        Button userregister = findViewById(R.id.userregister);
        userregister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.userregister:
                LitePal.getDatabase();
                EditText Edname = findViewById(R.id.username);
                EditText Edpassword = findViewById(R.id.userpassword);
                String username = Edname.getText().toString();
                String userpassword = Edpassword.getText().toString();

                if (username.equals("") || userpassword.equals("")) {
                    Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (!findname(username)) {
                    Toast.makeText(this, "用户名已经存在", Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(userpassword);
                    user.save();
                    Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }

    }

    public boolean findname(String name) {
        List<User> users = DataSupport.where("username = ?",name).find(User.class);
        if (users==null || users.size()==0) {
            return true;
        }
        return false;
    }
}