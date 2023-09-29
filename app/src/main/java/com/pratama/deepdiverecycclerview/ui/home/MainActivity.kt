package com.pratama.deepdiverecycclerview.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pratama.deepdiverecycclerview.R
import com.pratama.deepdiverecycclerview.ui.home.adapter.HomeAdapter
import com.pratama.deepdiverecycclerview.ui.home.adapter.HomeListener
import com.pratama.deepdiverecycclerview.ui.home.model.Person

class MainActivity : AppCompatActivity(), HomeListener {

    // step 1: inisiasi view recyclerview
    lateinit var myRv: RecyclerView
    lateinit var btnTambah: Button
    lateinit var rvAdapter: HomeAdapter
    private var listPerson = mutableListOf<Person>()
    private var lastIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRv = findViewById(R.id.rv)
        btnTambah = findViewById(R.id.btnTambah)


        // create adapter
        rvAdapter = HomeAdapter(listPerson, object : HomeListener {
            override fun onPersonDetailClick(person: Person) {
                Toast.makeText(this@MainActivity, "test", Toast.LENGTH_SHORT).show()
            }
        })

        rvAdapter = HomeAdapter(listPerson, this)

        // create layout manager
        myRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        // set adapter to rv
        myRv.adapter = rvAdapter

        // generate content
        for (i in 0..10) {
            listPerson.add(Person(title = "Title $i", name = "Name $i"))
        }

        // kasih tau ke adapter kalo datanya bertambah/berubah

        btnTambah.setOnClickListener {
            for (i in 0..10) {
                listPerson.add(Person(title = "Title $i", name = "Name $i"))
            }
            rvAdapter.notifyDataSetChanged()
            Log.d("debug", "panjang data ${listPerson.size}")
        }

    }

    override fun onPersonDetailClick(person: Person) {
        Toast.makeText(this, "person ${person.name}", Toast.LENGTH_SHORT).show()
    }

}