package com.example.cassino

data class Pergunta(
    val texto: String,
    val opcoes: List<String>,
    val indiceCorreto: Int
)