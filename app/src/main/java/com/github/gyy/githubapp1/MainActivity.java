package com.github.gyy.githubapp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;

import com.github.gyy.githubapp1.jiguang.JPushUtil;
import com.github.gyy.githubapp1.oaid.DevicesUtil;
import com.github.gyy.githubapp1.keyboard.Keyboard;
import com.github.gyy.githubapp1.sp.GlobalKey;
import com.github.gyy.githubapp1.sp.SharedPrefGlobal;

public class MainActivity extends AppCompatActivity {
    ScrollView scroll_view;
    //自定义键盘
    Keyboard key_board;
    EditText edit_view;
    View key_show;
    String[] KEY;
    StringBuffer stringBuffer = new StringBuffer();
    //
    EditText alias_edit;
    SharedPrefGlobal sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = new SharedPrefGlobal.Builder().setFileName(GlobalKey.FILE_USER).create(this);

        initView();
        //初始化oaid
        DevicesUtil.init();

    }

    public void getDeviceInfo(View view) {
        //获取oaid
        DevicesUtil.getOaid();
    }

    public void setAlias(View view) {
        String PHONE = alias_edit.getText().toString();

        sp.put(GlobalKey.PHONE, PHONE);
        JPushUtil.setAlias(getApplication(), PHONE);

    }

    public void deleteAlias(View view) {

        sp.put(GlobalKey.PHONE, "");
        JPushUtil.deleteAlias(this);
    }


    private void initView() {
        key_board = findViewById(R.id.keyboard);
        edit_view = findViewById(R.id.edit_view);
        key_show = findViewById(R.id.key_show);
        scroll_view = findViewById(R.id.scroll_view);
        alias_edit = findViewById(R.id.alias_edit);


        key_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (KEY == null) {
                    KEY = new String[]{
                            "1", "2", "3",
                            "4", "5", "6",
                            "7", "8", "9",
                            "", "0", "<<"
                    };
                    //设置键盘
                    key_board.setKeyboardKeys(KEY);
                    //键盘键的点击事件
                    key_board.setOnClickKeyboardListener(new Keyboard.OnClickKeyboardListener() {
                        @Override
                        public void onKeyClick(int position, String value) {
                            if (position < 11 && position != 9) {
                                stringBuffer.append(value);
                                edit_view.setText(stringBuffer.toString());
                                edit_view.setSelection(stringBuffer.length());
                            } else if (position == 11) {
                                if (stringBuffer.length() > 0) {
                                    stringBuffer.deleteCharAt(stringBuffer.length()-1);
                                    edit_view.setText(stringBuffer.toString());
                                    edit_view.setSelection(stringBuffer.length());
                                }

                            }
                        }
                    });
                }

                //显示键盘，滚动 scroll_view
                key_board.setVisibility(View.VISIBLE);
                scroll_view.post(new Runnable() {
                    public void run() {
                        scroll_view.fullScroll(View.FOCUS_DOWN);
                    }
                });

            }
        });
    }
}
