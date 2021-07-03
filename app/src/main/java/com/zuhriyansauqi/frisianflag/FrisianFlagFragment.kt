package com.zuhriyansauqi.frisianflag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zuhriyansauqi.frisianflag.databinding.FragmentSlideShowBinding

class FrisianFlagFragment : Fragment() {
    private var _binding: FragmentSlideShowBinding? = null
    private val binding get() = _binding!!

    private var _currentPage = 0
    private var currentPage
        get() = _currentPage
        set(value) {
            _currentPage = value
            gotoPage(_currentPage)
        }

    private lateinit var assets: List<Int>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSlideShowBinding.inflate(inflater, container, false)
        assets = loadAssets(requireContext(), R.array.ff_assets)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gotoPage(currentPage)

        with(binding.pagination) {
            numberOfItems = assets.size
            selectedItem = 1

            registerListener(object : PaginationView.OnTouchListener {
                override fun onItemTouched(page: Int) {
                    selectedItem = page
                    currentPage = page - 1
                }
            })
        }

        binding.prevButton.setOnClickListener { currentPage -= 1 }
        binding.nextButton.setOnClickListener { currentPage += 1 }
    }

    private fun gotoPage(page: Int) {
        binding.slideAsset.setImageResource(assets[page])
        when (currentPage) {
            0 -> binding.prevButton.visibility = View.GONE
            assets.size - 1 -> binding.nextButton.visibility = View.GONE
            else -> {
                binding.prevButton.visibility = View.VISIBLE
                binding.nextButton.visibility = View.VISIBLE
            }
        }
        binding.pagination.selectedItem = currentPage + 1
    }
}
