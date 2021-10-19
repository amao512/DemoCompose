package com.aslnstbk.democompose.profile.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.aslnstbk.democompose.global.data.ResponseData
import com.aslnstbk.democompose.global.presentation.ui.theme.DemoComposeTheme
import com.aslnstbk.democompose.profile.domain.models.User
import org.koin.androidx.compose.get

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = get()) {
    val state = viewModel.profileState.value

    Column(modifier = Modifier.padding(16.dp)) {
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
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = rememberImagePainter("https://www.pixsy.com/wp-content/uploads/2021/04/ben-sweet-2LowviVHZ-E-unsplash-1.jpeg"),
            contentDescription = user.email,
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape)
                .border(1.dp, MaterialTheme.colors.secondaryVariant, CircleShape)
        )

        Text(
            text = user.displayName,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    DemoComposeTheme(darkTheme = true) {
        ProfileScreen()
    }
}