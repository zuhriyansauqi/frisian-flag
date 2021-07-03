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

        binding.slideAsset.setImageResource(assets[0])

        with(binding.pagination) {
            numberOfItems = assets.size
            selectedItem = 1

            registerListener(object : PaginationView.OnTouchListener {
                override fun onItemTouched(page: Int) {
                    selectedItem = page
                    binding.slideAsset.setImageResource(assets[page - 1])
                }
            })
        }
    }
}
