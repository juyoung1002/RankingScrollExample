package com.whaleshark.rankingscrollexample.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BlankFragment(private val onSuppressLayout: (Boolean) -> Unit) : Fragment() {
    private lateinit var param1: String
    private var param2: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)!!
            param2 = it.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
//                        .blockingHorizontalDragGesturesOfParent()
                ) {
                    MyFragmentContent()
                }
            }
        }
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun MyFragmentContent() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(param2))
        ) {
            Text(text = param1, style = MaterialTheme.typography.h5)

            val scrollableState = rememberScrollableState(consumeScrollDelta = { delta ->
                Log.e("TAG", "consumeScrollDelta: $delta")
                delta
            })

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .onGloballyPositioned { coordinates ->
                        HitTest.setScrollableCoordinates(param1, coordinates)
                    }
            ) {
                for (i in 1..4) {
                    Text(
                        text = "Item $i",
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier
                            .size(250.dp, 400.dp)
                            .background(randomColor())
                    )
                }
            }
        }
    }

    private fun randomColor(): Color {
        val random = Random()
        val r = random.nextInt(256)
        val g = random.nextInt(256)
        val b = random.nextInt(256)
        return Color(android.graphics.Color.rgb(r, g, b))
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(text: String, color: Color, onSuppressLayout: (Boolean) -> Unit) =
            BlankFragment(onSuppressLayout = onSuppressLayout).apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, text)
                    putInt(ARG_PARAM2, color.toArgb())
                }
            }
    }
}