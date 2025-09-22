package com.example.tugaspam3

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RegistrationScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen() {
    var nim by remember { mutableStateOf("") }
    var nama by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var tanggalLahir by remember { mutableStateOf("") }

    // Setup DatePicker
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            tanggalLahir = "$selectedDay/${selectedMonth + 1}/$selectedYear"
        },
        year, month, day
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Form Registrasi",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(28.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Input NIM
                CustomTextField(
                    value = nim,
                    onValueChange = { nim = it },
                    label = "NIM",
                    placeholder = "Masukkan NIM Anda",
                    icon = Icons.Filled.Info,
                    keyboardType = KeyboardType.Number
                )

                // Input Nama
                CustomTextField(
                    value = nama,
                    onValueChange = { nama = it },
                    label = "Nama",
                    placeholder = "Masukkan nama lengkap Anda",
                    icon = Icons.Filled.Person
                )

                // Input Email
                CustomTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = "Email",
                    placeholder = "contoh@email.com",
                    icon = Icons.Filled.Email,
                    keyboardType = KeyboardType.Email
                )

                // Input Tanggal Lahir pakai DatePicker
                OutlinedTextField(
                    value = tanggalLahir,
                    onValueChange = { },
                    label = { Text("Tanggal Lahir") },
                    placeholder = { Text("DD/MM/YYYY") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.DateRange,
                            contentDescription = "Tanggal Lahir"
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { datePickerDialog.show() },
                    enabled = false, // user tidak bisa ketik manual
                    colors = OutlinedTextFieldDefaults.colors(
                        disabledTextColor = Color.Black,
                        disabledBorderColor = Color(0xFF6366F1),
                        disabledLabelColor = Color(0xFF6366F1)
                    )
                )

                // Tombol DAFTAR
                Button(
                    onClick = {
                        println("=== DATA PENDAFTARAN ===")
                        println("NIM: $nim")
                        println("Nama: $nama")
                        println("Email: $email")
                        println("Tanggal Lahir: $tanggalLahir")
                        println("========================")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6366F1)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Daftar"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("DAFTAR")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    icon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, color = Color(0xFF6366F1)) },
        placeholder = { Text(text = placeholder, color = Color.Gray) },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color(0xFF6366F1)
            )
        },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF6366F1),
            unfocusedBorderColor = Color(0xFFE5E7EB),
            focusedLabelColor = Color(0xFF6366F1),
            cursorColor = Color(0xFF6366F1)
        ),
        shape = RoundedCornerShape(12.dp)
    )
}
