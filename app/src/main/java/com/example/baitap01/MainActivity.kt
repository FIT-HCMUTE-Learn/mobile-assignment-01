package com.example.baitap01

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baitap01.ui.theme.Baitap01Theme
import com.example.baitap01.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val numbers = arrayListOf(54, 23, 12, 46, 74)
        for (num in numbers) {
            if (num % 2 == 0) {
                Log.d("Số chẵn", num.toString())
            } else {
                Log.d("Số lẻ", num.toString())
            }
        }

        setContent {
            Baitap01Theme {
                MainScreen { reversedText ->
                    Toast.makeText(this, reversedText, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onToastMessage: (String) -> Unit) {
    var inputText by remember { mutableStateOf("") }
    var reversedText by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Thông tin sinh viên",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF6200EA))
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.student_image),
                    contentDescription = "Student Image",
                    modifier = Modifier
                        .size(120.dp)
                        .border(2.dp, Color.Black, CircleShape),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = "Tên: Nguyễn Văn A\nMSSV: 12345678",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    label = { Text("Nhập chuỗi bất kỳ") },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        reversedText = inputText
                            .split(" ")
                            .reversed()
                            .joinToString(" ")
                            .uppercase()

                        onToastMessage(reversedText)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Xử lý chuỗi")
                }

                Text(
                    text = "Kết quả: $reversedText",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6200EA),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )
}
