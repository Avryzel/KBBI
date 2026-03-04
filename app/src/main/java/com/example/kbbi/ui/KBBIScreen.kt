package com.example.kbbi.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kbbi.ui.game.KBBIViewModel
import com.example.kbbi.ui.theme.KBBITheme

@Composable
fun KBBIScreen(viewModel: KBBIViewModel) {
    val gameUiState by viewModel.uiState.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Surface(
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(60.dp),
        ) {
            when (gameUiState.isCorrect) {
                true -> {
                    Text(
                        text = "BENAR!",
                        textAlign = TextAlign.Center,
                        fontSize = 26.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.primary)
                            .padding(15.dp)
                    )
                }
                false -> {
                    Text(
                        text = "SALAH!",
                        textAlign = TextAlign.Center,
                        fontSize = 26.sp,
                        color = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.primary)
                            .padding(15.dp)
                    )
                }
                else -> {

                }
            }
        }

        Spacer(Modifier.height(100.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            Text(
                text = "${gameUiState.streak}",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary,
            )
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .size(100.dp)
            )
        }

        Spacer(Modifier.height(100.dp))

        KBBIOptionButton(
            word = "Abjad",
            backgroundColor = MaterialTheme.colorScheme.primary,
            onClick = {}
        )

        Spacer(Modifier.height(30.dp))

        KBBIOptionButton(
            word = "Abjat",
            backgroundColor = MaterialTheme.colorScheme.tertiary,
            onClick = {}
        )
    }
}

@Composable
private fun KBBIOptionButton(
    word: String,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Column {
        TextButton(
            onClick = onClick,
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.textButtonColors(
                containerColor = backgroundColor
            ),
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(56.dp)
        ) {
            Text(
                text = word,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 18.sp
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun KBBIScreenPreview() {
    KBBITheme {
        KBBIScreen(
            viewModel = TODO()
        )
    }
}