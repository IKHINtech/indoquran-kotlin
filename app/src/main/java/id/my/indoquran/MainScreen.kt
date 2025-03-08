package id.my.indoquran

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.my.indoquran.ui.theme.IndoQuranTheme
import androidx.compose.material.icons.extended.*
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.ImportContacts
import androidx.compose.material.icons.filled.MenuBook


enum class IndoQuranScreen() {
    Home,
    Quran,
    Hadist,
    JadwalSholat
}

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
)


@ExperimentalMaterial3Api
@Composable
fun IndoQuranAppBar(
    modifier: Modifier = Modifier
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
        IndoQuranAppBar(modifier = Modifier)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun IndoQuranAppBarPreviewDark() {
    IndoQuranTheme(darkTheme = true) {
        IndoQuranAppBar(modifier = Modifier)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unSelectedIcon = Icons.Outlined.Home
        ),
        BottomNavigationItem(
            title = "Surah",
            selectedIcon = Icons.Filled.ImportContacts,
            unSelectedIcon = Icons.Filled.ImportContacts,
        ),

        BottomNavigationItem(
            title = "Hadits",
            selectedIcon = Icons.Filled.MenuBook,
            unSelectedIcon = Icons.Default.MenuBook,
        ),
        BottomNavigationItem(
            title = "Doa",
            selectedIcon = Icons.Filled.FavoriteBorder,
            unSelectedIcon = Icons.Default.FavoriteBorder,
        ),
        BottomNavigationItem(
            title = "Sholat",
            selectedIcon = Icons.Filled.CollectionsBookmark,
            unSelectedIcon = Icons.Default.CollectionsBookmark,
        ),
    )
    Scaffold(
        topBar = { IndoQuranAppBar(modifier = modifier) },
        bottomBar = {
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
                                imageVector = if (selectedItemIndex == index) {
                                    item.selectedIcon
                                } else item.unSelectedIcon,
                                contentDescription = item.title,
                                tint = if (selectedItemIndex == index)
                                      MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),

                            )
                        }
                    )
                }
            }
        }
    ) { interPadding ->
        Box(modifier = Modifier.padding(interPadding))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    IndoQuranTheme {
        MainScreen()
    }
}