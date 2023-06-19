package com.example.sudhashree

import android.R.layout
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.sudhashree.db.Mobile


class MyAdapter (val items : List<Mobile>, var context: Context) : RecyclerView.Adapter<MyAdapter.myViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val infalter= LayoutInflater.from(parent.context)
        val view =infalter.inflate(R.layout.layout,parent,false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
            holder.tvModel.text= items[position].mobileModel
            holder.tvPrice.text= items[position].mobilePrice.toString()
            holder.tvQuantity_white.text= items[position].quantityWhite.toString()
            holder.tvQuantity_black.text= items[position].quantityBlack.toString()

            var total= items[position].quantityWhite.toInt()+ items[position].quantityBlack.toInt()
            holder.tv_totalQuant.text="Qty: "+total

            holder.ll_head.setOnClickListener(View.OnClickListener {

                val intent = Intent(context,SelectEditOption::class.java)
                intent.putExtra("Modelname",items[position].mobileModel)
                intent.putExtra("Modelprice",items[position].mobilePrice.toString())
                intent.putExtra("ModelID",position+1)
                intent.putExtra("Quantity_BLACK",items[position].quantityBlack )
                intent.putExtra("Quantity_White",items[position].quantityWhite )
                context.startActivity(intent)
            })
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class myViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var tvModel = itemView.findViewById<TextView>(R.id.tv_model)
        var tvPrice= itemView.findViewById<TextView>(R.id.tv_price)
        var tvQuantity_white= itemView.findViewById<TextView>(R.id.tv_Quantity_white)
        var tvQuantity_black= itemView.findViewById<TextView>(R.id.tv_Quantity_black)
        var ll_head = itemView.findViewById<LinearLayout>(R.id.ll_head)
        var tv_totalQuant= itemView.findViewById<TextView>(R.id.tv_totalQuant)

    }
}


