package com.zuhriyansauqi.frisianflag

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.zuhriyansauqi.frisianflag.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val handler by lazy { Handler(Looper.getMainLooper()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        animateSplashAsset()
        setupMenuActions()
    }

    private fun animateSplashAsset() {
        YoYo.with(Techniques.Landing)
            .duration(2000)
            .onStart { binding.splashAsset.visibility = View.VISIBLE }
            .onEnd {
                handler.postDelayed({
                    YoYo.with(Techniques.TakingOff)
                        .onEnd {
                            binding.splashAsset.visibility = View.INVISIBLE

                            animateHomeAsset()
                            animateMainMenu()
                        }
                        .playOn(binding.splashAsset)
                }, 300)
            }
            .playOn(binding.splashAsset)
    }

    private fun animateHomeAsset() {
        YoYo.with(Techniques.FadeInUp)
            .duration(700)
            .onStart { binding.homeAsset.visibility = View.VISIBLE }
            .playOn(binding.homeAsset)
    }

    private fun animateMainMenu() {
        YoYo.with(Techniques.FadeInRight)
            .duration(700)
            .onStart { binding.mainMenu.visibility = View.VISIBLE }
            .playOn(binding.mainMenu)
    }

    private fun setupMenuActions() {
        with(binding) {
            menuFrisianFlag.setOnClickListener { playClickSound { navigate(Page.FRISIAN_FLAG) } }
            menuHpk.setOnClickListener { playClickSound { navigate(Page.HPK) } }
            menuStunting.setOnClickListener { playClickSound { navigate(Page.STUNTING) } }
            menuAsi.setOnClickListener { playClickSound { navigate(Page.ASI) } }
            menuPrimamum.setOnClickListener { playClickSound { navigate(Page.PRIMAMUM) } }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigate(page: Page) {
        findNavController().navigate(page.direction)
    }

    private enum class Page(val direction: NavDirections) {
        FRISIAN_FLAG(HomeFragmentDirections.actionHomeFragmentToFrisianFlagFragment()),
        HPK(HomeFragmentDirections.actionHomeFragmentToHPKFragment()),
        STUNTING(HomeFragmentDirections.actionHomeFragmentToStuntingFragment()),
        PRIMAMUM(HomeFragmentDirections.actionHomeFragmentToPrimamumFragment()),
        ASI(HomeFragmentDirections.actionHomeFragmentToAsiFragment())
    }
}