package com.example.pro_valyuta

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.pro_valyuta.adapters.AllAdapter
import com.example.pro_valyuta.adapters.ViewPagerAdapter
import com.example.pro_valyuta.databinding.FragmentCurrencyBinding
import com.example.pro_valyuta.databinding.FragmentHomeBinding
import com.example.pro_valyuta.livedata.MainViewModel
import com.example.pro_valyuta.retrofit.RetrofitClient
import com.example.pro_valyuta.retrofit.Valyuta
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CurrencyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CurrencyFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentCurrencyBinding
    lateinit var allAdapter: AllAdapter
    private lateinit var tempArrayList: ArrayList<Valyuta>
    lateinit var mainViewModel: MainViewModel
    lateinit var list: ArrayList<Valyuta>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCurrencyBinding.inflate(layoutInflater,container,false)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)




        RetrofitClient.apiService.getListCurrency().enqueue(object:
            Callback<List<Valyuta>> {
            override fun onResponse(call: Call<List<Valyuta>>, response: Response<List<Valyuta>>) {
                if (response.isSuccessful) {

                    val body = response.body()
                    list = ArrayList()
                    list.addAll(body!!)

                    tempArrayList = ArrayList()
                    tempArrayList.addAll(list)

                        allAdapter = AllAdapter(tempArrayList,object : AllAdapter.OnItemClickListner{
                            override fun onItemClick(valyuta: Valyuta, position: Int) {
                                val bundle = Bundle()
                                bundle.putInt("position", position)
                                findNavController().navigate(R.id.calculatorFragment, bundle)
                            }

                        })
                    binding.rvAll.adapter = allAdapter



                }
            }

            override fun onFailure(call: Call<List<Valyuta>>, t: Throwable) {

            }

        })







//        mainViewModel.getCurrency().observe(requireActivity(), Observer {
//
//            list = ArrayList()
//            list.addAll(it)
//
//
//            tempArrayList = ArrayList()
//            tempArrayList.addAll(list)
//
//
//
//
//        })




        return binding.root

    }

    private fun setRv(code:String) {

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val item = menu?.findItem(R.id.search_action)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(newText: String?): Boolean {
                tempArrayList.clear()
                val searchText = newText!!.toLowerCase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    list.forEach {
                        if (it.code?.toLowerCase(Locale.getDefault())!!.contains(searchText)){

                            tempArrayList.add(it)
                        }
                    }
                    binding.rvAll.adapter!!.notifyDataSetChanged()
                }else{
                    tempArrayList.clear()
                    tempArrayList.addAll(list)
                    binding.rvAll.adapter!!.notifyDataSetChanged()
                }



                return false
            }

        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CurrencyFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CurrencyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}