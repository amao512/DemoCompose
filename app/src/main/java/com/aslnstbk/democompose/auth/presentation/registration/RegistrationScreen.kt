package com.aslnstbk.democompose.auth.presentation.registration

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import com.aslnstbk.democompose.auth.data.models.RegistrationParam
import com.aslnstbk.democompose.global.presentation.states.RedirectActivityState
import com.aslnstbk.democompose.global.presentation.states.ShowErrorState
import com.aslnstbk.democompose.global.presentation.ui.components.EditText
import com.aslnstbk.democompose.global.presentation.ui.theme.DemoComposeTheme
import org.koin.androidx.compose.get

@Composable
fun RegistrationScreen(
    navController: NavController,
    redirectToActivity: (RedirectActivityState) -> Unit,
    viewModel: RegistrationViewModel = get()
) {
    val redirectToActivityState = viewModel.redirectActivityState.value
    val errorState = viewModel.showErrorState.value

    val nameInputValue = remember { mutableStateOf(TextFieldValue()) }
    val surnameInputValue = remember { mutableStateOf(TextFieldValue()) }
    val phoneInputValue = remember { mutableStateOf(TextFieldValue()) }
    val emailInputValue = remember { mutableStateOf(TextFieldValue()) }
    val passwordInputValue = remember { mutableStateOf(TextFieldValue()) }

    val confirmPasswordInputValue = remember { mutableStateOf(TextFieldValue()) }
    val isDoctor = remember { mutableStateOf(false) }
    val doctorButtonColor = animateColorAsState(
        targetValue = if (isDoctor.value) MaterialTheme.colors.secondaryVariant else Color.Gray
    )
    val patientButtonColor = animateColorAsState(
        targetValue = if (!isDoctor.value) MaterialTheme.colors.secondaryVariant else Color.Gray
    )

    if (redirectToActivityState.isRedirect && redirectToActivityState.activity.isNotBlank()) {
        redirectToActivity(redirectToActivityState)
    }

    RegistrationContent(
        nameInputValue = nameInputValue,
        surnameInputValue = surnameInputValue,
        phoneInputValue = phoneInputValue,
        emailInputValue = emailInputValue,
        passwordInputValue = passwordInputValue,
        confirmPasswordInputValue = confirmPasswordInputValue,
        isDoctor = isDoctor,
        doctorButtonColor = doctorButtonColor,
        patientButtonColor = patientButtonColor,
        popBackStack = {
            navController.popBackStack()
        },
        onRegisterClick = {
            viewModel.onRegister(param = RegistrationParam(
                email = emailInputValue.value.text,
                password = passwordInputValue.value.text,
                name = nameInputValue.value.text,
                surname = surnameInputValue.value.text,
                phoneNumber = phoneInputValue.value.text,
                isDoctor = isDoctor.value
            ))
        },
        errorState = errorState
    )
}

@Composable
private fun RegistrationContent(
    nameInputValue: MutableState<TextFieldValue>,
    surnameInputValue: MutableState<TextFieldValue>,
    phoneInputValue: MutableState<TextFieldValue>,
    emailInputValue: MutableState<TextFieldValue>,
    passwordInputValue: MutableState<TextFieldValue>,
    confirmPasswordInputValue: MutableState<TextFieldValue>,
    isDoctor: MutableState<Boolean>,
    doctorButtonColor: State<Color>,
    patientButtonColor: State<Color>,
    popBackStack: () -> Unit,
    onRegisterClick: () -> Unit,
    errorState: ShowErrorState
) {
    val isButtonEnabled = (nameInputValue.value.text.isNotBlank() && surnameInputValue.value.text.isNotBlank()
                        && phoneInputValue.value.text.isNotBlank() && emailInputValue.value.text.isNotBlank()
                        && passwordInputValue.value.text.isNotBlank() && confirmPasswordInputValue.value.text.isNotBlank())
                        && passwordInputValue.value.text == confirmPasswordInputValue.value.text

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = MaterialTheme.colors.background),
        contentAlignment = Alignment.BottomStart
    ) {
        LazyColumn(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colors.primaryVariant,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.registration),
                    fontSize = 28.sp,
                    color = MaterialTheme.colors.secondaryVariant,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )

                EditText(
                    value = nameInputValue.value,
                    onValueChanged = { nameInputValue.value = it },
                    placeholder = stringResource(id = R.string.enter_name),
                    keyboardType = KeyboardType.Text
                )

                EditText(
                    value = surnameInputValue.value,
                    onValueChanged = { surnameInputValue.value = it },
                    placeholder = stringResource(id = R.string.enter_surname),
                    keyboardType = KeyboardType.Text
                )

                EditText(
                    value = phoneInputValue.value,
                    onValueChanged = { phoneInputValue.value = it },
                    placeholder = stringResource(id = R.string.enter_phone),
                    keyboardType = KeyboardType.Phone
                )

                EditText(
                    value = emailInputValue.value,
                    onValueChanged = { emailInputValue.value = it },
                    placeholder = stringResource(id = R.string.enter_email),
                    keyboardType = KeyboardType.Email
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { isDoctor.value = true },
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = doctorButtonColor.value
                        ),
                        modifier = Modifier
                            .padding(16.dp)
                            .weight(1f)
                    ) {
                        Text(
                            text = stringResource(id = R.string.i_am_doctor),
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            modifier = Modifier.padding(6.dp)
                        )
                    }

                    Button(
                        onClick = { isDoctor.value = false },
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = patientButtonColor.value
                        ),
                        modifier = Modifier
                            .padding(16.dp)
                            .weight(1f)
                    ) {
                        Text(
                            text = stringResource(id = R.string.i_am_patient),
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            modifier = Modifier.padding(6.dp)
                        )
                    }
                }

                EditText(
                    value = passwordInputValue.value,
                    onValueChanged = { passwordInputValue.value = it },
                    placeholder = stringResource(id = R.string.enter_password),
                    keyboardType = KeyboardType.Password
                )

                EditText(
                    value = confirmPasswordInputValue.value,
                    onValueChanged = { confirmPasswordInputValue.value = it },
                    placeholder = stringResource(id = R.string.confirm_password),
                    keyboardType = KeyboardType.Password
                )

                Button(
                    onClick = { onRegisterClick() },
                    enabled = isButtonEnabled,
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
                        text = stringResource(id = R.string.sign_up),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(6.dp),
                        color = Color.White
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.already_have_account), color = Color.Gray)
                    Text(
                        text = stringResource(id = R.string.sign_in),
                        color = MaterialTheme.colors.secondaryVariant,
                        modifier = Modifier
                            .padding(start = 2.dp)
                            .clickable { popBackStack() },
                    )
                }
            }
        }

        if (errorState.error.isNotBlank()) {
            Box(modifier = Modifier.padding(16.dp)) {
                Snackbar(snackbarData = object : SnackbarData {
                    override val actionLabel: String?
                        get() = null
                    override val duration: SnackbarDuration
                        get() = SnackbarDuration.Short
                    override val message: String
                        get() = errorState.error

                    override fun dismiss() {}

                    override fun performAction() {}
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegistrationScreenPreview() {
    DemoComposeTheme(darkTheme = true) {
        RegistrationScreen(rememberNavController(), {})
    }
}