package com.cj.backfragment2.fragment.home.detail1

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cj.backfragment2.R
import com.cj.backfragment2.utils.OnCurrentFragmentListener
import kotlinx.android.synthetic.main.detail1_fragment.*

class Detail1Fragment : Fragment() {

    private lateinit var mListener: OnCurrentFragmentListener

    companion object {
        fun newInstance() = Detail1Fragment()
    }

    private lateinit var viewModel: Detail1ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail1_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(Detail1ViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mDetail1Click.setOnClickListener {
            mListener.onCurrentFragment(0)
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