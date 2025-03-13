package id.my.indoquran.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import id.my.indoquran.ui.screens.doa.DoaScreen
import id.my.indoquran.ui.screens.hadits.HaditsScreen
import id.my.indoquran.ui.screens.home.HomeScreen
import id.my.indoquran.ui.screens.jadwal_sholat.JadwalSholatScreen
import id.my.indoquran.ui.screens.mainscreen.IndoQuranScreen
import id.my.indoquran.ui.screens.surah.SurahScreen

@Composable
fun NavigationApp(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = IndoQuranScreen.Home.name,
    ) {
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