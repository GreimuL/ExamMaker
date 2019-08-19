package com.android.greimul.exammaker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.greimul.exammaker.db.Problems
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class ProbListAdapter internal constructor(context: Context): RecyclerView.Adapter<ProbListAdapter.ProbViewHolder>(){
    private val inflater:LayoutInflater = LayoutInflater.from(context)
    private var probs = emptyList<Problems>()

    inner class ProbViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val probItemView: TextView = itemView.itemTextView
        val probIdView:TextView = itemView.idView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProbViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return ProbViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProbViewHolder, position: Int) {
        val current = probs[position]
        holder.probItemView.text = current.title
        holder.probIdView.text = current.id.toString()
    }
    internal fun setProbs(probs:List<Problems>){
        this.probs = probs
        notifyDataSetChanged()
    }

    override fun getItemCount() = probs.size

}