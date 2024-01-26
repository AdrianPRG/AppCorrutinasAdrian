package com.alopgal962.corrutinas.VM

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VMCorrutina:ViewModel() {

    //Si es verdadero es rojo, si es falso es azul
    private var _quecolor by mutableStateOf(false)

    //Numero de veces que se ha pulsado
    private var _numapis by mutableStateOf(0)

    //Texto que muestra el numero de veces llamada la api
    private var _texto by mutableStateOf("")


    //Cambia el booleano para que ahora se devuelva el otro color
    fun cambiacolor(){
        _quecolor=!_quecolor
    }

    //Se crea una variable color, que sera rojo o azul segun el estado de la variable 'quecolor'
    //Este se devuelve y el boton 1 recibe por parametros el color
    fun coloractual():Color{
        var color:Color
        when (_quecolor){
            true -> color = Color.Red
            false -> color = Color.Blue
        }
        return color
    }

    //Devuelve el texto segun el numero de veces llamada a la api
    fun textoactual():String{
        if (_numapis<1){
            _texto=""
        }
        return _texto
    }

//Bloqueo de la app 5 segundos pudiendo seguir con el funcionamiento del otro boton
    fun fetchData() {
        _numapis+=1
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                delay(5000)
                "Respuesta de la API $_numapis"
            }
            _texto=result
        }
    }




}