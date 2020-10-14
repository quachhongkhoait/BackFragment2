package com.cj.backfragment2.fragment.home.detail

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cj.backfragment2.R
import com.cj.backfragment2.fragment.home.HomeFragment
import com.cj.backfragment2.fragment.home.base.BaseHomeFragment
import com.cj.backfragment2.fragment.home.detail1.Detail1Fragment
import com.cj.backfragment2.utils.OnCurrentFragmentListener
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private lateinit var mListener: OnCurrentFragmentListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mDetailClick.setOnClickListener {
            if (parentFragment is BaseHomeFragment) {
                (parentFragment as BaseHomeFragment?)?.replaceFragment(Detail1Fragment(), true)
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