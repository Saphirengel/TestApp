package com.example.testapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.testapp.apiundco.Unterrasse
import com.example.testapp.databinding.TestScreenBinding
import com.example.testapp.viewmodel.TestViewModel


class TestFragment(


) : Fragment() {

    private lateinit var binding: TestScreenBinding
    val viewModel: TestViewModel by activityViewModels()
    private  var selectedRace: Unterrasse? = null


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
                // Fehlerbehandlung für den API-Aufruf
                Toast.makeText(requireContext(), "Fehler beim Laden der Rassen", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        // lesen der daten im spinner(rasse) -> den zusätzlichen wert(strength) -> plus diesem wert

        var werteCounter = binding.tvPunkte.text.toString().toInt()
        var staerke = binding.tvStaerke.text.toString().toInt()
        var newStaerke = staerke
        Log.e("Test", "Werte")
        if(selectedRace?.strenght != null){
            Log.e("Test", "rechnen")
            newStaerke = staerke + (selectedRace!!.strenght).toInt()
            binding.ibWertePlus.setOnClickListener {
                if (werteCounter > 0) {
                    staerke++
                    werteCounter--
                    binding.tvStaerke.text = staerke.toString()
                    binding.tvPunkte.text = werteCounter.toString()
                } else {
                }

            }
            binding.ibWerteMinus.setOnClickListener {
                if (staerke > 0) {
                    staerke--
                    werteCounter++
                    binding.tvStaerke.text = staerke.toString()
                    binding.tvPunkte.text = werteCounter.toString()
                } else {
                }
            }
        }
        binding.tvStaerke.text = newStaerke.toString()




        val geschick = binding.tvGeschick.text.toString().toInt()






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
            ) { selectedRace = dataList[position]}

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedRace = dataList.first()
            }
        }
    }
}