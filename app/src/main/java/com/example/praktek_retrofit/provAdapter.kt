package com.example.praktek_retrofit

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.praktek_retrofit.databinding.ListItemProvBinding

class provAdapter(val list: ArrayList<attributeData>) :
    RecyclerView.Adapter<provAdapter.profViewHolde>() {
    inner class profViewHolde(private val binding: ListItemProvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val kartu = binding.kartuProv
        fun bind(attributeData: attributeData) {
            with(binding) {
                namaProv.text = attributeData.attributes.Provinsi
                provPositif.text = attributeData.attributes.Kasus_Posi.toString()
                provMeninggal.text = attributeData.attributes.Kasus_Meni.toString()
                provSembuh.text = attributeData.attributes.Kasus_Semb.toString()

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): profViewHolde {
        val binding =
            ListItemProvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return profViewHolde(binding)
    }

    override fun onBindViewHolder(holder: profViewHolde, position: Int) {
        val check = position % 4
        when (check) {
            0 -> holder.kartu.setCardBackgroundColor(Color.parseColor("#F28C8C"))
            1 -> holder.kartu.setCardBackgroundColor(Color.parseColor("#56A4CF"))
            3 -> holder.kartu.setCardBackgroundColor(Color.parseColor("#7573C9"))
        }
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}