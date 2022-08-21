package com.udacity.shoestore.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLogInBinding

class LogInFragment:Fragment() {
    private lateinit var binding: FragmentLogInBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_log_in,
            container,
            false
        )
        binding.apply {
            logInButton.setOnClickListener {
                navigateTo()
            }
            createButton.setOnClickListener {
                navigateTo()
            }
        }
        return binding.root
    }

    private fun navigateTo(){
        findNavController().navigate(R.id.action_logInFragment_to_welcomeFragment)
    }
}