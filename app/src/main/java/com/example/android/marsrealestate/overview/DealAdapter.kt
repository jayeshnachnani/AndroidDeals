package com.example.android.marsrealestate.overview


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsrealestate.R
import com.example.android.marsrealestate.databinding.ListItemDealBinding
import com.example.android.marsrealestate.models.Deal
//import com.udacity.asteroidradar.Asteroid
//import com.udacity.asteroidradar.R
//import com.udacity.asteroidradar.databinding.ListItemAsteroidBinding


class DealAdapter (val clickListener: DealListener): RecyclerView.Adapter<DealAdapter.ViewHolder>()

{
    //var data = listOf<Asteroid>()
    /*val asteroid1 = Asteroid(1L,"rumpelstiltskin","sec",1.6,1.7,
            1.8,1.9,true)
    val asteroid2 = Asteroid(1L,"rumpelstiltskin1000","sec",1.6,1.7,
            1.8,1.9,true)
    val asteroid3 = Asteroid(1L,"rumpelstiltskinrumpelstiltskin","sec",1.6,1.7,
            1.8,1.9,true)*/
    var dealList = mutableListOf<Deal>()

        set(value) {
            field = value
            notifyDataSetChanged()
        }

    init {
        //asteroidList.add(asteroid1)
        //asteroidList.add(asteroid2)
        //asteroidList.add(asteroid3)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
                .inflate(R.layout.list_item_deal, parent, false)
        return ViewHolder.from(parent)
    }

    override fun getItemCount()= dealList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dealList[position]
        val res = holder.itemView.context.resources
        holder.dealname.text = item.dealName
        holder.interestRate.text = "Interest Rate:" + item.interestRate.toString() + "%"
        holder.bind(clickListener,item)
    }
    //class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    class ViewHolder private constructor(val binding: ListItemDealBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: DealListener, item: Deal) {
            //binding.deal = item
            //binding.
            binding.deal = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemDealBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
        val dealname: TextView = itemView.findViewById(R.id.deal_name)
        val interestRate: TextView = itemView.findViewById(R.id.interest_rate)

    }
}
class DealListener(val clickListener: (deal: Deal) -> Unit) {
    fun onClick(deal: Deal) = clickListener(deal)
}
