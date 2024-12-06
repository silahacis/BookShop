package com.bookstore.bookstoreapp.fragments.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.bookstore.bookstoreapp.databinding.FragmentIntroductionBinding


class IntroductionFragment : Fragment() {

    private lateinit var binding : FragmentIntroductionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntroductionBinding.inflate(layoutInflater)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLogin.setOnClickListener {
            val loginAction = IntroductionFragmentDirections.actionIntroductionFragmentToLoginFragment()
            Navigation.findNavController(it).navigate(loginAction)
        }

        binding.buttonRegister.setOnClickListener {
            val registerAction = IntroductionFragmentDirections.actionIntroductionFragmentToRegisterFragment()
            Navigation.findNavController(it).navigate(registerAction)
        }

    }
}