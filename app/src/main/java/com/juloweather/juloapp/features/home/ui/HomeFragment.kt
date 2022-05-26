package com.juloweather.juloapp.features.home.ui

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.juloweather.juloapp.base.BaseFragment
import com.juloweather.juloapp.databinding.FragmentHomeBinding
import com.juloweather.juloapp.features.home.viewmodel.HomeViewModel
import com.juloweather.utils.ext.fragment.adjustFontScale
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@AndroidEntryPoint
@FragmentScoped
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    @FragmentScoped
    override val binding: FragmentHomeBinding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    override val viewModel: HomeViewModel by viewModels()
    override val backToPreviousFragmentOnBackPressed: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adjustFontScale(resources.configuration!!)
    }

}