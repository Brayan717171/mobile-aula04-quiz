package com.example.cassino.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cassino.Pergunta

private val Verde = Color(0xFF4CAF50)
private val Vermelho = Color(0xFFF44336)
private val Cinza = Color(0xFFBDBDBD)

@Composable
fun QuizScreen(
    pergunta: Pergunta,
    indiceAtual: Int,
    totalPerguntas: Int,
    respostaSelecionada: Int?,
    quizFinalizado: Boolean,
    onResponder: (Int) -> Unit,
    onFinalizar: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Quando o ViewModel avisa que acabou, navega para o resultado
    LaunchedEffect(quizFinalizado) {
        if (quizFinalizado) onFinalizar()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = "Pergunta ${indiceAtual + 1} de $totalPerguntas",
            fontSize = 16.sp
        )




        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = pergunta.texto,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        pergunta.opcoes.forEachIndexed { indice, opcao ->

            // Define a cor de cada botão
            val cor = when {
                respostaSelecionada == null -> null // cor padrão do botão
                indice == pergunta.indiceCorreto -> Verde
                indice == respostaSelecionada -> Vermelho
                else -> Cinza
            }

            Button(
                onClick = { onResponder(indice) },
                colors = if (cor == null) {
                    ButtonDefaults.buttonColors()
                } else {
                    ButtonDefaults.buttonColors(containerColor = cor)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Text(text = opcao, fontSize = 16.sp)
            }
        }
    }
}