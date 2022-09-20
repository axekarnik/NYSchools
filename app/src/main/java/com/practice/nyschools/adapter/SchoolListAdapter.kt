package com.practice.nyschools.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practice.nyschools.data.model.School
import com.practice.nyschools.databinding.CellSchoolListItemBinding
import com.practice.nyschools.databinding.FragmentSchoolListBinding

class SchoolListAdapter(val callback: (School) -> Unit) : RecyclerView.Adapter<SchoolListAdapter.SchoolViewHolder>() {
    var schoolList : MutableList<School> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        val binding = CellSchoolListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SchoolViewHolder(binding, callback)
    }

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        val school = schoolList[position]
        holder.setData(school)
    }

    override fun getItemCount()= schoolList.size

    fun setData(data: List<School>) {
        schoolList = data as ArrayList<School>
        notifyDataSetChanged()
    }

    fun clearData() {
        schoolList.clear()
        notifyDataSetChanged()
    }

    class SchoolViewHolder(private val binding: CellSchoolListItemBinding,
    val callback: (School) -> Unit) : RecyclerView.ViewHolder(binding.root) {

        fun setData(school: School) {
            binding.schoolName.text  = school.name

            binding.root.setOnClickListener {
                callback(school)
            }
        }
    }

}