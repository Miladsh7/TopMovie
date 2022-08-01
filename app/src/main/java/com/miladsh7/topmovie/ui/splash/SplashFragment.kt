package com.miladsh7.topmovie.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.miladsh7.topmovie.R
import com.miladsh7.topmovie.databinding.FragmentSplashBinding
import com.miladsh7.topmovie.utils.StoreUserData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {

    //Binding
    private lateinit var binding: FragmentSplashBinding

    @Inject
    lateinit var storeUserData: StoreUserData

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Set delay
        lifecycle.coroutineScope.launchWhenCreated {
            delay(2000)
            //Check user token
            storeUserData.getUserToken().collect {
                if (it.isEmpty()) {
                    findNavController().navigate(R.id.actionSplashToRegister)
                } else {
                    findNavController().navigate(R.id.actionToHome)
                }
            }
        }
    }

}