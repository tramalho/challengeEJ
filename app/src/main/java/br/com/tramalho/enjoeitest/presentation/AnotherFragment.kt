package br.com.tramalho.enjoeitest.presentation


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.tramalho.enjoeitest.R
import kotlinx.android.synthetic.main.fragment_another.*

class AnotherFragment : Fragment() {


    companion object {

        private val TITLE_BUNDLE: String = "TITLE_BUNDLE"

        fun getInstance(rIdTitle: Int): AnotherFragment {

            val anotherFragment = AnotherFragment()

            val args = Bundle()
            args.putInt(TITLE_BUNDLE, rIdTitle)
            anotherFragment.setArguments(args)

            return anotherFragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_another, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            title.text = getString(it.getInt(TITLE_BUNDLE, R.string.app_name))
        }
    }
}
