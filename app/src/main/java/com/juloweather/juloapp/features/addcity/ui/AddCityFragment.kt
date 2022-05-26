package com.juloweather.juloapp.features.addcity.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.juloweather.juloapp.base.BaseFragment
import com.juloweather.juloapp.databinding.FragmentAddCityBinding
import com.juloweather.juloapp.features.addcity.viewmodel.AddCityViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@AndroidEntryPoint
@FragmentScoped
class AddCityFragment : BaseFragment<FragmentAddCityBinding, AddCityViewModel>() {

    @FragmentScoped
    override val binding: FragmentAddCityBinding by lazy { FragmentAddCityBinding.inflate(layoutInflater) }
    override val viewModel: AddCityViewModel by viewModels()
    override val backToPreviousFragmentOnBackPressed: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}