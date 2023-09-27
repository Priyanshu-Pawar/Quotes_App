package com.example.quotesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuotesListAdapter(val context: Context, val list: List<QuotesResponse>, val listener: CopyListener) : RecyclerView.Adapter<QuotesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.list_quotes, parent, false)
        return QuotesViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        holder.textQuotes.text = list[position].text
        holder.textAuthorName.text = list[position].author
        holder.btnCopy.setOnClickListener{
            listener.onCopyClicked(list[holder.adapterPosition].text)
        }
    }
}

class QuotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var  textQuotes : TextView = itemView.findViewById(R.id.text_quotes)
    var  textAuthorName : TextView = itemView.findViewById(R.id.text_author_name)
    var  btnCopy : Button = itemView.findViewById(R.id.btn_copy)

}