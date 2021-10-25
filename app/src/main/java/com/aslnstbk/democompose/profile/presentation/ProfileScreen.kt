package com.aslnstbk.democompose.profile.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aslnstbk.democompose.global.data.ResponseData
import com.aslnstbk.democompose.global.presentation.ui.theme.DemoComposeTheme
import com.aslnstbk.democompose.profile.domain.models.User
import com.aslnstbk.democompose.profile.presentation.ui.components.ProfileCard
import org.koin.androidx.compose.get

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = get()) {
    val state = viewModel.profileState.observeAsState().value

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        when (state) {
            is ResponseData.Success -> {
                ProfileScreenContent(user = state.data)
            }
            is ResponseData.Error -> {}
        }
    }
}

@Composable
private fun ProfileScreenContent(user: User) {
    ProfileCard(user = user)
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    DemoComposeTheme(darkTheme = true) {
        ProfileScreen()
    }
}