package com.whaleshark.rankingscrollexample.ui

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInWindow

object HitTest {
    private val map = mutableMapOf<String, LayoutCoordinates>()

    fun setScrollableCoordinates(title: String, coordinates: LayoutCoordinates) {
        map[title] = coordinates
    }

    fun hit(x: Float, y: Float): Boolean {
        map.forEach { (title, coordinates) ->
            if(coordinates.isAttached){
                val bounds = coordinates.boundsInWindow()
                println("Hit $title, x = $x, y = $y, bounds = $bounds")
                if (bounds.contains(Offset(x, y))) {
                    println("Hit $title")
                    return true
                }
            }
        }
        return false
    }
}