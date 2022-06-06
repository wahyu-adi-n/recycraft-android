package com.example.recycraft.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recycraft.R
import com.example.recycraft.adapter.DummyHorizontalAdapter
import com.example.recycraft.adapter.DummyVerticalAdapter
import com.example.recycraft.data.model.CategoriesModel
import com.example.recycraft.data.model.TopCraftsModel
import com.example.recycraft.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private lateinit var rvCraft: RecyclerView
    private lateinit var rvCategory: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
/*
        binding?.apply {
            rvTopCrafts.setHasFixedSize(true)
            rvTopCrafts.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            rvTopCrafts.adapter = dummyHorizontalAdapter

            rvCategories.setHasFixedSize(true)
            rvCategories.layoutManager = LinearLayoutManager(activity)
            rvCategories.adapter = dummyVerticalAdapter

        }*/
        //vertical rv
        val lm = LinearLayoutManager(activity)
        lm.orientation = LinearLayoutManager.VERTICAL
        rvCraft = view.findViewById(R.id.rvTopCrafts)

        val adapterCraft = DummyVerticalAdapter(ArrayCraft, activity)
        rvCraft.setHasFixedSize(true)
        rvCraft.layoutManager = lm
        rvCraft.adapter = adapterCraft

        //horizontal rv
        val vm = LinearLayoutManager(activity)
        vm.orientation = LinearLayoutManager.HORIZONTAL
        rvCategory = view.findViewById(R.id.rvCategories)

        val adapterCategory = DummyHorizontalAdapter(ArrayCategory, activity)
        rvCategory.setHasFixedSize(true)
        rvCategory.layoutManager = vm
        rvCategory.adapter = adapterCategory

        return view
    }

    private val ArrayCraft: ArrayList<TopCraftsModel>
        get() {
            val dataTitle = resources.getStringArray(R.array.titlesCraft)
            val dataCategoryCraft = resources.getStringArray(R.array.categoryCraft)
            val arrayCraft = ArrayList<TopCraftsModel>()
            for (i in dataTitle.indices) {
                val craft = TopCraftsModel(
                    R.drawable.kerajinanlampion,
                    dataTitle[i],
                    dataCategoryCraft[i]
                )
                arrayCraft.add(craft)
            }
            return arrayCraft
        }

    private val ArrayCategory: ArrayList<CategoriesModel>
        get() {
            val dataTitleCategory = resources.getStringArray(R.array.titlesCategories)
            val arrayCategory = ArrayList<CategoriesModel>()
            for (i in dataTitleCategory.indices) {
                val category = CategoriesModel(
                    R.drawable.sampahoverlay,
                    dataTitleCategory[i],
                )
                arrayCategory.add(category)
            }
            return arrayCategory
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


