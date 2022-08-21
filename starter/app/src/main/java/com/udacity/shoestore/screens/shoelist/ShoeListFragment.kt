package com.udacity.shoestore.screens.shoelist


import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.CardviewBinding
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe


class ShoeListFragment:Fragment() {
    private lateinit var binding: FragmentShoeListBinding
    private lateinit var viewModel: ShoeViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            com.udacity.shoestore.R.layout.fragment_shoe_list,
            container,
            false
        )
        viewModel = ViewModelProvider(requireActivity()).get(ShoeViewModel::class.java)
        binding.add.setOnClickListener {
            navigateTo()
        }
        setHasOptionsMenu(true)
        viewModel.list.observe(viewLifecycleOwner, Observer { shoes->
            for(shoe in shoes) {
                val cardViewBinding:CardviewBinding = DataBindingUtil.inflate(inflater,R.layout.cardview,container,false)
                cardViewBinding.shoe = shoe
                binding.linearLayout.addView(cardViewBinding.root)
            }
        })
        (requireActivity() as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(null)
        return binding.root
    }

    private fun navigateTo(){
        findNavController().navigate(com.udacity.shoestore.R.id.action_shoeListFragment_to_shoeDetailsFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.nav_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logInFragment -> findNavController().navigate(R.id.action_shoeListFragment_to_logInFragment)
        }
        return super.onOptionsItemSelected(item)
    }
}