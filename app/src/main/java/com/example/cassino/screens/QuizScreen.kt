package com.example.cassino.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cassino.Pergunta
import com.example.cassino.R
import com.example.cassino.ui.theme.CinzaBordaQuiz
import com.example.cassino.ui.theme.CinzaRespondidaQuiz
import com.example.cassino.ui.theme.RosaQuiz
import com.example.cassino.ui.theme.TextoQuiz
import com.example.cassino.ui.theme.VerdeQuiz
import com.example.cassino.ui.theme.VermelhoQuiz

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
            .background(RosaQuiz)
            .systemBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.logo_quiz),
            contentDescription = "Logo do Quiz",
            modifier = Modifier.size(88.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Selo "Pergunta X de Y"
        Surface(
            color = VerdeQuiz,
            shape = RoundedCornerShape(14.dp),
            border = BorderStroke(1.dp, TextoQuiz),
            modifier = Modifier.fillMaxWidth(0.85f)
        ) {
            Text(
                text = "Pergunta ${indiceAtual + 1} de $totalPerguntas",
                fontSize = 22.sp,
                color = TextoQuiz,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 14.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Cartão branco com pergunta e opções
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text(
                    text = pergunta.texto,
                    fontSize = 24.sp,
                    color = TextoQuiz,
                    modifier = Modifier.padding(bottom = 6.dp)
                )

                pergunta.opcoes.forEachIndexed { indice, opcao ->

                    // Define a cor de cada opção
                    val corFundo = when {
                        respostaSelecionada == null -> Color.White
                        indice == pergunta.indiceCorreto -> VerdeQuiz
                        indice == respostaSelecionada -> VermelhoQuiz
                        else -> CinzaRespondidaQuiz
                    }

                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 56.dp)
                            .background(corFundo, RoundedCornerShape(16.dp))
                            .border(1.dp, CinzaBordaQuiz, RoundedCornerShape(16.dp))
                            .clickable { onResponder(indice) }
                            .padding(horizontal = 18.dp, vertical = 12.dp)
                    ) {
                        Text(
                            text = opcao,
                            fontSize = 20.sp,
                            color = TextoQuiz
                        )
                    }
                }
            }
        }
    }
}
