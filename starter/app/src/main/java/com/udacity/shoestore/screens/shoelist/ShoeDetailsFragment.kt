package com.udacity.shoestore.screens.shoelist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.shoestore.models.Shoe

class ShoeDetailsFragment:Fragment() {
    private lateinit var binding: FragmentShoeDetailsBinding
    private lateinit var viewModel: ShoeViewModel
    private lateinit var tempShoe: Shoe
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_details,
            container,
            false
        )
        viewModel = ViewModelProvider(requireActivity()).get(ShoeViewModel::class.java)

        tempShoe = Shoe("N/A",0.0,"N/A","N/A")
        Log.d("TAG", "onCreateView: ${tempShoe.name}")
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            shoeViewModel = viewModel
            shoe = tempShoe
        }
        Log.d("TAG", "onCreateView: ${binding.shoe!!.name}")
        viewModel.eventSave.observe(viewLifecycleOwner, Observer {
            flag ->
            if(flag) {
                tempShoe = binding.shoe as Shoe
                viewModel.add(tempShoe)
                navigateTo()
                viewModel.onSaveComplete()
            }
        })

        viewModel.eventCancel.observe(viewLifecycleOwner, Observer {
                flag ->
            if(flag) {
                navigateTo()
                viewModel.onCancelComplete()
            }
        })
        return binding.root
    }

    private fun navigateTo(){
        findNavController().navigateUp()
    }
}