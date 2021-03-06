package com.android.greimul.exammaker.setview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.greimul.exammaker.R
import kotlinx.android.synthetic.main.setview_item.view.*
import java.io.File
import java.nio.file.Path

class SetListAdapter internal constructor(context:Context): RecyclerView.Adapter<SetListAdapter.SetViewHolder>(){
    private val inflater:LayoutInflater =LayoutInflater.from(context)
    private var sets = emptyList<File>()
    inner class SetViewHolder(itemView: View):RecyclerView. ViewHolder(itemView){
        val setItemView: TextView = itemView.showSetBt
    }

    interface SetClick{
        fun onClick(view:View,pos:Int,file:File)
    }
    var setClick:SetClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetViewHolder {
        val itemView = inflater.inflate(R.layout.setview_item,parent,false)
        return SetViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: SetViewHolder, position: Int) {
        val current = sets[position]
        holder.setItemView.text = current.nameWithoutExtension
        if(setClick!=null) {
            holder.itemView.showSetBt.setOnClickListener {
                setClick?.onClick(it, position, current)
            }
        }
    }
    internal fun setSets(sets:List<File>){
        this.sets = sets
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = sets.size

}