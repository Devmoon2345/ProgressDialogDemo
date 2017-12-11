package com.example.progressdialogdemo;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

//http://blog.csdn.net/caesardadi/article/details/11982721
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mButton1 = findViewById(R.id.button1);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogSpinner();
            }
        });

        Button mButton2 = findViewById(R.id.button2);
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogHorizontal();
            }
        });
    }

    //圆形进度条
    private void showDialogSpinner() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置进度条的形式为圆形转动的进度条
        dialog.setCancelable(true);// 设置是否可以通过点击Back键取消
        dialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
        dialog.setIcon(R.mipmap.ic_launcher);//
        // 设置提示的title的图标，默认是没有的，如果没有设置title的话只设置Icon是不会显示图标的
        dialog.setTitle("提示");
        // dismiss监听
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

            @Override
            public void onDismiss(DialogInterface dialog) {
                // TODO Auto-generated method stub

            }
        });
        // 监听Key事件被传递给dialog
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub

                return false;
            }
        });
        // 监听cancel事件
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                // TODO Auto-generated method stub

            }
        });
        //设置可点击的按钮，最多有三个(默认情况下)
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                    }
                });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                    }
                });
        dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "中立",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                    }
                });
        dialog.setMessage("这是一个圆形进度条");
        dialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    Thread.sleep(5000);
                    // cancel和dismiss方法本质都是一样的，都是从屏幕中删除Dialog,唯一的区别是
                    // 调用cancel方法会回调DialogInterface.OnCancelListener如果注册的话,dismiss方法不会回掉
                    dialog.cancel();
                    // dialog.dismiss();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }).start();
    }

    //水平进度条
    private void showDialogHorizontal() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);// 设置水平进度条
        dialog.setCancelable(true);// 设置是否可以通过点击Back键取消
        dialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
        dialog.setIcon(R.mipmap.ic_launcher);// 设置提示的title的图标，默认是没有的
        dialog.setTitle("提示");
        dialog.setMax(100);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                    }
                });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                    }
                });
        dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "中立",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                    }
                });
        dialog.setMessage("这是一个水平进度条");
        dialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                int i = 0;
                while (i < 100) {
                    try {
                        Thread.sleep(100);
                        // 更新进度条的进度,可以在子线程中更新进度条进度
                        dialog.incrementProgressBy(1);
                        // dialog.incrementSecondaryProgressBy(10)//二级进度条更新方式
                        i++;

                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
                // 在进度条走完时删除Dialog
                dialog.dismiss();
            }
        }).start();
    }
}
