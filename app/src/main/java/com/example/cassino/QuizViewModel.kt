package com.example.cassino

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuizViewModel : ViewModel() {

    val perguntas = listOf(
        Pergunta(
            "Qual a capital da frança?",
            listOf("Paris", "Versalhes", "Marselha", "nice"),
            0
        ),
        Pergunta(
            "Qual é o maior planeta do Sistema Solar?",
            listOf("Terra", "júpter", "marte", "saturno"),
            1
        ),
        Pergunta(
            "O que significa a sigla MVVM?",
            listOf(
                "Model View ViewModel",
                "Main View Variable Model",
                "Model Variable View Manager",
                "Multi View Virtual Model"
            ),
            0
        ),
        Pergunta(
            "Quem foi o artista responsável pela pintura da Mona Lisa?",
            listOf("Vicent van gogh", "pablo picasso", "Leonardo da vinci", "michelangelo"),
            2
        ),
        Pergunta(
            "Qual função guarda estado que sobrevive a recomposições?",
            listOf("remember", "launch", "println", "listOf"),
            0
        )
    )

    var nomeJogador by mutableStateOf("")
        private set

    var indiceAtual by mutableIntStateOf(0)
        private set

    var acertos by mutableIntStateOf(0)
        private set

    // null = ainda não respondeu a pergunta atual
    var respostaSelecionada by mutableStateOf<Int?>(null)
        private set

    var quizFinalizado by mutableStateOf(false)
        private set

    val perguntaAtual: Pergunta
        get() = perguntas[indiceAtual]

    fun onNomeChange(novoNome: String) {
        nomeJogador = novoNome
    }

    fun iniciarQuiz() {
        indiceAtual = 0
        acertos = 0
        respostaSelecionada = null
        quizFinalizado = false
    }

    fun responder(indice: Int) {
        // Ignora cliques enquanto a cor está sendo mostrada
        if (respostaSelecionada != null) return

        respostaSelecionada = indice
        if (indice == perguntaAtual.indiceCorreto) {
            acertos++
        }

        viewModelScope.launch {
            delay(1200) // tempo para o usuário ver verde/vermelho
            if (indiceAtual < perguntas.lastIndex) {
                indiceAtual++
                respostaSelecionada = null
            } else {
                quizFinalizado = true
            }
        }
    }

    fun reiniciar() {
        iniciarQuiz()
        nomeJogador = ""
    }
}