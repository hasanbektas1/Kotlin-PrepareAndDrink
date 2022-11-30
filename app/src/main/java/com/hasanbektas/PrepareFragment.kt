package com.hasanbektas

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.hasanbektas.prepareanddrink.*
import com.hasanbektas.prepareanddrink.databinding.FragmentPrepareBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_drink.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PrepareFragment : Fragment() {

    private var _binding: FragmentPrepareBinding? = null
    private val binding get() = _binding!!

    private lateinit var detailData : ArrayList<DrinkData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailData = ArrayList<DrinkData>()

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentPrepareBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

       arguments?.let {

            val data = PrepareFragmentArgs.fromBundle(it).liste

           binding.textView.text = "Drink: ${data.strDrink}"
           binding.textView3.text = data.strCategory
           binding.textView5.text = data.strGlass
           binding.textView7.text = data.strAlcoholic
           binding.textView8.text = "Ingredient: ${data.strIngredient1}, ${data.strIngredient2}, ${data.strIngredient3}"
           binding.textView9.text = "Measure: ${data.strMeasure1}, ${data.strMeasure2}, ${data.strMeasure3}"
           binding.textView10.text = "Instructions: ${data.strInstructions}"
           Picasso.get().load(data.strDrinkThumb).into(binding.imageView)

           binding.button.setOnClickListener {

               val action = PrepareFragmentDirections.actionPrepareFragmentToDrinkFragment()
               Navigation.findNavController(it).navigate(action)
           }

        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}