package com.example.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private final LinkedList<String> mWordList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 20; i++) {
            mWordList.addLast("Word " + i);
        }
        mRecyclerView = findViewById(R.id.recylerview); //Tham chiếu đến biến trong file xml
        mAdapter = new WordListAdapter(this, mWordList); //Truyền context và truyền nội dung
        mRecyclerView.setAdapter(mAdapter); //Gán recycler view sử dụng adapter
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        mWordList.clear();
        for (int i = 0; i < 20; i++) {
            mWordList.addLast("Word " + i);
        }
        mRecyclerView.setAdapter(mAdapter);
        return super.onOptionsItemSelected(item);
    }

    public void onFABClick(View view) {
        int wordListSize = mWordList.size();
        mWordList.addLast("+ Word " + wordListSize);
        mAdapter.notifyItemInserted(wordListSize);
        mRecyclerView.smoothScrollToPosition(wordListSize); //cuon cho muot
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Reset").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return super.onCreateOptionsMenu(menu);
    }
}