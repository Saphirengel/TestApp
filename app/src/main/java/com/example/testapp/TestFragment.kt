package com.example.testapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.testapp.apiundco.Unterrasse
import com.example.testapp.databinding.TestScreenBinding
import com.example.testapp.viewmodel.TestViewModel


class TestFragment(


) : Fragment() {

    private lateinit var binding: TestScreenBinding
    val viewModel: TestViewModel by activityViewModels()
    private  var selectedRace = Unterrasse(0,"0","0","0","0","0","0","0","0")
    private var werteCounter = 10


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





        viewModel.calculatetStrenght.observe(viewLifecycleOwner){
            try {
                binding.tvStaerke.text = it.toString()
            }catch (e: Exception){}
        }
        viewModel.calculatetDextery.observe(viewLifecycleOwner){
            try {
                binding.tvGeschick.text = it.toString()
            }catch (e: Exception){}
        }
        viewModel.calculatetInti.observe(viewLifecycleOwner){
            try {
                binding.tvIntelligenz.text = it.toString()
            }catch (e: Exception){}
        }
        viewModel.calculatetConsti.observe(viewLifecycleOwner){
            try {
                binding.tvKonstitution.text = it.toString()
            }catch (e: Exception){}
        }
        viewModel.calculatetWeisheit.observe(viewLifecycleOwner){
            try {
                binding.tvWeisheit.text = it.toString()
            }catch (e: Exception){}
        }
        viewModel.calculatetCharisma.observe(viewLifecycleOwner){
            try {
                binding.tvCharisma.text = it.toString()
            }catch (e: Exception){}
        }
        viewModel.calculatetGlueck.observe(viewLifecycleOwner){
            try {
                binding.tvGlueck.text = it.toString()
            }catch (e: Exception){}
        }



        viewModel.strenght.observe(viewLifecycleOwner){
            binding.tvBeschreibung.text
        }
        binding.tvPunkte.text = werteCounter.toString()



            binding.ibWertePlus.setOnClickListener {
                if(werteCounter  > 0){viewModel.wertePlus()
                werteCounter --
                binding.tvPunkte.text = werteCounter.toString()
                }else{}
            }

        binding.ibWerteMinus.setOnClickListener {

                viewModel.wereMinus()
                werteCounter ++
                binding.tvPunkte.text = werteCounter.toString()

        }






        binding.btAntwort.setOnClickListener {
            findNavController().navigate(R.id.bewegungsFragment)
        }
    }
    private fun setupSpinner(dataList: List<Unterrasse>) {
        Log.e("Test", "Wahl")
        var dummy = Unterrasse(id = 0, raceName = "Wähle eine Rasse","5","5","5","5","5","5","5")
        var newList = dataList.toMutableList()
        newList.add(0,dummy)
        var arryList = newList.toTypedArray()
        val raceList = arryList.map { it.raceName }
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
                view: View?,
                position: Int,
                id: Long
            ) {
               var dummy = Unterrasse(id = 0, raceName = "Wähle eine Rasse","0","0","0","0","0","0","0")
                var newList = dataList.toMutableList()
                newList.add(0,dummy)
                var arryList = newList.toTypedArray()

                if (position == 0) {
                    // Beschreibungstext ausblenden und "Wähle eine Rasse" anzeigen
                    binding.tvBeschreibung.visibility = View.INVISIBLE
                    binding.ibWertePlus.visibility = View.INVISIBLE
                    binding.ibWerteMinus.visibility = View.INVISIBLE
                    binding.btAntwort.visibility = View.INVISIBLE

                } else {
                    // Beschreibungstext ausblenden und "Wähle eine Rasse" anzeigen
                    binding.tvBeschreibung.visibility = View.VISIBLE
                    binding.ibWertePlus.visibility = View.VISIBLE
                    binding.ibWerteMinus.visibility = View.VISIBLE
                    binding.btAntwort.visibility = View.VISIBLE
                }

                selectedRace = arryList[position]
                viewModel.valueCalculatet(selectedRace)
                neueStats()
                beschreibung(selectedRace)
                werteCounter = 10
                binding.tvPunkte.text = werteCounter.toString()



            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }
    fun neueStats(){
            try {
                binding.tvStaerke.text = viewModel.calculatetStrenght.value.toString()
                binding.tvGeschick.text = viewModel.calculatetDextery.value.toString()
                binding.tvIntelligenz.text = viewModel.calculatetInti.value.toString()
                binding.tvKonstitution.text = viewModel.calculatetConsti.value.toString()
                binding.tvWeisheit.text = viewModel.calculatetWeisheit.value.toString()
                binding.tvCharisma.text = viewModel.calculatetCharisma.value.toString()
                binding.tvGlueck.text = viewModel.calculatetGlueck.value.toString()
            }catch (e: Exception){}
        }
    fun beschreibung(selectedRace: Unterrasse){
        viewModel.werteBeschreibung(selectedRace)
        binding.tvBeschreibung.text ="""
            Du bist ${selectedRace.raceName.toString()} ein  der ${viewModel.strenght.value.toString()} ist dein Geschick ist ${viewModel.dextery.value.toString()}. Deine Intelligenz übertrifft ${viewModel.intelligence.value.toString()}.
            Deine Konstititon entspricht ${viewModel.constituion.value.toString()} und das angesammelte Wissen gleicht ${viewModel.wisdom.value.toString()} . Du
            hast die Ausstrahlung von ${viewModel.charisma.value.toString()} , aber was dein Glück an geht liegt es bei ${viewModel.luck.value.toString()} .
        """.trimIndent()
    }
}