package br.com.tramalho.enjoeitest.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.tramalho.enjoeitest.R
import br.com.tramalho.enjoeitest.infraestructure.disableShiftMode
import kotlinx.android.synthetic.main.activity_main.*

private const val HOME: Int = 0
private const val SEARCH: Int = 1
private const val PHOTOS: Int = 2
private const val MESSAGE: Int = 3
private const val PROFILE: Int = 4

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewPager()
        setupBottomNavigation()
    }

    private fun setupViewPager() {
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)

        viewPagerAdapter.add(HomeFragment())
        viewPagerAdapter.add(AnotherFragment.getInstance(R.string.search))
        viewPagerAdapter.add(AnotherFragment.getInstance(R.string.photo))
        viewPagerAdapter.add(AnotherFragment.getInstance(R.string.messages))
        viewPagerAdapter.add(AnotherFragment.getInstance(R.string.profile))

        viewPager.adapter = viewPagerAdapter

        viewPager.offscreenPageLimit = viewPagerAdapter.count

        viewPager.beginFakeDrag();
    }

    private fun setupBottomNavigation() {
        bottomBar.disableShiftMode()

        bottomBar.setOnNavigationItemSelectedListener {

            val idx: Int = when (it.itemId) {
                R.id.actionSearch -> SEARCH
                R.id.actionPhotos -> PHOTOS
                R.id.actionMessages -> MESSAGE
                R.id.actionProfile -> PROFILE
                else -> HOME
            }

            viewPager.setCurrentItem(idx, true)
            true
        }
    }
}

