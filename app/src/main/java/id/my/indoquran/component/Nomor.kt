package id.my.indoquran.component

import androidx.compose.ui.graphics.Path

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.my.indoquran.ui.theme.IndoQuranTheme

@Composable
fun CustomShapeComponent(
    modifier: Modifier = Modifier,
    boxSize: Dp = 40.dp,
    textSize: TextUnit = 13.sp,
    nomor: Int
) {
    Box(
        modifier = modifier
            .size(boxSize)
            .wrapContentSize(Alignment.Center)
    ) {
        // Canvas untuk menggambar shape
        Canvas(modifier = Modifier.size(boxSize)) {
            val path = Path().apply {
                moveTo(size.width, size.height * 0.5f)
                lineTo(size.width * 0.85f, size.height * 0.6454f)
                lineTo(size.width * 0.8536f, size.height * 0.8536f)
                lineTo(size.width * 0.6454f, size.height * 0.8511f)
                lineTo(size.width * 0.5f, size.height)
                lineTo(size.width * 0.3546f, size.height * 0.8511f)
                lineTo(size.width * 0.1464f, size.height * 0.8536f)
                lineTo(size.width * 0.1489f, size.height * 0.6454f)
                lineTo(0f, size.height * 0.5f)
                lineTo(size.width * 0.1489f, size.height * 0.3546f)
                lineTo(size.width * 0.1464f, size.height * 0.1464f)
                lineTo(size.width * 0.3546f, size.height * 0.1489f)
                lineTo(size.width * 0.5f, 0f)
                lineTo(size.width * 0.6454f, size.height * 0.1489f)
                lineTo(size.width * 0.8536f, size.height * 0.1464f)
                lineTo(size.width * 0.8511f, size.height * 0.3546f)
                close()
            }

            drawPath(
                path = path,
                color = Color(0xFF00957B), // cPrimary
                style = Stroke(width = 4f)
            )
        }

        // Teks di tengah
        Text(
            text = "$nomor",
            color = Color(0xFF00957B), // cPrimary
            fontSize = textSize.value.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNomor(){
    IndoQuranTheme {
        CustomShapeComponent(modifier = Modifier, nomor = 1)
    }
}
