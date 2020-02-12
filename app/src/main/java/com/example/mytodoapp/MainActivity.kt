package com.example.mytodoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TodoListのadapterを設定
        val adapter = MyArrayAdapter(this, 0)
        val listView: ListView = findViewById(R.id.todo_list)
        listView.adapter = adapter

        // 追加ボタンクリック時の処理
        add_button.setOnClickListener {
            // todoのテキストを取得
            if(edit_text.text != null){
                // 取得したテキストをadapterに追加
                adapter.insert(ListItem(edit_text.text.toString()), adapter.count)
                adapter.notifyDataSetChanged()
                // テキストクリア
                edit_text.text.clear()
            }
        }
    }
}

// リスト項目のデータ
class ListItem(val title : String) {

    var description : String = "No description"

    constructor(title: String, description: String) : this(title) {
        this.description = description
    }
}

// リスト項目を再利用するためのホルダー
data class ViewHolder(val checkBox: CheckBox, val titleView: TextView, val deleteIcon: ImageButton)

// 自作のリスト項目データを扱えるようにした ArrayAdapter
class MyArrayAdapter(context: Context, resource: Int) : ArrayAdapter<ListItem>(context, resource) {

    private var inflater: LayoutInflater? =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var viewHolder: ViewHolder? = null
        var view = convertView

        // 再利用の設定
        if (view == null) {

            view = inflater!!.inflate(R.layout.list_item, parent, false)

            viewHolder = ViewHolder(
                view.findViewById(R.id.checkBox),
                view.findViewById(R.id.item_title),
                view.findViewById(R.id.delete_button)
            )
            view.tag = viewHolder
        } else {
            viewHolder = view.tag as ViewHolder
        }

        // 項目の情報を設定
        val listItem = getItem(position)
        viewHolder.titleView.text = listItem!!.title
        viewHolder.deleteIcon.setOnClickListener { _ ->
            // 削除ボタンをタップしたときの処理
            this.remove(listItem)
            this.notifyDataSetChanged()
        }

        return view!!
    }
}
