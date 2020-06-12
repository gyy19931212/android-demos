package com.github.gyy.githubapp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;

import com.github.gyy.githubapp1.oaid.DevicesUtil;
import com.github.gyy.githubapp1.keyboard.Keyboard;

public class MainActivity extends AppCompatActivity {
    //控件
    ScrollView scroll_view;
    Keyboard key_board;
    EditText edit_view;
    View key_show;
    //数据
    String[] KEY;
    StringBuffer stringBuffer = new StringBuffer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        //初始化oaid
        DevicesUtil.init();

    }

    public void getDeviceInfo(View view) {
        //获取oaid
        DevicesUtil.getOaid();
    }

    private void initView() {
        key_board = findViewById(R.id.keyboard);
        edit_view = findViewById(R.id.edit_view);
        key_show = findViewById(R.id.key_show);
        scroll_view = findViewById(R.id.scroll_view);

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
