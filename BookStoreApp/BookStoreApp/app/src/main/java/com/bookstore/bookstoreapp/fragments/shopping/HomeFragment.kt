package com.bookstore.bookstoreapp.fragments.shopping

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bookstore.bookstoreapp.R
import com.bookstore.bookstoreapp.adapters.HomeViewerPagerAdapter
import com.bookstore.bookstoreapp.databinding.FragmentHomeBinding
import com.bookstore.bookstoreapp.fragments.categories.PhilosophyFragment
import com.bookstore.bookstoreapp.fragments.categories.HistoryFragment
import com.bookstore.bookstoreapp.fragments.categories.HorrorFragment
import com.bookstore.bookstoreapp.fragments.categories.MainCategoryFragment
import com.bookstore.bookstoreapp.fragments.categories.MysteryFragment
import com.bookstore.bookstoreapp.fragments.categories.ScienceFictionFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoriesFragments = arrayListOf<Fragment>(
            MainCategoryFragment(),
            HorrorFragment(),
            HistoryFragment(),
            MysteryFragment(),
            ScienceFictionFragment(),
            PhilosophyFragment()
        )

        val viewPagerAdapter = HomeViewerPagerAdapter(categoriesFragments, childFragmentManager, lifecycle)
        binding.viewPagerHome.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPagerHome){ tab, position ->

            when(position){
                0 -> tab.text = "Home"
                1 -> tab.text = "Horror"
                2 -> tab.text = "History"
                3 -> tab.text = "Mystery"
                4 -> tab.text = "Science Fiction"
                5 -> tab.text = "Philosophy"
            }
        }.attach()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    val textView = tab.view.findViewById<TextView>(com.google.android.material.R.id.action_bar_title)
                    textView?.setTextAppearance(requireContext(), R.style.TabSelectedText)  // Set bold and blue color
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.let {
                    val textView = tab.view.findViewById<TextView>(com.google.android.material.R.id.action_bar_title)
                    textView?.setTextAppearance(requireContext(), R.style.TabUnselectedText)  // Set normal and gray color
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
}