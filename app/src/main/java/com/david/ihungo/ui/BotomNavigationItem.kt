package com.david.ihungo.ui

import com.david.ihungo.R

// All icon list here
//https://fonts.google.com/icons?selected=Material+Symbols+Sharp:cached:FILL@0;wght@400;GRAD@0;opsz@48&icon.style=Sharp
sealed class BotomNavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : BotomNavigationItem("home", R.drawable.fact_check_fill1_wght400_grad0_opsz24, "")
    object Create : BotomNavigationItem("create", R.drawable.control_point_duplicate_fill1_wght400_grad0_opsz24, "")
}