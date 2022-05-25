package com.juloweather.juloapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.juloweather.juloapp.base.navigation.NavigationCommand
import com.juloweather.juloapp.base.viewmodel.BaseViewModel
import com.juloweather.utils.AppUtil.setStatusBarColor

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    abstract val binding: VB
    abstract val viewModel: VM
    abstract val backToPreviousFragmentOnBackPressed: Boolean
    abstract val useThemeStatusBarColor: Boolean
    abstract val statusBarIconColorWhite: Boolean
    private var onBackPressedCallback: OnBackPressedCallback? = null

    override fun onStart() {
        super.onStart()
        viewModel.showToast.observe(this) { Toast.makeText(activity, it, Toast.LENGTH_SHORT).show() }
        viewModel.showSnack.observe(this) {
            Snackbar.make(
                this.requireView(),
                it,
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    fun navigate(command: NavigationCommand) {
        when (command) {
            is NavigationCommand.To     -> findNavController().navigate(command.directions)
            is NavigationCommand.Back   -> findNavController().popBackStack()
            is NavigationCommand.BackTo -> findNavController().popBackStack(command.destinationId, false)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setStatusBarColor(
            requireActivity(),
            useThemeStatusBarColor = useThemeStatusBarColor,
            iconColorWhite = statusBarIconColorWhite
        )
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        performDataBinding()
        handleOnBackPressed()
    }

    private fun performDataBinding(){
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

    private fun handleOnBackPressed() {
        onBackPressedCallback = object : OnBackPressedCallback(backToPreviousFragmentOnBackPressed) {
            override fun handleOnBackPressed() {
                navigate(NavigationCommand.Back)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback!!)
    }
}