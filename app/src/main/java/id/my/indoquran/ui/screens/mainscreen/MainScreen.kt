package id.my.indoquran.ui.screens.mainscreen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import id.my.indoquran.R
import id.my.indoquran.navigation.NavigationApp
import id.my.indoquran.ui.theme.IndoQuranTheme


enum class IndoQuranScreen(@DrawableRes title: Int) {
    Home(title = R.string.home_screen),
    Quran(title = R.string.quran_screen),
    Hadist(title = R.string.hadits_screen),
    Doa(title = R.string.doa_screen),
    JadwalSholat(R.string.jadwal_sholat_screen),
    SplashScreen(R.string.app_name)
}

data class BottomNavigationItem(
    val title: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unSelectedIcon: Int,
    val onClick: () -> Unit,
)


@ExperimentalMaterial3Api
@Composable
fun IndoQuranMainAppBar(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {},
    canNavigateBack: Boolean,
) {
    TopAppBar(title = {
        Text(
            stringResource(R.string.app_name), style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
            )
        )
    }, navigationIcon = {
        if (canNavigateBack) {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back Button"
                )
            }
        } else Image(
            painter = painterResource(R.drawable.logo_indoquran),
            contentDescription = "Logo IndoQur`an",
            modifier = Modifier
                .size(
                    60.dp
                )
                .padding(8.dp)
        )
    }, actions = {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Filled.Settings, contentDescription = "Setting Icon",
                tint = MaterialTheme.colorScheme.primary,
            )
        }
    }, colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.background
    ), modifier = modifier
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showBackground = true, //showSystemUi = true
)
@Composable
fun IndoQuranAppBarPreview() {
    IndoQuranTheme {
        IndoQuranMainAppBar(
            modifier = Modifier, canNavigateBack = false
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun IndoQuranAppBarPreviewDark() {
    IndoQuranTheme(darkTheme = true) {
        IndoQuranMainAppBar(
            modifier = Modifier, canNavigateBack = false
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier, navController: NavHostController = rememberNavController()
) {
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
    val items = listOf(
        BottomNavigationItem(title = stringResource(R.string.home_screen),
            selectedIcon = R.drawable.home,
            unSelectedIcon = R.drawable.home_outline,
            onClick = {
                navController.navigate(IndoQuranScreen.Home.name) {
                    popUpTo(IndoQuranScreen.Home.name) { inclusive = true }
                    launchSingleTop = true
                }
            }),
        BottomNavigationItem(title = stringResource(R.string.quran_screen),
            selectedIcon = R.drawable.all_quran_fullfiled,
            unSelectedIcon = R.drawable.al_quran_outline,
            onClick = {
                navController.navigate(IndoQuranScreen.Quran.name) {
                    popUpTo(IndoQuranScreen.Home.name) { inclusive = true }
                    launchSingleTop = true
                }
            }),
        BottomNavigationItem(title = stringResource(R.string.hadits_screen),
            selectedIcon = R.drawable.book_filled,
            unSelectedIcon = R.drawable.book_outline,
            onClick = {
                navController.navigate(IndoQuranScreen.Hadist.name) {
                    popUpTo(IndoQuranScreen.Home.name) { inclusive = true }
                    launchSingleTop = true
                }
            }),
        BottomNavigationItem(title = stringResource(R.string.doa_screen),
            selectedIcon = R.drawable.pray,
            unSelectedIcon = R.drawable.pray_outline,
            onClick = {
                navController.navigate(IndoQuranScreen.Doa.name) {
                    popUpTo(IndoQuranScreen.Home.name) { inclusive = true }
                    launchSingleTop = true
                }
            }),
        BottomNavigationItem(title = stringResource(R.string.jadwal_sholat_screen),
            selectedIcon = R.drawable.sholat,
            unSelectedIcon = R.drawable.sholat_outline,
            onClick = {
                navController.navigate(IndoQuranScreen.JadwalSholat.name) {
                    popUpTo(IndoQuranScreen.Home.name) { inclusive = true }
                    launchSingleTop = true
                }
            }),
    )

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = IndoQuranScreen.valueOf(
        backStackEntry?.destination?.route ?: IndoQuranScreen.SplashScreen.name
    )

    Scaffold(topBar = {
        if (currentScreen != IndoQuranScreen.SplashScreen) {
            IndoQuranMainAppBar(modifier = modifier,
                canNavigateBack = false,
                navigateUp = { navController.navigateUp() })
        }
    }, bottomBar = {
        if (currentScreen != IndoQuranScreen.SplashScreen) {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            item.onClick()
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
                                tint = if (selectedItemIndex == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(
                                    alpha = 0.8f
                                ),

                                )
                        })
                }
            }
        }
    }) { interPadding ->
        Box(modifier = Modifier.padding(interPadding)) {
            NavigationApp()
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