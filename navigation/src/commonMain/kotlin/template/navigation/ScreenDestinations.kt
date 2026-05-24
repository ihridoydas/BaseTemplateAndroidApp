package template.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface ScreenDestinations : NavKey {
    @Serializable
    data object HomeScreen : ScreenDestinations

    @Serializable
    data object ViewScreen : ScreenDestinations
}
