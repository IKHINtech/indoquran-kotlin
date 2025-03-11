package id.my.indoquran

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import id.my.indoquran.ui.theme.IndoQuranTheme
import id.my.indoquran.ui.screens.mainscreen.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IndoQuranTheme(darkTheme = false) {
                MainScreen()
            }
        }
    }
}
