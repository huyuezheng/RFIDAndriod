package com.zhl.scanlable;

import com.uhf.scanlable.UHfData;
import com.uhf.scanlable.UHfData.UHfGetData;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import cn.pda.serialport.Tools;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ReadWriteActivity extends Activity implements OnClickListener, OnItemSelectedListener {

    private int mode;
    EditText edENum0;
    int selectedEd = 3;
    int selectedWhenPause = 0;

    Spinner c_mem;
    EditText c_wordPtr;
    EditText c_len;
    EditText c_pwd;
    EditText c_ptr;

    EditText b_id;
    EditText b_addr;
    EditText b_num;

    EditText content;
    Button rButton;
    Button wButton;
    Button btWriteEPC;
    private static final int CHECK_W_6B = 0;
    private static final int CHECK_R_6B = 1;
    private static final int CHECK_W_6C = 2;
    private static final int CHECK_R_6C = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rw_6c);
        initView();

        Util.initSoundPool(this);
    }

    @Override
    protected void onResume() {
        Log.i("zhouxin", ">>>>>>>>>>>>>>>>>>>>>>rw onResume");
        if (!UHfData.getuhf_id().equals(edENum0.getText().toString())) {
            edENum0.setText(UHfData.getuhf_id());
        }

        UHfData.setuhf_id("10100100500006FF00");
        content.setText(UHfData.getuhf_id());
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        selectedWhenPause = selectedEd;
    }

    @Override
    protected void onDestroy() {
        Log.i("zhouxin", ">>>>>>>>>>>>>>>>>>>>>rw onDestroy");
        super.onDestroy();
    }

    private void initView() {
        edENum0 = (EditText) findViewById(R.id.epc0);
        c_mem = (Spinner) findViewById(R.id.mem_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.men_select,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        c_mem.setAdapter(adapter);
        c_mem.setSelection(3, true);
        c_mem.setOnItemSelectedListener(this);

        c_wordPtr = (EditText) findViewById(R.id.et_wordptr);//起始地址可以是0
        c_wordPtr.setText("0");
        c_len = (EditText) findViewById(R.id.et_length);
        c_len.setText("4");
        c_pwd = (EditText) findViewById(R.id.et_pwd);
        c_pwd.setText("00000000");
        content = (EditText) findViewById(R.id.et_content_6c);
        rButton = (Button) findViewById(R.id.button_read_6c);
        wButton = (Button) findViewById(R.id.button_write_6c);
        btWriteEPC = (Button) findViewById(R.id.button_write_epc);
        rButton.setOnClickListener(this);
        wButton.setOnClickListener(this);
        btWriteEPC.setOnClickListener(this);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ScanMode.class);
        intent.putExtra(MainActivity.EXTRA_MODE, getIntent().getStringExtra(MainActivity.EXTRA_MODE));
        Window w = ((ActivityGroup) getParent()).getLocalActivityManager().startActivity("FirstActivity", intent);
        View view = w.getDecorView();
        ((ActivityGroup) getParent()).setContentView(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        if (view == wButton) {
            Log.i("zhouxin", "----onclick---- wButton");
            if (!checkContent(CHECK_W_6C))
                return;
            try {
                int wordPtr = Integer.valueOf("0");
				byte[] word = Tools.intToByte(wordPtr);
				int result = UHfGetData.Write6c((byte) (int) Integer.valueOf(c_len.getText().toString()),
						(byte) ((edENum0.getText().toString().length()) / 4),
						UHfGetData.hexStringToBytes(edENum0.getText().toString()), (byte) selectedEd, word,
						UHfGetData.hexStringToBytes(content.getText().toString()),
						UHfGetData.hexStringToBytes(c_pwd.getText().toString()));
              /*  int wordPtr = Integer.valueOf("0");
                byte[] word = Tools.intToByte(wordPtr);
                int result = UHfGetData.Write6c((byte) (int) Integer.valueOf(4),
                        (byte) ("ifid的长度".length() / 4),
                        UHfGetData.hexStringToBytes("IFID字符串"), (byte) 3, word,
                        UHfGetData.hexStringToBytes("我们要写入的一维码字符串"),
                        UHfGetData.hexStringToBytes("00000000密码"));*/

                if (result != 0) {
                    Toast.makeText(getApplicationContext(), getString(R.string.write_fail), Toast.LENGTH_SHORT).show();
                } else {
                    content.setText("");
                    Toast.makeText(getApplicationContext(), getString(R.string.write_success), Toast.LENGTH_SHORT)
                            .show();
                }
            } catch (Exception ex) {
            }
        } else if (view == rButton) {
            Log.i("zhouxin", "----onclick---- rButton");
            if (!checkContent(CHECK_R_6C))
                return;
            try {
                int wordPtr = Integer.valueOf(c_wordPtr.getText().toString());
                byte[] word = Tools.intToByte(wordPtr);
                int result = UHfGetData.Read6C((byte) ((edENum0.getText().toString().length()) / 4),
                        UHfGetData.hexStringToBytes(edENum0.getText().toString()), (byte) selectedEd, word,
                        Byte.valueOf(c_len.getText().toString()),
                        UHfGetData.hexStringToBytes(c_pwd.getText().toString()));
                String temp = UHfGetData
                        .bytesToHexString(UHfGetData.getRead6Cdata(), 0, Byte.valueOf(c_len.getText().toString()) * 2)
                        .toUpperCase();
                if (result != 0) {
                    content.setText("");
                    showToast(getString(R.string.read_fail));
                } else {
                    content.setText(temp.toUpperCase());
                    showToast(getString(R.string.read_success));
                    Util.play(1, 0);
                }
            } catch (Exception ex) {
            }
        } else if (view == btWriteEPC) {
            if (!checkContent(CHECK_W_6C))
                return;
            try {
                int result = UHfGetData.WriteEPC((byte) ((edENum0.getText().toString().length()) / 4),
                        UHfGetData.hexStringToBytes(c_pwd.getText().toString()),
                        UHfGetData.hexStringToBytes(edENum0.getText().toString()),
                        UHfGetData.hexStringToBytes(content.getText().toString()));
                if (result != 0) {
                    showToast(getString(R.string.write_epc_fail));
                } else {
                    showToast(getString(R.string.write_epc_success));
                }
            } catch (Exception ex) {
            }

        }
    }

    private Toast mToast;

    private void showToast(String content) {
        if (mToast == null) {
            mToast = Toast.makeText(this, content, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(content);
        }
        mToast.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
        selectedEd = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    }

    private boolean checkContent(int check) {
        switch (check) {
            case CHECK_W_6C:
                if (Util.isEtEmpty(content))
                    return Util.showWarning(this, R.string.content_empty_warning);
                if (Integer.valueOf(c_len.getText().toString()) != content.getText().toString().length() / 4)
                    return Util.showWarning(this, R.string.length_content_warning);
                if (!(Util.isLenLegal(content)))
                    return Util.showWarning(this, R.string.str_lenght_odd_warning);
            case CHECK_R_6C:
                if (Util.isEtEmpty(c_wordPtr))
                    return Util.showWarning(this, R.string.wordptr_empty_warning);
                if (Util.isEtEmpty(c_len))
                    return Util.showWarning(this, R.string.length_empty_warning);
                if (Util.isEtEmpty(c_pwd))
                    return Util.showWarning(this, R.string.pwd_empty_warning);

                if (!(Util.isLenLegal(c_pwd)))
                    return Util.showWarning(this, R.string.str_lenght_odd_warning);

                break;
            default:
                break;
        }
        return true;
    }

}
