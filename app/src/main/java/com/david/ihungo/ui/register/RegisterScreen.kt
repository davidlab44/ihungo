package com.david.ihungo.ui.register

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.david.ihungo.R

@Composable
fun RegisterScreen(
    contextActivity: RegisterActivity,
    registerViewModel:RegisterViewModel,
    modifier: Modifier = Modifier
) {
    //val mContext=LocalContext.current.applicationContext
    Column(
        modifier=Modifier.padding(all=40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier=Modifier.padding(top=12.dp,start=0.dp,bottom=12.dp).fillMaxWidth()) {
            Text(text="Ingresa tus datos de registro", fontSize = 18.sp,color= Color(0xFF0D5788), fontWeight = FontWeight.Bold)
        }

        Row() {
            OutlinedTextField(
                value = registerViewModel.name,
                onValueChange = { typedEmail ->
                    //email = typedEmail
                    registerViewModel.name= typedEmail.trim()
                },
                modifier = modifier
                    .fillMaxWidth()
                    //.width(342.dp)
                    .padding(bottom = 9.dp),
                singleLine = true,
                label = { Text(text = "Nombre") },
                placeholder = { Text("") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "Nombre"
                    )
                },
            )
        }

        Row() {
            OutlinedTextField(
                value = registerViewModel.email,
                onValueChange = { typedEmail ->
                    //email = typedEmail
                    registerViewModel.email= typedEmail.trim()
                },
                modifier = modifier
                    .fillMaxWidth()
                    //.width(342.dp)
                    .padding(bottom = 9.dp),
                singleLine = true,
                label = { Text(text = "Usuario") },
                placeholder = { Text("") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "Usuario"
                    )
                },
            )
        }

        var passwordVisibility by remember { mutableStateOf(false)}

        val passwordTrailingIcon = if (passwordVisibility)
            painterResource(id = R.drawable.visibility_fill1_wght400_grad0_opsz24)
        else
            painterResource(id = R.drawable.visibility_off_fill1_wght400_grad0_opsz24)

        Row() {
            OutlinedTextField(
                value = registerViewModel.password,
                onValueChange = { typedPassword ->
                    //password = typedPassword
                    registerViewModel.password= typedPassword.trim()
                },
                modifier = modifier
                    .fillMaxWidth(),
                //.width(342.dp),
                singleLine = true,
                label = { Text(text = "Contraseña") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Lock,
                        contentDescription = "Contraseña"
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            passwordVisibility = !passwordVisibility
                        }
                    ) {
                        Icon(
                            painter = passwordTrailingIcon,
                            contentDescription = "Password Visibility"
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = if (passwordVisibility) VisualTransformation.None
                else
                    PasswordVisualTransformation()
            )
        }

        Row(modifier=Modifier.padding(top=200.dp)) {
            Button(
                onClick = {
                    registerViewModel.validateRegister(registerViewModel.name,registerViewModel.email,registerViewModel.password,contextActivity)
                },
                modifier
                    .fillMaxWidth()
                    //.width(342.dp)
                    .height(64.dp)
                    .padding(top = 15.dp, bottom = 0.dp)
            ) {
                Text(
                    "Enviar Registro",
                    fontSize = 17.sp
                )
            }
        }
    }
}