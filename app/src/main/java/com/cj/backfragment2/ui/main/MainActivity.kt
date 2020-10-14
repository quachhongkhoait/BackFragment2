package com.cj.backfragment2.ui.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.cj.backfragment2.R
import com.cj.backfragment2.fragment.chat.ChatFragment
import com.cj.backfragment2.fragment.home.base.BaseHomeFragment
import com.cj.backfragment2.fragment.home.detail1.Detail1Fragment
import com.cj.backfragment2.fragment.profile.ProfileFragment
import com.cj.backfragment2.utils.OnCurrentFragmentListener
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    OnCurrentFragmentListener {

    private var mActionBarDrawerToggle: ActionBarDrawerToggle? = null
    val fm: FragmentManager = supportFragmentManager
    var checkChangeTab : Boolean = false

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTab()
        initNav()
        mToolbar.setNavigationOnClickListener {
            Log.d("nnn", "onCreate: "+checkChangeTab)
            if(!checkChangeTab){
                mDrawerLayout.openDrawer(Gravity.LEFT)
            } else{
                backStack()
            }
        }
    }

    private fun initNav() {
        setSupportActionBar(mToolbar)
        mToolbar.setTitle("Home")
        mNavigationView.setNavigationItemSelectedListener(this)
        mActionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            mDrawerLayout,
            mToolbar,
            R.string.open,
            R.string.close
        )
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle!!)
        mActionBarDrawerToggle!!.isDrawerIndicatorEnabled = true
        mActionBarDrawerToggle!!.syncState()

    }

    private fun initTab() {
        var mAdapterPager = AdapterPager(supportFragmentManager)
        mAdapterPager.addData(BaseHomeFragment(), "Home")
        mAdapterPager.addData(ChatFragment(), "Chat")
        mAdapterPager.addData(ProfileFragment(), "Profile")
        mViewPager.adapter = mAdapterPager
        mViewPager.offscreenPageLimit = 3
        mTabLayout.setupWithViewPager(mViewPager)
        mTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position != 0) {
                    changeNavDrawer(false)
                } else {
                    for (frag in fm.getFragments()) {
                        val childFm: FragmentManager = frag.childFragmentManager
                        if (childFm.getBackStackEntryCount() > 0) {
                            changeNavDrawer(true)
                        } else{
                            changeNavDrawer(false)
                        }
                        return
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemHome -> {
                Toast.makeText(this, "Home", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.itemNew -> {
                Toast.makeText(this, "New", Toast.LENGTH_LONG).show()
                return true
            }
        }
        return false
    }

    override fun onCurrentFragment(i: Int) {
        if (i == 0) {
            for (frag in fm.getFragments()) {
                try {
                    if (frag.childFragmentManager.backStackEntryCount > 0) {
                        frag.childFragmentManager.popBackStack(
                            null,
                            FragmentManager.POP_BACK_STACK_INCLUSIVE
                        )
                        changeNavDrawer(false)
                    }
                } catch (ignored: IllegalStateException) {
                    Log.d("nnn", "onCurrentFragment: " + ignored.message)
                }
            }
        } else if (i == 1) {
            changeNavDrawer(true)

        } else {
            changeNavDrawer(true)
        }
    }

    override fun onBackPressed() {
        for (frag in fm.getFragments()) {
            if (frag.isVisible && mViewPager.currentItem == 0) {
                val childFm: FragmentManager = frag.childFragmentManager
                if (childFm.getBackStackEntryCount() > 0) {
                    childFm.popBackStack()
                    if (childFm.getBackStackEntryCount() == 1) {
                        changeNavDrawer(false)
                    } else{
                        changeNavDrawer(true)
                    }
                    return
                }
            }
        }
        super.onBackPressed()
    }

    fun changeNavDrawer(bo: Boolean) {
        if (bo) {
            mToolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace)
            checkChangeTab = true
        } else {
            mToolbar.setNavigationIcon(R.drawable.ic_dehaze)
            checkChangeTab = false
        }
    }

    fun backStack(){
        for (frag in fm.getFragments()) {
            if (frag.isVisible && mViewPager.currentItem == 0) {
                val childFm: FragmentManager = frag.childFragmentManager
                if (childFm.getBackStackEntryCount() > 0) {
                    childFm.popBackStack()
                    if (childFm.getBackStackEntryCount() == 1) {
                        changeNavDrawer(false)
                    } else{
                        changeNavDrawer(true)
                    }
                    return
                }
            }
        }
    }
}
