package id.my.indoquran.ui

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.my.indoquran.ui.theme.IndoQuranTheme
import androidx.compose.animation.core.tween
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.my.indoquran.IndoQuranScreen
import id.my.indoquran.R
import kotlinx.coroutines.delay


@Composable
fun CustomSplashScreen(
    navigateToHome: () -> Unit
) {
    var targetSizeOuter by remember { mutableStateOf(180.dp) } // Ukuran awal kecil
    var targetSizeMiddle by remember { mutableStateOf(160.dp) }

    // Memulai animasi setelah Composable dirender
    LaunchedEffect(Unit) {
        targetSizeOuter = 210.dp
        targetSizeMiddle = 190.dp

        delay(1000) // Tunggu 2 detik sebelum pindah
        navigateToHome()
    }

    val animatedSizeOuter by animateDpAsState(
        targetValue = targetSizeOuter, // Animasi ke ukuran akhir
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
        label = "Outer Box Animation"
    )

    val animatedSizeMiddle by animateDpAsState(
        targetValue = targetSizeMiddle, // Ukuran akhir
        animationSpec = tween(
            durationMillis = 1000,
            delayMillis = 200,
            easing = FastOutSlowInEasing
        ), // Delay 200ms
        label = "Middle Box Animation"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
// animasi size box ini
                modifier = Modifier
                    .size(animatedSizeOuter)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.5f))
                    .padding(20.dp)
            ) {
                Box(
// animasi size box ini
                    modifier = Modifier
                        .size(animatedSizeMiddle)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.5f))
                        .padding(20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.5f))
                            .padding(20.dp)
                            .align(Alignment.Center)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.logo_indoquran),
                            contentDescription = "Logo",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                "IndoQur`an",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                "Qur`an dan Terjemah",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CustomSplashScreenPreview() {
    IndoQuranTheme {
        val navController = rememberNavController()
        CustomSplashScreen(navigateToHome = {
            navController.navigate(IndoQuranScreen.Home.name)

        })
    }
}