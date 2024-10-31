package com.example.gmail

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var inputSearch: EditText
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputSearch = findViewById(R.id.Search)
        listView = findViewById(R.id.ListView)

        val initResults: ArrayList<Pair<String, String>> = arrayListOf(Pair("Nguyen Van A", "MSSV: 123456"), Pair("Tran Thi B", "MSSV: 654321"), Pair("Le Van C", "MSSV: 789123"), Pair("Pham Thi D", "MSSV: 321654"), Pair("Hoang Van E", "MSSV: 987654"), Pair("Nguyen Thi F", "MSSV: 456789"), Pair("Tran Van G", "MSSV: 654987"), Pair("Le Thi H", "MSSV: 321987"), Pair("Pham Van I", "MSSV: 987321"))

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, initResults)
        listView.adapter = adapter

        inputSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length!! <= 2) {
                    listView.adapter = adapter;
                    return;
                }

                val changedResult = initResults.filter { it.first.contains(s.toString(), ignoreCase = true) || it.second.contains(s.toString(), ignoreCase = true) }

                val newAdapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_2, android.R.id.text1, changedResult.map { "${it.first}: ${it.second}" })
                listView.adapter = newAdapter
            }
        })


    }
}
