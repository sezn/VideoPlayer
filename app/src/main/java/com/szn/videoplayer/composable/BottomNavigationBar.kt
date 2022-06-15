package com.szn.videoplayer.composable

import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.szn.videoplayer.navigation.NavBarItems

@Composable
fun BottomNavigationBar(navController: NavHostController) {
 
    BottomNavigation {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
 
        NavBarItems.BarItems.forEach { navItem ->
 
            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                unselectedContentColor = Color.White,
                selectedContentColor = Color.Red,
                modifier =  Modifier.background(MaterialTheme.colors.background),
                onClick = {
                    navController.navigate(navItem.route) {
                       popUpTo(navController.graph.findStartDestination().id) {
                           saveState = true
                       }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
 
                icon = {
                    Icon(imageVector = navItem.image, 
                           contentDescription = navItem.title)
                },
                label = {
                    Text(text = navItem.title)
                },
            )
        }
    }
}