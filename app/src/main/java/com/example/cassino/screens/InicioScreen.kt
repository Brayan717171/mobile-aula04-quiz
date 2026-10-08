package com.example.cassino.screens

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InicioScreen(
    nome: String,
    onNomeChange: (String) -> Unit,
    onIniciar: () -> Unit,
    modifier: Modifier = Modifier,

) {
    Column(
        modifier = modifier
            .background(Color(0xFFE0E0E0))
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,


    ) {


        Text(

            text = "QUIZ",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF000000)
        )

        Text(
            text = "Teste seus conhecimentos!",
            fontSize = 18.sp ,
            color = Color(0xFF212121)
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = nome,
            onValueChange = onNomeChange,
            label = { Text("Seu nome") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()



        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onIniciar,
            enabled = nome.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("INICIAR")
        }
    }
}