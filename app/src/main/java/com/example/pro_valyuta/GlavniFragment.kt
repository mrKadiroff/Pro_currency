package com.example.pro_valyuta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"


/**
 * A simple [Fragment] subclass.
 * Use the [GlavniFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GlavniFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var categoryID: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            categoryID = it.getInt(ARG_PARAM1)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_glavni, container, false)
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
        fun newInstance(categoryID: Int) =
            GlavniFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, categoryID)
                }
            }
    }
}