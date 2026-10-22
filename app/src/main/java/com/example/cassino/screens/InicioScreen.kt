package com.example.cassino.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cassino.R
import com.example.cassino.ui.theme.AmareloQuiz
import com.example.cassino.ui.theme.AzulQuiz
import com.example.cassino.ui.theme.TextoQuiz

@Composable
fun InicioScreen(
    onIniciar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AzulQuiz)
            .systemBarsPadding()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logo_quiz),
            contentDescription = "Logo do Quiz",
            modifier = Modifier.size(180.dp)
        )

        Spacer(modifier = Modifier.height(72.dp))

        Text(
            text = "QUIZATRON 3000",
            fontSize = 32.sp,
            color = TextoQuiz,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = onIniciar,
            shape = CircleShape,
            border = BorderStroke(1.dp, TextoQuiz),
            colors = ButtonDefaults.buttonColors(
                containerColor = AmareloQuiz,
                contentColor = TextoQuiz
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text(text = "COMEÇAR!", fontSize = 20.sp)
        }
    }
}
