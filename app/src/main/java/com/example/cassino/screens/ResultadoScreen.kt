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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cassino.R
import com.example.cassino.ui.theme.AmareloQuiz
import com.example.cassino.ui.theme.AzulQuiz
import com.example.cassino.ui.theme.TextoQuiz
import com.example.cassino.ui.theme.VerdeQuiz

@Composable
fun ResultadoScreen(
    acertos: Int,
    total: Int,
    onReiniciar: () -> Unit,
    modifier: Modifier = Modifier
) {
    val mensagem = when {
        acertos == total -> "Perfeito!"
        acertos >= total / 2 -> "Bom trabalho!"
        else -> "Continue tentando!"
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(R.drawable.logo_quiz),
            contentDescription = "Logo do Quiz",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Faixa azul com mensagem e pontuação
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(AzulQuiz)
                .padding(horizontal = 32.dp, vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(48.dp)
        ) {
            Surface(
                color = VerdeQuiz,
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, TextoQuiz),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = mensagem,
                    fontSize = 28.sp,
                    color = TextoQuiz,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 18.dp)
                )
            }

            Text(
                text = "Você acertou $acertos de $total perguntas",
                fontSize = 26.sp,
                color = TextoQuiz,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = onReiniciar,
            shape = CircleShape,
            border = BorderStroke(1.dp, TextoQuiz),
            colors = ButtonDefaults.buttonColors(
                containerColor = AmareloQuiz,
                contentColor = TextoQuiz
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .height(60.dp)
        ) {
            Text(text = "JOGAR NOVAMENTE", fontSize = 20.sp)
        }
    }
}
