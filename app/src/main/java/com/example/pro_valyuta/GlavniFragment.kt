package com.example.pro_valyuta

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.pro_valyuta.adapters.ViewPagerAdapter
import com.example.pro_valyuta.databinding.FragmentGlavniBinding
import com.example.pro_valyuta.databinding.FragmentHomeBinding
import com.example.pro_valyuta.databinding.TabItemBinding
import com.example.pro_valyuta.livedata.MainViewModel
import com.example.pro_valyuta.photoRetrofit.Photo
import com.example.pro_valyuta.photoRetrofit.RetrofitClientPhoto
import com.example.pro_valyuta.retrofit.RetrofitClient
import com.example.pro_valyuta.retrofit.Valyuta
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [GlavniFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GlavniFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var categoryID: Int? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            categoryID = it.getInt(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
    }
    lateinit var binding: FragmentGlavniBinding
    private val TAG = "GlavniFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGlavniBinding.inflate(layoutInflater,container,false)


        RetrofitClient.apiService.getListCurrency().enqueue(object:
            Callback<List<Valyuta>> {
            override fun onResponse(call: Call<List<Valyuta>>, response: Response<List<Valyuta>>) {
                if (response.isSuccessful){

                    val body = response.body()
                    binding.date.text = body!![categoryID!!].date
                    binding.sotilishi.text = "${body[categoryID!!].cb_price} UZS"
                    binding.olinishi.text = "${body[categoryID!!].nbu_buy_price} UZS"
                    Glide.with(binding.root.context).load("https://nbu.uz/local/templates/nbu/images/flags/${body[categoryID!!].code}.png").into(binding.simvol)








                }
            }

            override fun onFailure(call: Call<List<Valyuta>>, t: Throwable) {

            }

        })



        RetrofitClientPhoto.apiServicePhoto.getPhotoy().enqueue(object:
            Callback<Photo> {
            override fun onResponse(call: Call<Photo>, response: Response<Photo>) {
                if (response.isSuccessful){
                    Log.d(TAG, "onResponse: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<Photo>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })





        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GlavniFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(categoryID: Int,code: String) =
            GlavniFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, categoryID)
                    putString(ARG_PARAM2, code)
                }
            }
    }
}