/*
* MIT License
*
* Copyright (c) 2024 Hridoy Chandra Das
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*
*/
package template.navigation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import template.common.DURATION_MILLIS

@ExperimentalAnimationApi
fun NavGraphBuilder.screen(
    route: String,
    arguments: List<NamedNavArgument> = listOf(),
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit,
) {
    val animSpec: FiniteAnimationSpec<IntOffset> = tween(DURATION_MILLIS, easing = FastOutSlowInEasing)

    composable(
        route,
        arguments = arguments,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { screenWidth -> screenWidth },
                animationSpec = animSpec,
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { screenWidth -> -screenWidth },
                animationSpec = animSpec,
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { screenWidth -> -screenWidth },
                animationSpec = animSpec,
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { screenWidth -> screenWidth },
                animationSpec = animSpec,
            )
        },
        content = content,
    )
}
