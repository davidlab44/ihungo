package com.david.ihungo.ui.login

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.RowScopeInstance.align
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.david.ihungo.R
import com.david.ihungo.ui.MainActivity
import com.david.ihungo.ui.register.RegisterActivity

@Composable
fun LoginScreen(
    contextActivity:LoginActivity,
    loginViewModel:LoginViewModel,
    modifier: Modifier = Modifier
) {
    //val mContext=LocalContext.current.applicationContext
    Column(
        modifier=Modifier.padding(all=40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier=Modifier.padding(top=150.dp,start=0.dp,bottom=90.dp).fillMaxWidth()) {
            Image(
                painterResource(id = R.drawable.logo4),
                contentDescription = "Seedgrower",
                //modifier.fillMaxWidth()
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = CircleShape),
            )
        }
        // val emailIsValid = email.isEmailValid()
        Row() {
            OutlinedTextField(
                value = loginViewModel.email,
                onValueChange = { typedEmail ->
                    //email = typedEmail
                    loginViewModel.email= typedEmail.trim()
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 9.dp),
                singleLine = true,
                label = { Text(text = "User") },
                placeholder = { Text("") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "Email"
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
                value = loginViewModel.password,
                onValueChange = { typedPassword ->
                    //password = typedPassword
                    loginViewModel.password= typedPassword.trim()
                },
                modifier = modifier
                    .fillMaxWidth(),
                //.width(342.dp),
                singleLine = true,
                label = { Text(text = "Password") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Lock,
                        contentDescription = "Password"
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

        Row(modifier=Modifier.padding(top=50.dp)) {
            //Login Button
            Button(
                onClick = {
                    loginViewModel.validateLogin(loginViewModel.email,loginViewModel.password,contextActivity)
                },
                modifier
                    .fillMaxWidth()
                    //.width(342.dp)
                    .height(64.dp)
                    .padding(top = 15.dp, bottom = 0.dp)
            ) {
                Text(
                    "Iniciar Sesión",
                    fontSize = 17.sp
                )
            }
        }

        Row() {
            //Register
            Button(
                onClick = {
                    contextActivity.startActivity(Intent(contextActivity, RegisterActivity::class.java))
                    //authenticableViewModel.login("Admin","Med1co2011", contextActivity)
                    //authenticableViewModel.login(authenticableViewModel.user,authenticableViewModel.password)
                },
                modifier
                    .fillMaxWidth()
                    //.width(342.dp)
                    .height(64.dp)
                    .padding(top = 15.dp, bottom = 0.dp)
            ) {
                Text(
                    "Registrarse",
                    fontSize = 17.sp
                )
            }
        }
    }
}