package com.chen.activity;

import com.nineteen.myandroid.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FirstActivity extends Activity implements OnClickListener {

    EditText inputNum;
    TextView result;
    Button send;

    public static final int REQUESTCODE_SECOND = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_actvity);
        inputNum = (EditText) findViewById(R.id.inputNum);
        send = (Button) findViewById(R.id.btnSend);
        result = (TextView) findViewById(R.id.result);
        send.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSend:
                if (inputNum.getText().toString() != null
                        && !inputNum.getText().toString().equals("")) {
                    long num = Long.parseLong(inputNum.getText().toString());
                    Intent intent = new Intent(this, SecondActivity.class);
                    intent.putExtra("num", num);
                    startActivityForResult(intent, REQUESTCODE_SECOND);
                }
                break;

            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE_SECOND && resultCode == RESULT_OK
                && data != null && data.hasExtra("result")) {
            result.setText("计算结果为：" + data.getLongExtra("result", 0));
        }
    }
}
