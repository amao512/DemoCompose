package com.aslnstbk.democompose.global.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aslnstbk.democompose.global.presentation.ui.theme.DemoComposeTheme

@Composable
fun EditText(
    value: TextFieldValue,
    onValueChanged: (TextFieldValue) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    backgroundColor: Color = MaterialTheme.colors.primaryVariant
) {
    TextField(
        value = value,
        onValueChange = { onValueChanged(it) },
        placeholder = {
            Text(text = placeholder)
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colors.secondaryVariant
                ),
                shape = RoundedCornerShape(16.dp)
            ),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = true,
            keyboardType = keyboardType
        ),
        shape = RoundedCornerShape(16.dp),
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.secondaryVariant,
            disabledTextColor = Color.Transparent,
            backgroundColor = backgroundColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true)
@Composable
fun EditTextPreview() {
    DemoComposeTheme(darkTheme = true) {
        EditText(
            value = TextFieldValue(),
            onValueChanged = {},
            placeholder = "Enter"
        )
    }
}