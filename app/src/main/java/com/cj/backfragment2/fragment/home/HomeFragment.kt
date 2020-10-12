package com.cj.backfragment2.fragment.home

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cj.backfragment2.R
import com.cj.backfragment2.fragment.home.base.BaseHomeFragment
import com.cj.backfragment2.fragment.home.detail.DetailFragment
import com.cj.backfragment2.utils.OnCurrentFragmentListener
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    private lateinit var mListener: OnCurrentFragmentListener

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mHomeClick.setOnClickListener {
            if (parentFragment is BaseHomeFragment) {
                (parentFragment as BaseHomeFragment?)?.replaceFragment(DetailFragment(), true)
                mListener.onCurrentFragment(1)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mListener = context as OnCurrentFragmentListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$e must implement OnChangeHeaderListener")
        }
    }
}