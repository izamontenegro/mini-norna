package com.minicurso.mininorna

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
 * ============================================================
 *  MINI NORNA · Tutorial prático do minicurso de Jetpack Compose
 * ============================================================
 *  Este é o card de peça do Norna, mas está todo bagunçado:
 *  tudo empilhado, sem espaçamento e sem fundo.
 *
 *  Sua missão: deixar o card IGUAL à imagem objetivo_card.png
 *  (app/src/main/res/drawable/objetivo_card.png — dê dois cliques nela no Android Studio),
 *  mexendo só em layout (Column, Row, Box) e Modifiers.
 *
 *  Você só precisa editar a função GarmentCard (procure os "TODO").
 *  As peças menores (foto, selo, chip, botão Salvar) já estão prontas lá embaixo.
 *
 *  Dica: abra o modo Split e use o Preview "Meu card" para ver cada mudança na hora.
 */

// ---------- Dados (já prontos) ----------

data class Garment(
    val name: String,
    val brand: String,
    val grade: Char,
    val materials: List<String>,
)

val SAMPLE = Garment(
    name = "Calça jeans",
    brand = "Denim & Co",
    grade = 'B',
    materials = listOf("Algodão 98%", "Elastano 2%"),
)

val Fundo = Color(0xFFEEF3FD)
val Azul = Color(0xFF2457E6)
val Cinza = Color(0xFF6E7591)

// ---------- Activity ----------

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Fundo)
                        .statusBarsPadding(),
                ) {
                    GarmentCard(SAMPLE)
                }
            }
        }
    }
}

// ============================================================
//  É AQUI QUE VOCÊ TRABALHA
// ============================================================

@Composable
fun GarmentCard(garment: Garment, modifier: Modifier = Modifier) {

    // TODO 1 · Modifier: o card não tem margem, nem fundo branco, nem cantos arredondados.
    //   Objetivo: 16.dp de margem por fora, fundo branco com cantos de 20.dp e 16.dp de espaço por dentro.
    //   Lembre: a ORDEM dos modifiers importa!
    Column(modifier = modifier) {

        // TODO 2 · Box: o selo "B" está embaixo da foto.
        //   Objetivo: o selo fica POR CIMA da foto, no canto superior direito, afastado 8.dp da borda.
        GarmentPhoto()
        GradeBadge(garment.grade)

        // TODO 3 · Row: nome, marca e "Salvar" estão um embaixo do outro.
        //   Objetivo: nome e marca juntos à esquerda (um embaixo do outro),
        //   e o "Salvar" na mesma linha, encostado à direita e centralizado na vertical.
        Text(garment.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Text(garment.brand, color = Cinza)
        SaveAction()

        // TODO 4 · Row + Arrangement: os chips de material estão empilhados.
        //   Objetivo: chips lado a lado, com 8.dp de espaço entre eles.
        garment.materials.forEach { material ->
            MaterialChip(material)
        }

        // TODO 5 · Modifier + Spacer: o botão está pequeno e colado nos chips.
        //   Objetivo: botão ocupando toda a largura do card e com 16.dp de espaço acima.
        //   (Bônus: coloque também 12.dp de espaço entre a foto e o nome, e entre o nome e os chips.)
        Button(onClick = { }) {
            Text("Ver detalhes do impacto")
        }
    }
}

// ============================================================
//  Peças prontas (não precisa mexer)
// ============================================================

/** Foto da peça (um retângulo colorido no lugar da imagem). */
@Composable
fun GarmentPhoto(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(Color(0xFFB9CBF7)),
        contentAlignment = Alignment.Center,
    ) {
        Text("foto da peça", color = Color(0xFF3B4A7A))
    }
}

/** Selo redondo com a nota de impacto. */
@Composable
fun GradeBadge(grade: Char, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(44.dp)
            .background(Color(0xFF6BA547), CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        Text(grade.toString(), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }
}

/** Botão de texto "♡ Salvar". */
@Composable
fun SaveAction(modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Filled.FavoriteBorder, contentDescription = null, tint = Azul)
        Spacer(Modifier.width(4.dp))
        Text("Salvar", color = Azul, fontWeight = FontWeight.SemiBold)
    }
}

/** Chip com um material da composição. */
@Composable
fun MaterialChip(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontSize = 14.sp,
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(Color(0xFFFFF2CC))
            .padding(horizontal = 12.dp, vertical = 6.dp),
    )
}

// ---------- Preview ----------

@Preview(name = "Meu card", showBackground = true, backgroundColor = 0xFFEEF3FD, widthDp = 360)
@Composable
fun GarmentCardPreview() {
    MaterialTheme {
        GarmentCard(SAMPLE)
    }
}

// Alguns imports (Arrangement, Row, Spacer, padding…) ainda não são usados em GarmentCard:
// eles já estão aqui para quando você completar os TODOs.
