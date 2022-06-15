package com.szn.videoplayer.composable

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.szn.app.ui.compose.AppTheme
import com.szn.videoplayer.R
import com.szn.videoplayer.composable.player.VideoPlayerView
import com.szn.videoplayer.navigation.NavRoutes

@Composable
fun CrossApp() {
    val navController = rememberNavController()
    var (canPop, setCanPop) = remember { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    val listener = NavController.OnDestinationChangedListener { controller, _, _ ->
        setCanPop(controller.previousBackStackEntry != null)
    }
    navController.addOnDestinationChangedListener(listener)
    val conf = LocalConfiguration.current

    AppTheme {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                if(conf.orientation == Configuration.ORIENTATION_PORTRAIT)
                    TopAppBar(
                        title = { Text(stringResource(id = R.string.app_name), color = Color.White) },
                        backgroundColor = MaterialTheme.colors.onBackground,
                        navigationIcon = {
                            if (canPop) {
                                IconButton(onClick = {
                                    navController.popBackStack()
                                }) {
                                    Icon(imageVector = Icons.Filled.ArrowBack, null, tint = Color.White)
                                }
                            } else null
                        }
                    )
                else null
             },
            content = { NavigationHost(navController = navController) },
            bottomBar = {
                if(conf.orientation == Configuration.ORIENTATION_PORTRAIT)
                    BottomNavigationBar(navController = navController)
            }
        )
    }
}

@Composable
fun NavigationHost(navController: NavHostController){

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route,
        modifier = Modifier.padding(0.dp)
    ) {

        composable(NavRoutes.Home.route) {
            HomeView(navController)
        }

        composable(NavRoutes.Player.route) {
            VideoPlayerView()
        }

    }
}