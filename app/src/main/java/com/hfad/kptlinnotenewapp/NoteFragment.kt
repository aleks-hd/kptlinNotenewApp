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


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class NoteFragment : Fragment() {
    lateinit var name: EditText
    lateinit var description: EditText
    lateinit var clearBtn: Button
    lateinit var addBtnNote: Button
    lateinit var arrayList: ArrayList<Note>
    lateinit var adapternotes:AdapterNote
    lateinit var recyclerView:RecyclerView
    private var param1: String? = null
    private var param2: String? = null

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
        getNoteData();
        val linearLayoutManager = LinearLayoutManager(this.context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        var adapterNote = AdapterNote(arrayList)
        recyclerView.adapter = adapterNote


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
            var note: Note = Note(title, description)
            arrayList.add(note)

        }
        Log.d("arraySIZE", "${arrayList.size}")
    }
}


