package id.my.indoquran

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import id.my.indoquran.ui.CustomSplashScreen
import id.my.indoquran.ui.DoaScreen
import id.my.indoquran.ui.HaditsScreen
import id.my.indoquran.ui.HomeScreen
import id.my.indoquran.ui.JadwalSholatScreen
import id.my.indoquran.ui.SurahScreen
import id.my.indoquran.ui.theme.IndoQuranTheme


enum class IndoQuranScreen() {
    Home,
    Quran,
    Hadist,
    Doa,
    JadwalSholat,
    SplashScreen
}

data class BottomNavigationItem(
    val title: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unSelectedIcon: Int,
    val onClick: () -> Unit,
)


@ExperimentalMaterial3Api
@Composable
fun IndoQuranAppBar(
    modifier: Modifier = Modifier,
    currentScreen: IndoQuranScreen,
    navigateUp: () -> Unit = {},
    canNavigateBack: Boolean,
) {
    TopAppBar(
        title = {
            Text(
                "IndoQur`an",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                )
            )
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back Button")
                }
            } else
                Image(
                    painter = painterResource(R.drawable.logo_indoquran),
                    contentDescription = "Logo IndoQur`an",
                    modifier = Modifier
                        .size(
                            60.dp
                        )
                        .padding(8.dp)
                )
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Settings, contentDescription = "Setting Icon",
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showBackground = true, //showSystemUi = true
)
@Composable
fun IndoQuranAppBarPreview() {
    IndoQuranTheme {
        IndoQuranAppBar(
            modifier = Modifier,
            currentScreen = IndoQuranScreen.Quran,
            canNavigateBack = false
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun IndoQuranAppBarPreviewDark() {
    IndoQuranTheme(darkTheme = true) {
        IndoQuranAppBar(
            modifier = Modifier,
            currentScreen = IndoQuranScreen.Quran,
            canNavigateBack = false
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = R.drawable.home,
            unSelectedIcon = R.drawable.home,
            onClick = {navController.navigate(IndoQuranScreen.Home.name){
                popUpTo(navController.graph.startDestinationRoute!!){inclusive = true}
            } }
        ),
        BottomNavigationItem(
            title = "Surah",
            selectedIcon = R.drawable.quran,
            unSelectedIcon = R.drawable.quran,
            onClick = {
                navController.navigate(IndoQuranScreen.Quran.name) {
                    popUpTo(navController.graph.startDestinationRoute!!) { inclusive = true }
                }
            }
        ),

        BottomNavigationItem(
            title = "Hadits",
            selectedIcon = R.drawable.hadits,
            unSelectedIcon = R.drawable.hadits,
            onClick = {
                navController.navigate(IndoQuranScreen.Hadist.name) {
                    popUpTo(navController.graph.startDestinationRoute!!) { inclusive = true }
                }
            }
        ),
        BottomNavigationItem(
            title = "Doa",
            selectedIcon = R.drawable.doa,
            unSelectedIcon = R.drawable.doa,

            onClick = {
                navController.navigate(IndoQuranScreen.Doa.name) {
                    popUpTo(navController.graph.startDestinationRoute!!) { inclusive = true }
                }
            }
        ),
        BottomNavigationItem(
            title = "Sholat",
            selectedIcon = R.drawable.sholat,
            unSelectedIcon = R.drawable.sholat,

            onClick = {
                navController.navigate(IndoQuranScreen.JadwalSholat.name) {
                    popUpTo(navController.graph.startDestinationRoute!!) { inclusive = true }
                }
            }
        ),
    )

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = IndoQuranScreen.valueOf(
        backStackEntry?.destination?.route ?: IndoQuranScreen.SplashScreen.name
    )

    Scaffold(
        topBar = {
            if (currentScreen != IndoQuranScreen.SplashScreen) {
                IndoQuranAppBar(
                    modifier = modifier,
                    currentScreen = currentScreen,
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() })
            }
        },
        bottomBar = {
            if (currentScreen != IndoQuranScreen.SplashScreen) {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                            },
                            alwaysShowLabel = false,
                            label = { Text(text = item.title) },
                            icon = {
                                Icon(
                                    modifier = Modifier.size(28.dp),
                                    painter = if (selectedItemIndex == index) {
                                        painterResource(item.selectedIcon)
                                    } else painterResource(item.unSelectedIcon),
                                    contentDescription = item.title,
                                    tint = if (selectedItemIndex == index)
                                        MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(
                                        alpha = 0.8f
                                    ),

                                    )
                            }
                        )
                    }
                }
            }
        }
    ) { interPadding ->
        NavHost(
            navController = navController,
            startDestination = IndoQuranScreen.SplashScreen.name,
            modifier = Modifier.padding(interPadding)
        ) {
            composable(route = IndoQuranScreen.SplashScreen.name) {
                CustomSplashScreen(navigateToHome = {
                    navController.navigate(IndoQuranScreen.Home.name) {
                        popUpTo(navController.graph.startDestinationRoute!!) { inclusive = true }
                    }

                })
            }
            composable(route = IndoQuranScreen.Home.name) {
                HomeScreen()
            }

            composable(route = IndoQuranScreen.Quran.name) {
                SurahScreen()
            }


            composable(route = IndoQuranScreen.Hadist.name) {
                HaditsScreen()
            }


            composable(route = IndoQuranScreen.Doa.name) {
                DoaScreen()
            }


            composable(route = IndoQuranScreen.JadwalSholat.name) {
                JadwalSholatScreen()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    IndoQuranTheme(darkTheme = true) {
        MainScreen()
    }
}