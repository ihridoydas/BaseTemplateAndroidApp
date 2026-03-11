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
package template.ui

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import template.local.language.LanguageDataStore
import template.local.theme.ThemeDataStore
import template.navigation.Navigator
import template.navigation.ScreenDestinations
import template.navigation.rememberNavigationState
import template.navigation.screen
import template.navigation.toEntries
import template.screens.HomeScreen
import template.screens.ViewScreen

@Composable
fun MainAnimationNavHost(
    languageDataStore: LanguageDataStore,
    themeDataStore: ThemeDataStore,
) {
    val navigationState = rememberNavigationState(
        startRoute = ScreenDestinations.HomeScreen,
        topLevelRoutes = setOf(ScreenDestinations.HomeScreen),
    )
    val navigator = remember { Navigator(navigationState) }

    val entryProvider = entryProvider {
        screen<ScreenDestinations.HomeScreen> {
            HomeScreen(
                navigator = navigator,
                languageDataStore = languageDataStore,
                themeDataStore = themeDataStore,
            )
        }
        screen<ScreenDestinations.ViewScreen> {
            ViewScreen(
                onBackPress = {
                    navigator.goBack()
                },
            )
        }
    }

    NavDisplay(
        entries = navigationState.toEntries(entryProvider),
        onBack = { navigator.goBack() },
        transitionSpec = {
            // Slide in from right when navigating forward
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(700),
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { -it },
                animationSpec = tween(700),
            )
        },
        popTransitionSpec = {
            // Slide in from left when navigating back
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(700),
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(700),
            )
        },
    )
}
