package com.whaleshark.rankingscrollexample.ui.unit

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun Modifier.blockingHorizontalDragGesturesOfParent() = this.pointerInput(Unit) {
    detectHorizontalDragGestures { _, _ -> }
}