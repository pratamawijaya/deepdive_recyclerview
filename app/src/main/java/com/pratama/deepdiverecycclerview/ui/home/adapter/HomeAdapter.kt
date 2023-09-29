package com.pratama.deepdiverecycclerview.ui.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pratama.deepdiverecycclerview.R
import com.pratama.deepdiverecycclerview.ui.home.MainActivity
import com.pratama.deepdiverecycclerview.ui.home.model.Person

interface HomeListener {
    fun onPersonDetailClick(person: Person)
}


class HomeAdapter(private val listData: List<Person>, val listener: HomeListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private val VIEW_SATU = 1
        private val VIEW_DUA = 2
        private val VIEW_SECTION = 3
    }


    inner class HomeViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        lateinit var tvTitle: TextView
        lateinit var tvDesc: TextView
        lateinit var btnDetail: Button

        fun bindView(person: Person) {
            // inisiasi view
            tvTitle = view.findViewById(R.id.tvTitle)
            tvDesc = view.findViewById(R.id.tvDesc)
            btnDetail = view.findViewById(R.id.btnDetail)

            tvTitle.text = person.title
            tvDesc.text = "Undangan untuk ${person.name}"

            btnDetail.setOnClickListener {
                // send action to caller
                // mengirim event ke pemanggil fungsi interface
                listener.onPersonDetailClick(person)
            }
        }
    }

    inner class HomeViewHolder2(private val view: View) : RecyclerView.ViewHolder(view) {
        lateinit var tvTitle: TextView
        lateinit var tvDesc: TextView
        lateinit var btnDetail: TextView

        fun bindView(person: Person) {
            // inisiasi view
            tvTitle = view.findViewById(R.id.tvTitle2)
            tvDesc = view.findViewById(R.id.tvDesc2)
            btnDetail = view.findViewById(R.id.btnDetail2)

            tvTitle.text = person.title
            tvDesc.text = "Undangan untuk ${person.name}"

            btnDetail.setOnClickListener {
                // send action to caller
                // mengirim event ke pemanggil fungsi interface
                listener.onPersonDetailClick(person)
            }
        }
    }

    inner class HomeSectionHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        lateinit var tvTitle: TextView

        fun bindView(text: String) {
            tvTitle = view.findViewById(R.id.tvTitleSection)
            tvTitle.text = text
        }
    }

    /*
    untuk mengatur view list tiap itemnya
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_SATU -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_list_content, parent, false)
                HomeViewHolder(view)
            }

            VIEW_SECTION -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_list_section, parent, false)
                HomeSectionHolder(view)
            }

            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_list_content_v2, parent, false)
                HomeViewHolder2(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        Log.d("debug", "get item view type dipanggil")
        return if (position == 5) {
            VIEW_SATU
        } else if (position == 10) {
            VIEW_SECTION
        } else {
            VIEW_DUA
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("debug", "on binnd view holder $position")
        when (getItemViewType(position)) {
            VIEW_SATU -> {
                val data = holder as HomeViewHolder
                data.bindView(listData[position])
            }

            VIEW_DUA -> {
                val data = holder as HomeViewHolder2
                data.bindView(listData[position])
            }

            VIEW_SECTION -> {
                val data = holder as HomeSectionHolder
                data.bindView("Section $position")
            }
        }
    }


    // berapa banyak item yang akan muncul didalam list
    override fun getItemCount(): Int {
        return listData.size
    }


}