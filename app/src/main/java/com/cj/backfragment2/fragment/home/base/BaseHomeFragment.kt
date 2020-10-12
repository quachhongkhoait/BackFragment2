package com.cj.backfragment2.fragment.home.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cj.backfragment2.R
import com.cj.backfragment2.fragment.home.HomeFragment
import com.cj.backfragment2.utils.OnCurrentFragmentListener

class BaseHomeFragment : Fragment(), OnCurrentFragmentListener {

    private lateinit var mListener: OnCurrentFragmentListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_base_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        replaceFragment(HomeFragment(), false)
    }

    fun replaceFragment(fragment: Fragment, addToBackStack: Boolean) {
        var a = childFragmentManager
        val transaction = childFragmentManager.beginTransaction()
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.setCustomAnimations(R.anim.slide_in_right, 0)
        transaction.replace(R.id.mFrameLayoutHome, fragment)
        transaction.commit()
    }

    override fun onCurrentFragment(i : Int) {
        TODO("Not yet implemented")
    }

    fun popFragment(): Boolean {
        var isPop = false
        if (childFragmentManager.backStackEntryCount > 0) {
            isPop = true
            childFragmentManager.popBackStack()
        }
        return isPop
    }
}