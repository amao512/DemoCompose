package com.aslnstbk.democompose.global.presentation.navigation

import com.aslnstbk.democompose.R

sealed class NavigationItem(
    var route: String,
    var icon: Int,
    var title: String
) {
    object Home : NavigationItem(route = "/home", icon = R.drawable.ic_global_home, title = "Home")
    object Profile : NavigationItem(route = "/profile", icon = R.drawable.ic_global_person, title = "Profile")
    object Search : NavigationItem(route = "/search", icon = 0, title = "Search")
}
