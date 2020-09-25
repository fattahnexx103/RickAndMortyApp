package com.nexx.nexxassistant.interviewapp.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nexx.nexxassistant.interviewapp.model.Character
import com.nexx.nexxassistant.interviewapp.network.CharacterService
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class CharacterViewModel: ViewModel(){

    val characterService = CharacterService.getApiService() //get the singleton api service
    var job: Job? = null //this is to manage the co-routine

    //for logging retrofit error. we pass this to the coroutine scope
    val exceptionHandler =  CoroutineExceptionHandler{coroutineContext: CoroutineContext,throwable ->
        onError("EXCEPTION: ${throwable.localizedMessage}")
    }

    val charactersLD = MutableLiveData<Character>() //the live data of results which will be observed
    var page = 0 //page # is 0 initially and gets incremented with each api call

    fun fetchCharacters(){

        //do this on the background using a co-routine scope
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {


                page += 1 //increment page number
                val response = characterService.getCharacters(page) //get the response
                withContext(Dispatchers.Main) {

                    //switch back to main thread and update the response LiveData if successful
                    if (response.isSuccessful) {
                        charactersLD.value = response.body()

                    } else {
                        onError("ERROR: ${response.message()}") //Just log the error
                    }
                }
            }

    }

    //takes in an error of type String and simply logs it
    private fun onError(message: String){
        Log.d("ERROR: ", " $message")
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel() //cancel the co-routine job.
    }
}