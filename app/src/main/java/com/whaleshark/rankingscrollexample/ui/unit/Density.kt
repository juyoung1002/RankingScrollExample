package com.whaleshark.rankingscrollexample.ui.unit

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun Int.textDp() = with(LocalDensity.current) { Dp(this@textDp.toFloat()).toSp() }