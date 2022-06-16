package com.ltbth.viewpager2andtablayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager2: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewPager2 = findViewById(R.id.pager)
        tabLayout = findViewById(R.id.tab_layout)
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        viewPager2.adapter = DemoCollectionAdapter(this)


        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = "PAGE ${(position + 1)}"
        }.attach()
        tabLayout.addTab(tabLayout.newTab().setId(1000),2)
    }
}