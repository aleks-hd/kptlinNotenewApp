package com.hfad.kptlinnotenewapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

import androidx.recyclerview.widget.RecyclerView


class AdapterNote(private var array:ArrayList<Note>): RecyclerView.Adapter<AdapterNote.ViewHolder>() {

    var note = Note()
    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        lateinit var inputTextTitle : EditText
        lateinit var inputTextDescription : EditText
        lateinit var deleteNote : Button
        init {
            inputTextTitle = view.findViewById(R.id.titleItem)
            inputTextDescription = view.findViewById(R.id.DescriptionItem)
            deleteNote = view.findViewById(R.id.deleteItem)
        }
        fun initNote(note:Note){
          var  title:String = note.title1
            inputTextTitle.setText(title)
            var  descr:String = note.description1
            inputTextDescription.setText(descr)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view  = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterNote.ViewHolder, position: Int) {
        if (array.size !=0){
        note = array.get(position)
       holder.initNote(note)}
        else {
            println("пустой массив")}
    }

    override fun getItemCount()=array.size

}