package com.aslnstbk.democompose.profile.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aslnstbk.democompose.R
import com.aslnstbk.democompose.global.data.ResponseData
import com.aslnstbk.democompose.global.presentation.states.RedirectActivityState
import com.aslnstbk.democompose.global.presentation.ui.theme.DemoComposeTheme
import com.aslnstbk.democompose.profile.domain.models.User
import com.aslnstbk.democompose.profile.presentation.models.ProfileAction
import com.aslnstbk.democompose.profile.presentation.ui.components.ProfileCard
import com.google.firebase.auth.FirebaseUser
import org.koin.androidx.compose.get

@Composable
fun ProfileScreen(
    profileId: String? = null,
    viewModel: ProfileViewModel = get(),
    firebaseUser: FirebaseUser = get(),
    redirectToActivity: (RedirectActivityState) -> Unit
) {
    viewModel.obtainEvent(event = ProfileAction.GetProfile(uid = profileId))

    val state = viewModel.profileState.observeAsState().value
    val isOwnProfile = viewModel.isOwnProfile.observeAsState().value
    val redirectToActivityState = viewModel.redirectToActivity.observeAsState().value

    redirectToActivityState?.let {
        if (it.isRedirect && it.activity.isNotBlank()) {
            redirectToActivity(it)
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        when (state) {
            is ResponseData.Success -> {
                ProfileScreenContent(
                    user = state.data,
                    isOwnProfile = isOwnProfile ?: true,
                    firebaseUser = firebaseUser,
                    onAddClick = {
                        viewModel.obtainEvent(
                            event = ProfileAction.AddUser(uid = state.data.id)
                        )
                    },
                    onExitClick = {
                        viewModel.obtainEvent(event = ProfileAction.Exit)
                    }
                )
            }
            is ResponseData.Error -> {
            }
        }
    }
}

@Composable
private fun ProfileScreenContent(
    user: User,
    isOwnProfile: Boolean,
    firebaseUser: FirebaseUser,
    onAddClick: () -> Unit,
    onExitClick: () -> Unit
) {
    ProfileCard(user = user)
    Divider(
        thickness = 0.dp,
        modifier = Modifier.padding(10.dp)
    )

    if (!isOwnProfile) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = 1.dp,
            shape = RoundedCornerShape(10.dp),
            backgroundColor = MaterialTheme.colors.primary
        ) {
            if (!user.checkIfExists(firebaseUser.uid)) {
                Button(
                    modifier = Modifier.padding(16.dp),
                    onClick = onAddClick,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.secondaryVariant
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.add),
                        color = Color.White
                    )
                }
            }
        }
    } else {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onExitClick,
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Red
            )
        ) {
            Text(
                text = stringResource(id = R.string.exit),
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    DemoComposeTheme(darkTheme = true) {
        ProfileScreen(redirectToActivity = {})
    }
}