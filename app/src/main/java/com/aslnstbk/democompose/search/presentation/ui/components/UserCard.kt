package com.aslnstbk.democompose.search.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.aslnstbk.democompose.global.presentation.ui.theme.DemoComposeTheme
import com.aslnstbk.democompose.profile.domain.models.User

@Composable
fun UserCard(
    user: User,
    onClick: () -> Unit
) {
    Card(
        elevation = 1.dp,
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        backgroundColor = MaterialTheme.colors.primary,
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = rememberImagePainter("https://www.pixsy.com/wp-content/uploads/2021/04/ben-sweet-2LowviVHZ-E-unsplash-1.jpeg"),
                contentDescription = user.email,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .border(1.dp, MaterialTheme.colors.secondaryVariant, CircleShape)
            )

            Text(
                text = user.displayName,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UserCardPreview() {
    DemoComposeTheme(darkTheme = true) {
        UserCard(
            user = User(name = "User"),
            onClick = {}
        )
    }
}