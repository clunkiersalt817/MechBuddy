package com.mechbuddy.app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mechbuddy.app.R
import com.mechbuddy.app.adapter.HomeAdapter
import com.mechbuddy.app.databinding.FragmentHomeBinding
import com.mechbuddy.app.datamodel.ProductModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var productList: ArrayList<ProductModel> = ArrayList()

    private val adapter: HomeAdapter = HomeAdapter()

    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)

        getData()

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter



        return binding.root
    }

    private fun getData(){
        firestore.collection("Categories")
            .get().addOnCompleteListener {
                if(it.isSuccessful){
                    productList.clear()
                    for(doc in it.result.documents){
                        val data = doc.toObject(ProductModel::class.java)
                        productList.add(data!!)
                    }
                    adapter.updateItems(productList)
                }
                else{
                    Toast.makeText(requireContext(), it.exception.toString(), Toast.LENGTH_SHORT).show()
                }
            }

    }
}