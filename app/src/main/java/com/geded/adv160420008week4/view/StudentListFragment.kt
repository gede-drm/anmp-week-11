package com.geded.adv160420008week4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.geded.adv160420008week4.R
import com.geded.adv160420008week4.viewmodel.ListViewModel

class StudentListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val studentListAdapter = StudentListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        val recViewStudent = view.findViewById<RecyclerView>(R.id.recViewStudent)
        recViewStudent.layoutManager = LinearLayoutManager(context)
        recViewStudent.adapter = studentListAdapter

        observeViewModel(view)

        val refreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout)
        refreshLayout.setOnRefreshListener {
            view.findViewById<RecyclerView>(R.id.recViewStudent).visibility = View.GONE
            view.findViewById<TextView>(R.id.txtError).visibility = View.GONE
            view.findViewById<ProgressBar>(R.id.progressLoad).visibility = View.VISIBLE
            viewModel.refresh()

            refreshLayout.isRefreshing = false
        }
    }

    fun observeViewModel(view:View){
        viewModel.studentsLD.observe(viewLifecycleOwner, Observer{
            studentListAdapter.updateStudentList(it)
        })
        viewModel.studentLoadErrorLD.observe(viewLifecycleOwner, Observer{
            if(it==true){
                view.findViewById<TextView>(R.id.txtError).visibility = View.VISIBLE
            }
            else{
                view.findViewById<TextView>(R.id.txtError).visibility = View.GONE
            }
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer{
            if(it==true){
                view.findViewById<RecyclerView>(R.id.recViewStudent).visibility = View.GONE
                view.findViewById<ProgressBar>(R.id.progressLoad).visibility = View.VISIBLE
            }
            else{
                view.findViewById<RecyclerView>(R.id.recViewStudent).visibility = View.VISIBLE
                view.findViewById<ProgressBar>(R.id.progressLoad).visibility = View.GONE
            }
        })
    }
}