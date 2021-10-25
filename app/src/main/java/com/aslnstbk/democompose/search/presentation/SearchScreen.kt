package com.aslnstbk.democompose.search.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aslnstbk.democompose.R
import com.aslnstbk.democompose.global.data.ResponseData
import com.aslnstbk.democompose.global.presentation.ui.components.EditText
import com.aslnstbk.democompose.global.presentation.ui.theme.DemoComposeTheme
import com.aslnstbk.democompose.global.presentation.utils.toLowerCase
import com.aslnstbk.democompose.search.presentation.ui.components.UserCard
import org.koin.androidx.compose.get

@Composable
fun SearchScreen(viewModel: SearchViewModel = get()) {

    val allUsers = viewModel.allUsers.observeAsState().value
    val inputValue = remember { mutableStateOf(TextFieldValue()) }

    Column(modifier = Modifier.fillMaxSize()) {
        EditText(
            value = inputValue.value,
            onValueChanged = {
                inputValue.value = it
                viewModel.onSearch(query = inputValue.value.text.toLowerCase())
            },
            placeholder = stringResource(id = R.string.search),
            backgroundColor = MaterialTheme.colors.primary
        )

        when (allUsers) {
            is ResponseData.Success -> {
                LazyColumn(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    items(items = allUsers.data) {
                        UserCard(
                            user = it,
                            onClick = {}
                        )
                        Divider(
                            modifier = Modifier.padding(6.dp),
                            thickness = 0.dp
                        )
                    }
                }
            }
            is ResponseData.Error -> {
                Text(text = allUsers.error)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchScreenPreview() {
    DemoComposeTheme(darkTheme = true) {
        SearchScreen()
    }
}