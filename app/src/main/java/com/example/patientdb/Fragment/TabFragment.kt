package com.example.patientdb.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.patientdb.databinding.FragmentTabBinding
import com.google.android.material.tabs.TabLayoutMediator
import java.lang.IndexOutOfBoundsException


class TabFragment : Fragment() {
    private var _binding: FragmentTabBinding? = null
    private val binding get() = requireNotNull(_binding)

    private var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            currentPage = savedInstanceState.getInt("item")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentTabBinding.inflate(inflater, container, false)
            .also {
                _binding = it
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewPager.adapter = FragmentTabAdapter(this@TabFragment)

            viewPager.currentItem = currentPage
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = when (position) {
                    0 -> "DB"
                    1 -> "Edit"
                    else -> "Error"
                }
            }
                .attach()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("item", binding.viewPager.currentItem)
    }
}

class FragmentTabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                ListFragment()
            }
            1 -> {
                EditFragment()
            }
            else -> throw IndexOutOfBoundsException("Wrong position $position")
        }
    }

}

