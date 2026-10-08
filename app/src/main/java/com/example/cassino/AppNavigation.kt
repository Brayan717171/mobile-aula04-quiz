package com.example.cassino

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cassino.screens.InicioScreen
import com.example.cassino.screens.QuizScreen
import com.example.cassino.screens.ResultadoScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    viewModel: QuizViewModel = viewModel()
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "inicio",
        modifier = modifier
    ) {
        composable("inicio") {
            InicioScreen(
                nome = viewModel.nomeJogador,
                onNomeChange = { viewModel.onNomeChange(it) },
                onIniciar = {
                    viewModel.iniciarQuiz()
                    navController.navigate("quiz")
                }
            )
        }

        composable("quiz") {
            QuizScreen(
                pergunta = viewModel.perguntaAtual,
                indiceAtual = viewModel.indiceAtual,
                totalPerguntas = viewModel.perguntas.size,
                respostaSelecionada = viewModel.respostaSelecionada,
                quizFinalizado = viewModel.quizFinalizado,
                onResponder = { viewModel.responder(it) },
                onFinalizar = {
                    navController.navigate("resultado") {
                        popUpTo("inicio")
                    }
                }
            )
        }

        composable("resultado") {
            ResultadoScreen(
                nome = viewModel.nomeJogador,
                acertos = viewModel.acertos,
                total = viewModel.perguntas.size,
                onReiniciar = {
                    viewModel.reiniciar()
                    navController.popBackStack("inicio", inclusive = false)
                }
            )
        }
    }
}