package com.example.testapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.testapp.apiundco.Unterrasse
import com.example.testapp.databinding.TestScreenBinding
import com.example.testapp.viewmodel.TestViewModel
import okhttp3.internal.notify
import java.util.Collections


class TestFragment(


) : Fragment() {

    private lateinit var binding: TestScreenBinding
    val viewModel: TestViewModel by activityViewModels()
    private  var selectedRace = Unterrasse()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TestScreenBinding.inflate(layoutInflater)
        viewModel.getRasse()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel.rassen.observe(viewLifecycleOwner) {
            try {
                setupSpinner(it) // Funktion zum Einrichten des Spinners aufrufen
                spinnerChoice(it)

            } catch (e: Exception) {
              Log.e("TestFragment", "Hier ist ein Funktionsfehler")
            }
        }
        binding.btAntwort.setOnClickListener {
            findNavController().navigate(R.id.bewegungsFragment)
        }
    }
    private fun setupSpinner(dataList: List<Unterrasse>) {
        Log.e("Test", "Wahl")
        val raceList = dataList.map { it.raceName }
        val spinner = binding.spRasse
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            raceList
        )
        spinner.adapter = adapter
    }
    fun spinnerChoice(dataList: List<Unterrasse>){
        Log.e("Test", "hören")
        binding.spRasse.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                var emptyRace = Unterrasse(id = 0, raceName = "Wähle eine Rasse")
                var newList = dataList.toMutableList()
                newList.add(0,emptyRace)
                var arrytest = newList.toTypedArray()

                selectedRace = arrytest[position]

                var werteCounter = binding.tvPunkte.text.toString().toInt()
                var staerke = binding.tvStaerke.text.toString().toInt()
                var newStaerke = staerke + selectedRace.strenght.toInt()
                binding.tvStaerke.text = newStaerke.toString()
                binding.ibWertePlus.setOnClickListener {
                if (newStaerke < 10 && werteCounter > 0) {
                    newStaerke++
                    werteCounter--
                    binding.tvStaerke.text = newStaerke.toString()
                    binding.tvPunkte.text = werteCounter.toString()
                } else {
                }

            }
                binding.ibWerteMinus.setOnClickListener {
                if (staerke > 1) {
                    newStaerke--
                    werteCounter++
                    binding.tvStaerke.text = newStaerke.toString()
                    binding.tvPunkte.text = werteCounter.toString()
                } else {
                }
            }

        }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }


    }
}


//Peace