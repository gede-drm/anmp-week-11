package com.geded.adv160420008week4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.geded.adv160420008week4.model.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val studentLD = MutableLiveData<Student>()
    val TAG= "volleyTag"
    private var queue: RequestQueue? = null

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    fun fetch(studentId:String){
        queue = Volley.newRequestQueue(getApplication())

        var studentId = studentId
        val url = "http://adv.jitusolution.com/student.php?id=$studentId"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object: TypeToken<Student>() { }.type
                val result = Gson().fromJson<Student>(it, sType)
                studentLD.value = result

                Log.d("showvolley", it)
            },
            {
                Log.d("showvolley", it.toString())
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
//        val student = Student("16055","Nonie","1998/03/28","5718444778","http://dummyimage.com/75x10.jpg/cc0000/ffffff")
//        studentLD.value = student
    }
}