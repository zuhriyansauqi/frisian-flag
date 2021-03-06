package com.zuhriyansauqi.frisianflag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.annotation.ArrayRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.labo.kaji.fragmentanimations.MoveAnimation
import com.zuhriyansauqi.frisianflag.databinding.FragmentSlideShowBinding
import com.zuhriyansauqi.frisianflag.view.PaginationView

open class SlideShowFragment(
    @ArrayRes private val assetsRes: Int,
    @ArrayRes private val backgroundsRes: Int? = null,
    @ArrayRes private val backgroundIndexesRes: Int? = null
) : Fragment() {
    private var _binding: FragmentSlideShowBinding? = null
    private val binding get() = _binding!!

    private var _currentPage = 0
    private var currentPage
        get() = _currentPage
        set(value) {
            val isPrev = _currentPage < value
            _currentPage = value
            gotoPage(_currentPage, isPrev)
        }

    private var isLoading = false
    private var isUsingCustomBackground = false

    private lateinit var assets: List<Int>
    private lateinit var backgrounds: Map<Int, Int>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSlideShowBinding.inflate(inflater, container, false)
        assets = loadAssets(requireContext(), assetsRes)

        if (backgroundsRes != null && backgroundIndexesRes != null) {
            isUsingCustomBackground = true
            backgrounds =
                loadBackgrounds(requireContext(), backgroundIndexesRes, backgroundsRes)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gotoPage(currentPage)
        setupPagination()

        binding.prevButton.setOnClickListener {
            playClickSound { if (!isLoading) currentPage -= 1 }
        }
        binding.nextButton.setOnClickListener {
            playClickSound { if (!isLoading) currentPage += 1 }
        }
        binding.logo.setOnClickListener {
            playClickSound { findNavController().popBackStack() }
        }
    }

    private fun setupPagination() {
        with(binding.pagination) {
            numberOfItems = assets.size
            selectedItem = 1

            registerListener(object : PaginationView.OnTouchListener {
                override fun onItemTouched(page: Int) {
                    playClickSound()
                    if (isLoading) return
                    selectedItem = page
                    currentPage = page - 1
                }
            })
        }
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int) =
        if (enter) {
            MoveAnimation.create(MoveAnimation.LEFT, enter, 700)
        } else {
            MoveAnimation.create(MoveAnimation.RIGHT, enter, 700)
        }

    private fun gotoPage(page: Int, isPrev: Boolean = false) {
        isLoading = true

        if (isUsingCustomBackground)
            invalidateBackground(page)

        val exitTechniques =
            if (isPrev) Techniques.SlideOutLeft
            else Techniques.SlideOutRight

        animateSlide(exitTechniques, page)

        invalidateControlButton()
        binding.pagination.selectedItem = currentPage + 1
    }

    private fun animateSlide(
        exitTechniques: Techniques,
        page: Int
    ) {
        YoYo.with(exitTechniques)
            .duration(700)
            .onEnd {
                binding.slide.visibility = View.INVISIBLE
                binding.slideAsset.setImageResource(assets[page])
                YoYo.with(Techniques.Landing)
                    .duration(700)
                    .onStart {
                        binding.slide.visibility = View.VISIBLE
                    }
                    .onEnd { isLoading = false }
                    .playOn(binding.slide)
            }
            .playOn(binding.slide)
    }

    private fun invalidateBackground(page: Int) {
        if (backgrounds.containsKey(page + 1))
            binding.root.setBackgroundResource(backgrounds[page + 1]!!)
        else
            binding.root.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.df_background)
    }

    private fun invalidateControlButton() {
        when (currentPage) {
            0 -> binding.prevButton.visibility = View.GONE
            assets.size - 1 -> binding.nextButton.visibility = View.GONE
            else -> {
                binding.prevButton.visibility = View.VISIBLE
                binding.nextButton.visibility = View.VISIBLE
            }
        }
    }
}