package com.example.kjong_8gahnmf.contact;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etNumber;
    private Button  btnInsertData;
    private Button  btnShowAllData;

    private DatabaseHelper databaseHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);

        etName = (EditText) findViewById(R.id.etName);
        etNumber = (EditText) findViewById(R.id.etNumber);

        btnInsertData = (Button) findViewById(R.id.btnInsertData);
        btnShowAllData = (Button) findViewById(R.id.btnShowAllData);

        btnInsertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String phone = etNumber.getText().toString();

                if(name.length() == 0){
                    showDilag("이름을 입력하세요");
                }
                else if(phone.length() == 0){
                    showDilag("번호를 입력하세요");
                }
                else{
                    AdressInfo addressInfo = new AdressInfo();
                    addressInfo.setName(name);
                    addressInfo.setPhone(phone);

                    databaseHelper.inserAddressData(addressInfo);
                }
            }
        });

        btnShowAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddressListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }


    private void showDilag(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("알림!");
        builder.setMessage(message);
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

}
