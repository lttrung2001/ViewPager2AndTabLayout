package com.ltbth.viewpager2andtablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class DemoCollectionAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    private lateinit var fragment: DemoObjectFragment
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        fragment = DemoObjectFragment()
        fragment.arguments = Bundle().apply {
            putInt(ARG_OBJECT,position+1)
        }
        return fragment
    }
}

private const val ARG_OBJECT = "object"

class DemoObjectFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_collection_object,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.let {
            val textView: TextView = view.findViewById(R.id.text1)
            textView.text = it.getInt(ARG_OBJECT).toString()
        }
    }
}