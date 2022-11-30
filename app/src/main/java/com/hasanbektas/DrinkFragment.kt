package com.hasanbektas

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hasanbektas.prepareanddrink.*
import com.hasanbektas.prepareanddrink.databinding.FragmentDrinkBinding
import kotlinx.android.synthetic.main.fragment_drink.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.zip.Inflater
import kotlin.collections.ArrayList


class DrinkFragment : Fragment() {

    private var _binding: FragmentDrinkBinding? = null
    private val binding get() = _binding!!


    private val BASE_URL = "https://www.thecocktaildb.com/"
    private lateinit var drinkModels : ArrayList<DrinkData>

    private var recyclerViewAdapter: DrinkAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        println("onCreate")

        drinkModels  = ArrayList<DrinkData>()

        loadData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDrinkBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
        // Inflate the layout for this fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireActivity())
        recyclerView.layoutManager = layoutManager
    }

    private fun loadData(){

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(DrinkAPI::class.java)
        val call = service.getData()

        call.enqueue(object : Callback<DrinkModel> {
            override fun onResponse(call: Call<DrinkModel>, response: Response<DrinkModel>) {

                if (response.isSuccessful){
                    response.body()?.let {

                        drinkModels = ArrayList(it.drinks)

                        drinkModels.let {

                            recyclerViewAdapter = DrinkAdapter(it)
                            recyclerView.adapter = recyclerViewAdapter
                        }
                    }
                }
            }
            override fun onFailure(call: Call<DrinkModel>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}