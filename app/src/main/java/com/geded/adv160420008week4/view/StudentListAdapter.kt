package com.geded.adv160420008week4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.geded.adv160420008week4.R
import com.geded.adv160420008week4.databinding.StudentListLayoutBinding
import com.geded.adv160420008week4.model.Student
import com.geded.adv160420008week4.util.loadImage

class StudentListAdapter(val studentList:ArrayList<Student>):RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>()
{
    class StudentViewHolder(var view:StudentListLayoutBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<StudentListLayoutBinding>(inflater, R.layout.student_list_layout, parent, false)

        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.view.student = studentList[position]
//        val imgProfile = holder.view.findViewById<ImageView>(R.id.imgProfile)
//        val txtID = holder.view.findViewById<TextView>(R.id.txtID)
//        val txtName = holder.view.findViewById<TextView>(R.id.txtName)
//        val btnDetail = holder.view.findViewById<TextView>(R.id.btnDetail)

//        imgProfile.setImageURI(studentList[position].photoUrl)
//        txtID.text = studentList[position].id
//        txtName.text = studentList[position].name

//        btnDetail.setOnClickListener{
//            val action = StudentListFragmentDirections.actionStudentDetail(studentList[position].id.toString())
//            Navigation.findNavController(it).navigate(action)
//        }

//        var imageView = holder.view.findViewById<ImageView>(R.id.imgProfile)
//        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarList)
//        imageView.loadImage(studentList[position].photoUrl, progressBar)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    fun updateStudentList(newStudentList:ArrayList<Student>){
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}