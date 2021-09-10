package com.example.praktek_retrofit

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.praktek_retrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var list = ArrayList<DataIndonesia>()

    private lateinit var binding: ActivityMainBinding

    private var listProv = ArrayList<attributeData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        showMainData()

        showListProv()

        val format = "dd-MM-yyyy"
        val dateFormat = SimpleDateFormat(format)
        val date = dateFormat.format(Date())

        binding.lastUpdate.text = "Terakhir dipernaharui : $date"

    }

    private fun showMainData() {
        RetrofitClient.instase.getMain().enqueue(object : Callback<ArrayList<DataIndonesia>> {
            override fun onResponse(
                call: Call<ArrayList<DataIndonesia>>,
                response: Response<ArrayList<DataIndonesia>>
            ) {
                val connStat = response.code().toString()
                if (connStat == "200") binding.progressBar.visibility = View.GONE

                response.body()?.let { list.addAll(it) }
                val indonesia = response.body()?.get(0)

                binding.txtMainPositif.text = indonesia?.positif
                binding.txtMainSembuh.text = indonesia?.sembuh
                binding.txtMainMeninggal.text = indonesia?.meninggal
            }

            override fun onFailure(call: Call<ArrayList<DataIndonesia>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }


        })
    }

    private fun showListProv() {
        binding.rvProv.setHasFixedSize(true)
        binding.rvProv.layoutManager = LinearLayoutManager(this)

        RetrofitClient.instase.getProv().enqueue(object : Callback<ArrayList<attributeData>> {
            override fun onResponse(
                call: Call<ArrayList<attributeData>>,
                response: Response<ArrayList<attributeData>>
            ) {
                Toast.makeText(this@MainActivity, response.code().toString(), Toast.LENGTH_SHORT)
                    .show()

                response.body()?.let { listProv.addAll(it) }
                binding.rvProv.adapter = provAdapter(listProv)
            }

            override fun onFailure(call: Call<ArrayList<attributeData>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}