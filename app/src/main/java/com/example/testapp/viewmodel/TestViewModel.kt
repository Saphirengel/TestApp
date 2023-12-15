package com.example.testapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.testapp.apiundco.RassenApi
import com.example.testapp.apiundco.TestRepository
import com.example.testapp.apiundco.Unterrasse
import kotlinx.coroutines.launch

class TestViewModel(application: Application): AndroidViewModel(application) {


    private val repository = TestRepository(RassenApi)

    val rassen = repository.rassenWerter

    private var _calculatetStrenght = MutableLiveData<Int>()
     val calculatetStrenght: LiveData<Int>
        get() = _calculatetStrenght

    private var _calculatetDextery = MutableLiveData<Int>()
    val calculatetDextery: LiveData<Int>
        get() = _calculatetDextery

    private var _calculatetInti = MutableLiveData<Int>()
    val calculatetInti: LiveData<Int>
        get() = _calculatetInti

    private var _calculatetConsti = MutableLiveData<Int>()
    val calculatetConsti: LiveData<Int>
        get() = _calculatetConsti

    private var _calculatetWeisheit = MutableLiveData<Int>()
    val calculatetWeisheit: LiveData<Int>
        get() = _calculatetWeisheit

    private var _calculatetCharisma = MutableLiveData<Int>()
    val calculatetCharisma: LiveData<Int>
        get() = _calculatetCharisma

    private var _calculatetGlueck = MutableLiveData<Int>()
    val calculatetGlueck: LiveData<Int>
        get() = _calculatetGlueck

    private var _strenght = MutableLiveData<String>()
    val strenght: LiveData<String>
        get() = _strenght

    private var _dextery = MutableLiveData<String>()
    val dextery: LiveData<String>
        get() = _dextery

    private var _intelligence = MutableLiveData<String>()
    val intelligence: LiveData<String>
        get() = _intelligence

    private var _constituion = MutableLiveData<String>()
    val constituion: LiveData<String>
        get() = _constituion

    private var _wisdom = MutableLiveData<String>()
    val wisdom: LiveData<String>
        get() = _wisdom

    private var _charisma = MutableLiveData<String>()
    val charisma: LiveData<String>
        get() = _charisma

    private var _luck = MutableLiveData<String>()
    val luck: LiveData<String>
        get() = _luck


//------------------------------------------alle funs
    fun getRasse(){
        viewModelScope.launch{
            repository.getRassen()}
    }

    fun valueCalculatet(selectedRace: Unterrasse){
        viewModelScope.launch {
            var dummy = Unterrasse(id = 0, raceName = "Wähle eine Rasse","5","5","5","5","5","5","5")
            _calculatetStrenght.value = dummy.strenght.toInt() + selectedRace.strenght.toInt()
            _calculatetDextery.value = dummy.dexteriey.toInt() + selectedRace.dexteriey.toInt()
            _calculatetInti.value = dummy.intelligence.toInt() + selectedRace.intelligence.toInt()
            _calculatetConsti.value = dummy.constituion.toInt() + selectedRace.constituion.toInt()
            _calculatetWeisheit.value = dummy.wisdom.toInt() + selectedRace.wisdom.toInt()
            _calculatetCharisma.value = dummy.charisma.toInt() + selectedRace.charisma.toInt()
            _calculatetGlueck.value = dummy.luck.toInt() + selectedRace.luck.toInt()

        }
    }

    fun werteBeschreibung(selectedRace: Unterrasse){

        var staerke = selectedRace.strenght.toInt()
        var geschick = selectedRace.dexteriey
        var inti = selectedRace.intelligence
        var consti = selectedRace.constituion
        var weisheit = selectedRace.wisdom
        var charisma = selectedRace.charisma
        var glueck = selectedRace.luck

// API Werte -> String -> Int -> String
        _strenght.value = when {
            _calculatetStrenght.value!! > 15 -> {
                "Herkules"
            }
            _calculatetStrenght.value!! > 10 -> {
                "ein Starker"
            }
            _calculatetStrenght.value!! > 5 -> {
                "Normalo"
            }

            _calculatetStrenght.value!! == 5 -> {
                "ein alter Mensch"
            }
            _calculatetStrenght.value!! <= 3 -> {
                "ein an Muskelschwund leidender Opa"
            }
            else -> {"Nichts"}
        }

        _dextery.value = when{
            _calculatetDextery.value!! > 15 -> {
                "Schattenhaft"}


            _calculatetDextery.value!! > 10 -> {
                "Elfengleich"}


            _calculatetDextery.value!! > 5 -> {
                "zumindest da"
            }


            _calculatetDextery.value!! == 5 -> {
                "du zerbrichst nur 5 von 10 Tassen" }


            _calculatetDextery.value!! <=3 -> {
                "nicht vorhanden"}
            else -> {"Nichts"}
        }

        _intelligence.value = when{
            _calculatetInti.value!! > 15 -> {
                "die vom Universum"}
            _calculatetInti.value!! > 10 -> {
            "von Gott"}
            _calculatetInti.value!! > 5 -> {
            "von einem Bauern"
        }
            _calculatetInti.value!! == 5 -> {
            "niemand"}
            _calculatetInti.value!! <=3 -> {
            "nicht mal dich selbst"}
        else -> {"Nichts"}
    }

    _constituion.value = when{
        _calculatetConsti.value!! > 15 -> {
            "Hermes"}
        _calculatetConsti.value!! > 10 -> {
            "einem Marathonläufer"}
        _calculatetConsti.value!! > 5 -> {
            "du kommst zumindest nicht aus der Puste"}
        _calculatetConsti.value!! == 5 -> {
            "einem Asthma kranken"}
        _calculatetConsti.value!! <= 3 -> {
            "einem Sterbenden"}
        else -> {"Nichts"}
    }

        _wisdom.value = when{
            _calculatetWeisheit.value!! > 15 -> {"eines Allwissenden"}
            _calculatetWeisheit.value!! > 10 -> {"einer Bibliothek"}
            _calculatetWeisheit.value!! > 5 ->{"einem Normalo"}
            _calculatetWeisheit.value!! == 5 -> {"einem der gerade weiß wo die Schlüssel sind"}
            _calculatetWeisheit.value!! <= 3 -> {"einem der gerade so weiß was für ein Tag ist"}
            else -> {"Nichts"}
        }

        _charisma.value = when{
            _calculatetCharisma.value!! > 15 -> {"Aphrodite"}
            _calculatetCharisma.value!! > 10 -> {"Cassanova"}
            _calculatetCharisma.value!! > 5 -> {"einem durchschnitts Bauer"}
            _calculatetCharisma.value!! == 5 -> {"einem Ork"}
            _calculatetCharisma.value!! <= 3 -> {"einem Troll"}
            else -> {"Nichts"}
        }

        _luck.value = when{
            _calculatetGlueck.value!! > 15 ->{"den Glückspilzen"}
            _calculatetGlueck.value!! > 10 ->{"4 blättrigen Kleeblättern"}
            _calculatetGlueck.value!! > 5 ->{"zumindest vorhanden"}
            _calculatetGlueck.value!! == 5 ->{"du solltest kein Glücksspiel wagen"}
            _calculatetGlueck.value!! <= 3 ->{"reden wir nciht drüber"}
            else -> {"Nichts"}
        }
    }
}