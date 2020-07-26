package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import org.litepal.crud.DataSupport;
import java.util.List;
public class LoginTest extends AppCompatActivity {

    private Button login;
    private Button register;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private CheckBox remenmberPassword;
    private CheckBox zddl;
    private EditText edname;
    private EditText edpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initview();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember = preferences.getBoolean("remember_password",false);
        if (isRemember){
            String account = preferences.getString("account","");
            String password = preferences.getString("password","");
            edname.setText(account);
            edpassword.setText(password);
            remenmberPassword.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username=edname.getText().toString();
                String password=edpassword.getText().toString();
                String getaccount = edname.getText().toString();
                String getpassword = edpassword.getText().toString();

                if (findname(username)&&findpassword(username,password)){
                    editor=preferences.edit();
                    if (remenmberPassword.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",getaccount);
                        editor.putString("password",getpassword);
                    Intent mainac=new Intent(LoginTest.this,MainActivity.class);
                    startActivity(mainac);
                    Toast.makeText(LoginTest.this,"登录成功！",Toast.LENGTH_SHORT).show();
                    }else {
                        editor.clear();
                    }
                    editor.commit();
                    finish();
                }else {
                    Toast.makeText(LoginTest.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(LoginTest.this, ResigerTest.class);
                startActivity(register);
            }
        });
    }

    private void initview() {
        remenmberPassword=findViewById(R.id.get_password);
        login = findViewById(R.id.login);
        edname=findViewById(R.id.name);
        edpassword=findViewById(R.id.password);
        zddl=findViewById(R.id.zddl);
    }
    public boolean findname(String name) {
        List<User> oldname = DataSupport.where("username = ?", name).find(User.class);
        if (oldname == null || oldname.size() == 0) {
            return false;
        }
        return true;
    }

    public boolean findpassword(String name,String password) {
        List<User> olduser = DataSupport.where("username = ? and password = ?", name,password).find(User.class);
        if (olduser == null || olduser.size() ==0) {
            return false;
        }
        return true;
    }
}