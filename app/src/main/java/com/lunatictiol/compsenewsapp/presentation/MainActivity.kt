package com.lunatictiol.compsenewsapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lunatictiol.compsenewsapp.presentation.article_list.ArticleScreen
import com.lunatictiol.compsenewsapp.presentation.drawerComponents.MenuItem
import com.lunatictiol.compsenewsapp.presentation.ui.theme.CompseNewsAPPTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompseNewsAPPTheme {

/*
 insider,
magazine, movies, nyregion, obituaries, opinion,
politics, realestate, science, sports, sundayreview,
technology, theater, t-magazine, travel, upshot,
us, world*/

                val items = listOf(
                    MenuItem(
                        id = "home",
                        title = "Home",
                        contentDescription = "Go to home screen",
                        icon = Icons.Default.Home

                    ),
                    MenuItem(
                        id = "art",
                        title = "ART",
                        contentDescription = "Go to home screen",
                        icon = Icons.Default.Edit

                    ),
                    MenuItem(
                        id = "book/review",
                        title = "Book Reviews",
                        contentDescription = "Go to home screen",
                        icon = Icons.Default.Info
                    ),
                    MenuItem(
                        id = "business",
                        title = "business",
                        contentDescription = "Go to home screen",
                        icon = Icons.Default.Star
                    ),
                    MenuItem(
                        id = "fashion",
                        title = "Fashion",
                        contentDescription = "Go to home screen",
                        icon = Icons.Default.Face
                    ),
                    MenuItem(
                        id = "food",
                        title = "Food",
                        contentDescription = "Go to home screen",
                        icon = Icons.Default.Favorite

                    ),
                    MenuItem(
                        id = "health",
                        title = "Health",
                        contentDescription = "Go to home screen",
                        icon = Icons.Default.Person
                    )
                    ,
                MenuItem(
                    id = "insider",
                    title = "Insider",
                    contentDescription = "Go to home screen",
                    icon = Icons.Default.ArrowForward

                )

                )
                val selectedItem = remember { mutableStateOf(items[0]) }

                val navController = rememberNavController()
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                NavHost(navController = navController, startDestination = "articleScreen" ) {

                    composable("ArticleScreen" + "?section={section}",
                        arguments = listOf(
                            navArgument(
                                name = "section"

                            ){
                                type= NavType.StringType
                                defaultValue="home"

                            }
                        )



                        ) {
                        val section = it.arguments?.getString("section")?: "home"
                        ArticleScreen(section = section)
                    }
                     }


                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet {
                            Spacer(Modifier.height(12.dp))
                            items.forEach { item ->
                                NavigationDrawerItem(
                                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                                    icon = { item.icon },
                                    label = { Text(item.title) },
                                    onClick = {
                                        scope.launch { drawerState.close()

                                        }

                                        selectedItem.value = item
                                    }
                                    , selected = item == selectedItem.value


                                )
                            }
                        }


                    })

                { if(drawerState.isClosed){
                        val section = selectedItem.value.id
                    ArticleScreen(section = section)
                }


                }


            }
        }
    }
}

