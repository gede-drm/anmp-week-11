package com.geded.adv160420008week4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.geded.adv160420008week4.R
import com.geded.adv160420008week4.viewmodel.DetailViewModel
import com.geded.adv160420008week4.viewmodel.ListViewModel

class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch()

        observeViewModel(view)
    }

    fun observeViewModel(view:View){
        viewModel.studentLD.observe(viewLifecycleOwner, Observer{
            view.findViewById<EditText>(R.id.txtID).setText(it.id)
            view.findViewById<EditText>(R.id.txtName).setText(it.name)
            view.findViewById<EditText>(R.id.txtDOB).setText(it.dob)
            view.findViewById<EditText>(R.id.txtPhone).setText(it.phone)
        })
    }
}