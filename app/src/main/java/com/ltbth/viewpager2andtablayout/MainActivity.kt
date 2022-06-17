package com.ltbth.viewpager2andtablayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager2: ViewPager2
    private lateinit var tabLayout: TabLayout

    companion object {
        const val FIXED_POSITION = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewPager2 = findViewById(R.id.pager)
        tabLayout = findViewById(R.id.tab_layout)
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        viewPager2.adapter = DemoCollectionAdapter(this)

//        val tabLayoutMediator = TabLayoutMediator(tabLayout,viewPager2) { tab, position ->
//            tab.text = "PAGE ${(position + 1)}"
//        }
//        tabLayoutMediator.attach()

        fillTabs()
        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab!!.let {
                    if (it.position < FIXED_POSITION) {
                        viewPager2.currentItem = it.position
                    } else if (it.position > FIXED_POSITION) {
                        viewPager2.currentItem = it.position-1
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                return
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                return
            }

        })

        viewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position < FIXED_POSITION) {
                    tabLayout.selectTab(tabLayout.getTabAt(position))
                } else {
                    tabLayout.selectTab(tabLayout.getTabAt(position+1))
                }
            }
        })
    }

    private fun fillTabs() {
        val count = viewPager2.adapter!!.itemCount
        for (i in 1..count) {
            tabLayout.addTab(tabLayout.newTab().setText("PAGE $i"))
        }
        tabLayout.addTab(tabLayout.newTab().setText("PAGE ADDED"),FIXED_POSITION)
    }

}