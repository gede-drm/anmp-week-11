package com.geded.adv160420008week4.view

import android.database.Observable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.geded.adv160420008week4.R
import com.geded.adv160420008week4.databinding.FragmentStudentDetailBinding
import com.geded.adv160420008week4.util.loadImage
import com.geded.adv160420008week4.viewmodel.DetailViewModel
import com.geded.adv160420008week4.viewmodel.ListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment(), ButtonNotifClickListener, ButtonUpdateClickListener {
    private lateinit var viewModel: DetailViewModel
    private lateinit var dataBinding:FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentStudentDetailBinding>(inflater, R.layout.fragment_student_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataBinding.listenerNotif = this
        dataBinding.listenerUpdate = this
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        if(arguments != null) {
            val studentId = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentid
            viewModel.fetch(studentId)

            observeViewModel(view)
        }
    }

    fun observeViewModel(view:View){
        viewModel.studentLD.observe(viewLifecycleOwner, Observer{
//            view.findViewById<EditText>(R.id.txtID).setText(it.id)
//            view.findViewById<EditText>(R.id.txtName).setText(it.name)
//            view.findViewById<EditText>(R.id.txtDOB).setText(it.dob)
//            view.findViewById<EditText>(R.id.txtPhone).setText(it.phone)
//            var progressBarDetail = view.findViewById<ProgressBar>(R.id.progressBarDetail)
//            view.findViewById<ImageView>(R.id.imgProfileDetail).loadImage(it.photoUrl, progressBarDetail)

            dataBinding.student = it
//            val btnNotif = view.findViewById<Button>(R.id.btnNotif)
//            btnNotif.setOnClickListener {
//                io.reactivex.rxjava3.core.Observable.timer(5, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                    Log.d("Messages", "Five Seconds")
//                    MainActivity.showNotification(student.name.toString(), "A new notification created", R.drawable.ic_baseline_notifications_24)
//                }
//            }
        })
    }

    override fun onButtoNotifClickListener(v:View) {
        io.reactivex.rxjava3.core.Observable.timer(5, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("Messages", "Five Seconds")
                MainActivity.showNotification(dataBinding.student?.name.toString(), "A new notification created", R.drawable.ic_baseline_notifications_24)
            }
    }

    override fun onButtonUpdateClickListener(v:View) {
        Toast.makeText(this.context, "Update Button Clicked: "+ dataBinding.student?.name, Toast.LENGTH_SHORT).show()
    }
}