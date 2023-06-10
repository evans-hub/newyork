package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        next=findViewById(R.id.nextButton);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ApiActivity.class);
                startActivity(intent);
                finish();
            }
        });

        List<Member> members = new ArrayList<>();
        members.add(new Member("Athul Tony"));
        members.add(new Member("Gopika Ajith"));
        members.add(new Member("Christeena Kuranattu Sunny"));

        MemberAdapter adapter = new MemberAdapter(this, members);
        listView.setAdapter(adapter);
    }
}