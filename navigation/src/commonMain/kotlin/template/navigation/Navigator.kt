package template.navigation

interface Navigator {
    fun navigate(route: ScreenDestinations)
    fun goBack()
}
