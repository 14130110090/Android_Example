package com.example.boxuegu.activity;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.boxuegu.R;
import com.example.boxuegu.utils.LogUtils;
import com.example.boxuegu.utils.MD5Utils;

import java.util.Objects;

public class RegisterActivity extends BaseActivity {
    EditText et_phone;
    EditText et_password;
    String et_phone_value;
    String et_password_value;
    TextView tv_title;
    TextView tv_back;
    Button bt_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

    private void init() {
        et_phone = findViewById(R.id.register_et_phone);
        et_password = findViewById(R.id.register_et_password);
        tv_title = findViewById(R.id.nav_tv_title);
        tv_back = findViewById(R.id.nav_tv_back);
        bt_reg = findViewById(R.id.register_bt_reg);
        tv_title.setText("注册登录");
        tv_back.setOnClickListener(onClickListener);
        bt_reg.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.nav_tv_back:
                    finish();
                    break;
                case R.id.register_bt_reg:
                    et_phone_value = et_phone.getText().toString().trim();
                    et_password_value = et_password.getText().toString().trim();
                    if (et_phone_value.equals("") || et_password_value.equals("")) {
                        showShortToast("不可以为空");
                        return;
                    }
                    if (isExistUserName(et_phone_value)) {
                        showShortToast("该用户已存在");
                        return;
                    }
                    saveRegisterInfo(et_phone_value, et_password_value);
                    break;
            }
        }
    };

    private boolean isExistUserName(String username) {
        SharedPreferences sp = getSharedPreferences("USER_INFO", MODE_PRIVATE);
        return Objects.equals(sp.getString("user_name", null), username);
    }

    private void saveRegisterInfo(String username, String password) {
        String md5Psw = MD5Utils.md5(password);
        SharedPreferences sp = getSharedPreferences("USER_INFO", MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString("user_name", username);
        ed.putString("user_password", md5Psw);
        ed.apply();
    }
}