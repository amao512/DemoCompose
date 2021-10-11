package com.aslnstbk.democompose.auth.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aslnstbk.democompose.R
import com.aslnstbk.democompose.auth.presentation.AuthNavRoute
import com.aslnstbk.democompose.global.states.RedirectActivityState
import com.aslnstbk.democompose.global.ui.components.EditText
import com.aslnstbk.democompose.presentation.ui.theme.DemoComposeTheme
import org.koin.androidx.compose.get

@Composable
fun LoginScreen(
    navController: NavController,
    redirectToActivity: (RedirectActivityState) -> Unit,
    viewModel: LoginViewModel = get()
) {
    val redirectToActivityState = viewModel.redirectToActivity.value

    val emailInputValue = remember { mutableStateOf(TextFieldValue()) }
    val passwordInputValue = remember { mutableStateOf(TextFieldValue()) }

    if (redirectToActivityState.isRedirect && redirectToActivityState.activity.isNotBlank()) {
        redirectToActivity(redirectToActivityState)
    }

    LoginContent(
        emailValue = emailInputValue.value,
        setEmailValue = { emailInputValue.value = it },
        passwordValue = passwordInputValue.value,
        setPasswordValue = { passwordInputValue.value = it },
        onSignIn = {
           viewModel.onSignIn(
               email = emailInputValue.value.text,
               password = passwordInputValue.value.text
           )
        },
        onRegistrationClick = {
            navController.navigate(AuthNavRoute.Registration.route)
        }
    )
}

@Composable
private fun LoginContent(
    emailValue: TextFieldValue,
    setEmailValue: (TextFieldValue) -> Unit,
    passwordValue: TextFieldValue,
    setPasswordValue: (TextFieldValue) -> Unit,
    onSignIn: () -> Unit,
    onRegistrationClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = MaterialTheme.colors.background),
        contentAlignment = Alignment.BottomStart
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colors.primaryVariant,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.login),
                fontSize = 28.sp,
                color = MaterialTheme.colors.secondaryVariant,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            EditText(
                value = emailValue,
                onValueChanged = { setEmailValue(it) },
                placeholder = stringResource(id = R.string.enter_email),
                keyboardType = KeyboardType.Email
            )
            EditText(
                value = passwordValue,
                onValueChanged = { setPasswordValue(it) },
                placeholder = stringResource(id = R.string.enter_password),
                keyboardType = KeyboardType.Password
            )
            Button(
                onClick = onSignIn,
                enabled = emailValue.text.isNotBlank() && passwordValue.text.isNotBlank(),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondaryVariant,
                    disabledBackgroundColor = Color.Gray
                ),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.enter),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(6.dp),
                    color = Color.White
                )
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.dont_have_account), color = Color.Gray)
                Text(
                    text = stringResource(id = R.string.sign_up),
                    color = MaterialTheme.colors.secondaryVariant,
                    modifier = Modifier
                        .padding(start = 2.dp)
                        .clickable {
                            onRegistrationClick()
                        },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    DemoComposeTheme(darkTheme = true) {
        LoginScreen(rememberNavController(), {})
    }
}