package com.hfad.kptlinnotenewapp

import android.icu.text.Transliterator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView


class AdapterNote(private var array: ArrayList<Note>) :
    RecyclerView.Adapter<AdapterNote.ViewHolder>() {

    lateinit var itemClickListenerSuperMan: OnItemClickListener
    var note = Note()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var inputTextTitle: TextView
        lateinit var inputTextDescription: TextView
        lateinit var deleteNote: Button
        lateinit var itemContainer: LinearLayout

        init {
            inputTextTitle = view.findViewById(R.id.titleItem)
            inputTextDescription = view.findViewById(R.id.DescriptionItem)
            deleteNote = view.findViewById(R.id.deleteItem)
            itemContainer = view.findViewById(R.id.itemContainer)
            itemContainer.setOnClickListener {
                Log.d("click item", "Вы нажали на item")
            }
        }

        fun initNote(note: Note) {
            var title: String = note.title1
            inputTextTitle.setText(title)
            var descr: String = note.description1
            inputTextDescription.setText(descr)
        }

        fun voidlinlayItem(): LinearLayout {
            return itemContainer
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterNote.ViewHolder, position: Int) {
        if (array.size != 0) {
            note = array.get(position)
            holder.initNote(note)
        } else {
            println("пустой массив")
        }
        holder.voidlinlayItem().setOnClickListener {
            Log.d("click ITEM", "Сделали клик по ITEM")
            if (itemClickListenerSuperMan != null) {
                itemClickListenerSuperMan.onItemClick(position)
            }
        }


    }

    override fun getItemCount() = array.size


     fun SetOnClickListener(itemClickListener: OnItemClickListener): Unit {
        this.itemClickListenerSuperMan = itemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int) :Unit
    }

}