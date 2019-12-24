package com.oluwafemi.mobcategories.ui.shared

import android.os.Bundle
import androidx.navigation.Navigation
import com.oluwafemi.mobcategories.R
import dagger.android.support.DaggerFragment

open class PagesFragment: DaggerFragment() {

    protected fun navigateWithArgsTo(navId: Int = R.id.home_main_nav, destId: Int, args: Bundle) {
        activity?.let {
            Navigation.findNavController(it, navId)
                .navigate(destId, args)
        }
    }
}