package com.app.nobelprize

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.nobelprize.data.model.NobelPrize
import com.app.nobelprize.presentation.ui.screens.NobelDetailScreen
import com.app.nobelprize.presentation.ui.screens.NobelListScreen
import com.app.nobelprize.presentation.ui.theme.NobelPrizeTheme
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NobelPrizeTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "nobel_list",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("nobel_list") {
                            NobelListScreen(
                                onItemClick = { prize ->
                                    val prizeJson = Uri.encode(Gson().toJson(prize))
                                    navController.navigate("nobel_detail/$prizeJson")
                                }
                            )
                        }
                        composable(
                            "nobel_detail/{prize}",
                            arguments = listOf(navArgument("prize") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val prizeJson = backStackEntry.arguments?.getString("prize")
                            val prize = Gson().fromJson(prizeJson, NobelPrize::class.java)
                            NobelDetailScreen(prize)
                        }
                    }
                }
            }
        }
    }
}
