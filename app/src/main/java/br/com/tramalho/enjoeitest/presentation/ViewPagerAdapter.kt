package br.com.tramalho.enjoeitest.presentation

import android.support.v4.app.FragmentManager
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter

class ViewPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {

    private val fragments : MutableList<Fragment> = ArrayList();

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    fun add(instance: Fragment) {
        fragments.add(instance)
    }
}