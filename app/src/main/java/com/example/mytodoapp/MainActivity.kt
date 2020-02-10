package com.example.mytodoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TodoListのadapter設定
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)
        val listView : ListView = findViewById(R.id.todo_list)
        listView.adapter = adapter

        // 追加ボタンクリック時の処理
        add_button.setOnClickListener {
            // todoのテキストを取得
            if(edit_text.text != null){
                // 取得したテキストをadapterに追加
                adapter.insert(edit_text.text.toString(), adapter.count)
                adapter.notifyDataSetChanged()
            }
        }
    }
}
