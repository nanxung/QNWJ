package com.example.chen.qnwj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private TextView editText;
    private ArrayList<String> l1;
    public static ArrayList<String> l2;
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        read();
        ArrayAdapter<String> adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,l1);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in=new Intent(MainActivity.this,web.class);
                in.putExtra("position",position);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                startActivity(in);
            }
        });

    }
    private void read(){
        try{
            InputStream in = getResources().openRawResource(R.raw.easy);
            int length=in.available();
            BufferedReader br=new BufferedReader(new InputStreamReader(in));

//            byte[] buffer=new byte[length];
//            in.read(buffer);
//            String ss=buffer.toString();
            String s="";
            while((s=br.readLine())!=null){
                String[] sa=s.split(",");
                l1.add(sa[0].substring(6));
                l2.add(sa[1].substring(4));
            }
            in.close();
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initUI(){
        list=(ListView)findViewById(R.id.list);
        l1=new ArrayList<>();
        l2=new ArrayList<>();
        editText=(TextView)findViewById(R.id.text);
    }
}
