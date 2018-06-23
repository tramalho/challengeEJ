package br.com.tramalho.enjoeitest.presentation

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.tramalho.enjoeitest.R
import br.com.tramalho.enjoeitest.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var  viewDataBinding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewDataBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_home,
                        container, true)

        return viewDataBinding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProviders.of(activity!!).get(HomeViewModel::class.java)

        viewDataBinding.viewModel = viewModel

        viewModel.init()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewDataBinding.unbind()
    }
}
