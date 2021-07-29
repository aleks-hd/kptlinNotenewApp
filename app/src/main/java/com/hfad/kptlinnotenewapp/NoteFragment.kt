package com.hfad.kptlinnotenewapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase



private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class NoteFragment : Fragment() {
    lateinit var name: EditText
    lateinit var description: EditText
    lateinit var clearBtn: Button
    lateinit var addBtnNote: Button
    lateinit var arrayList: ArrayList<Note>
    lateinit var adapterNote: AdapterNote
    lateinit var recyclerView: RecyclerView
    private var param1: String? = null
    private var param2: String? = null
    val db = Firebase.firestore
    val NOTE_COLLECTION:String = "NewCOllections"

    val collectionReference = db.collection(NOTE_COLLECTION)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_note, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewContainer)
        initEnter(view)
        initListenerBnt(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initRecyclerView(recyclerView)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initRecyclerView(recyclerView: RecyclerView) {
        recyclerView.setHasFixedSize(true)
        //Данные с ресурсов пока отключили
       // getNoteData();
        //получение списка с БД
        getNoteDataDatabase()
        //Надо дождаться выполнения этой задачи (как по другому пока не знаю)
        Thread.sleep(3000)
        val linearLayoutManager = LinearLayoutManager(this.context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
         adapterNote = AdapterNote(arrayList)
        recyclerView.adapter = adapterNote
       adapterNote.SetOnClickListener(object : AdapterNote.OnItemClickListener{
           override fun onItemClick(position: Int) {
               Log.d("click", "вы нажали на item")
               deleteItem(position)
}

       });
        initInputDate()
    }

    private fun deleteItem(position: Int) {
        collectionReference.document(arrayList.get(position).id.toString()).delete()
            .addOnSuccessListener { Log.d("DATABASE DELETE POSITION", "Данные были успегно удалены") }
            .addOnFailureListener{Log.d("DATABASE DELETE POSITION", "Ошибка удаления из базы данных")}

        arrayList.remove(arrayList.get(position))
        adapterNote.notifyDataSetChanged()
    }

    //получение списка заметок с базы данных
    private fun getNoteDataDatabase():ArrayList<Note> {
    arrayList = ArrayList<Note>()
    val mapping:NoteDataMapping = NoteDataMapping()
    collectionReference.get()
            .addOnSuccessListener { result->
                for (document in result){
                   val notenew = Note(document.id,document.get(mapping.qwe.NAME).toString(),document.get(mapping.qwe.DESCRIPTION).toString())
                    arrayList.add(notenew)
                    Log.d("READ NOTE", "Данные успешно считаны")
                }
            }
        .addOnFailureListener{exception ->
            Log.w("READ DATABASE", "Ошибка чтения из базы данных", exception)}

    return arrayList;
    }

    //инициализация слушателя addDate
    fun initInputDate():Unit{
        addBtnNote.setOnClickListener(View.OnClickListener {
            Log.d("ADD NOTE", "слушатель OK")
            enterEditTextView()
        })
    }

//метод получения данных с полей ввода
    fun enterEditTextView():Unit{
        val nameInput = name.text.toString()
        val descriptionInput = description.text.toString()
        val noteInputDate = Note(nameInput,descriptionInput)
        arrayList.add(noteInputDate)
        addDate(noteInputDate)
        adapterNote.notifyDataSetChanged()
    }


   //метод добавления данных в бд
        fun addDate(note:Note){
            val mapping:NoteDataMapping = NoteDataMapping()

            collectionReference.add(mapping.toDocument(note))
                .addOnSuccessListener { collectionReference->
                    Log.d("DataBase ADD", "Запись успешно добавлена")}
                .addOnFailureListener{e-> Log.w("Error Add Date", "Ошибка добавления данных",e)}
        }


    private fun initListenerBnt(view: View?) {
        addBtnNote.setOnClickListener { Log.d("click", "вы нажали на кнопку добавить") }
        clearBtn.setOnClickListener { Log.d("click", "нажали на кнопку удаления") }
    }


    //инициализация переменных (уточнить про !!)
    private fun initEnter(view: View?) {
        name = view?.findViewById<EditText>(R.id.editName)!!
        description = view?.findViewById<EditText>(R.id.editDescription)
        clearBtn = view?.findViewById<Button>(R.id.deleteNote)
        addBtnNote = view?.findViewById<Button>(R.id.addNote)
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NoteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    //Получение информации с ресурсов
    private fun getNoteData() {
        var arrayTitle: Array<String> = resources.getStringArray(R.array.Arraytitle)
        var arrayDescription: Array<String> = resources.getStringArray(R.array.ArrayDescr)
        var i = 0
        arrayList = ArrayList<Note>()
        for (i in arrayTitle.indices) run {
            var title: String = arrayTitle[i]
            var description: String = arrayDescription[i]
            var note = Note(title, description)
            arrayList.add(note)

        }
        Log.d("arraySIZE", "${arrayList.size}")
    }
}


