package com.hfad.kptlinnotenewapp




class NoteDataMapping {

    class Fields(var  NAME:String="name", var DESCRIPTION:String="description"){
       }

   //Чтение данных
    val qwe = Fields()
//Чтение данных и запись в объект
    fun toNote(id:String , doc:Map<String,String>):Note{
        var id = id
        var title = doc.get(qwe.NAME).toString()
        var description = doc.get(qwe.DESCRIPTION).toString()
        var note = Note(id,title,description)
        return note
    }

    //Загрузка данных
    fun toDocument(note:Note):Map<String,String>{
       val mapNote = mutableMapOf<String,String>()
        mapNote.put(qwe.NAME,note.title1)
        mapNote.put(qwe.DESCRIPTION,note.description1)
        return mapNote

    }

}