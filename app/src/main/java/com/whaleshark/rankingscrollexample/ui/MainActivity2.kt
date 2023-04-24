package com.whaleshark.rankingscrollexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.compose.ui.graphics.Color
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.whaleshark.rankingscrollexample.R

class MainActivity2 : AppCompatActivity() {
    private val viewPager: ViewPager2 by lazy {
        findViewById(R.id.view_pager)
    }

    private val onSuppressLayout: (Boolean) -> Unit = { suppress ->
        val recyclerView = viewPager.children.find { child ->
            child is RecyclerView
        } as RecyclerView
        recyclerView.suppressLayout(suppress)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        viewPager.adapter = BottomNavigationViewPagerAdapter(this, onSuppressLayout = onSuppressLayout)
        val recyclerView = viewPager.children.find { child ->
            child is RecyclerView
        } as RecyclerView

        recyclerView.addOnItemTouchListener(object :
            RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                if(e.action == MotionEvent.ACTION_DOWN){
                    Log.d("MainActivity2", "onInterceptTouchEvent, hit = ${HitTest.hit(e.rawX, e.rawY)}")
                    rv.requestDisallowInterceptTouchEvent(HitTest.hit(e.rawX, e.rawY))
                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                // Do nothing
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                // Do nothing
            }
        })
//        recyclerView.suppressLayout(true)
//        recyclerView.parent.requestDisallowInterceptTouchEvent(true)
//        recyclerView.requestDisallowInterceptTouchEvent(false)
//        recyclerView.addOnItemTouchListener(object :
//            RecyclerView.OnItemTouchListener {
//            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
//                return false
//            }
//
//            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
//                // Do nothing
//            }
//
//            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
//                // Do nothing
//            }
//        })
    }
}

class BottomNavigationViewPagerAdapter(
    activity: FragmentActivity,
    private val onSuppressLayout: (Boolean) -> Unit
) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BlankFragment.newInstance("text 0", Color.Red, onSuppressLayout = onSuppressLayout)
            1 -> BlankFragment.newInstance("text 1", Color.Blue, onSuppressLayout = onSuppressLayout)
            2 -> BlankFragment.newInstance("text 2", Color.Gray, onSuppressLayout = onSuppressLayout)
            3 -> BlankFragment.newInstance("text 3", Color.Green, onSuppressLayout = onSuppressLayout)
            else -> BlankFragment.newInstance("text 0", Color.Red, onSuppressLayout = onSuppressLayout)
        }
    }
}