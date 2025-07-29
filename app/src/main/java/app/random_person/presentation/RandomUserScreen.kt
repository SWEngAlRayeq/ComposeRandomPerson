package app.random_person.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import app.random_person.presentation.viewmodel.RandomUserViewModel
import coil.compose.AsyncImage

@Composable
fun RandomUserScreen(viewModel: RandomUserViewModel = hiltViewModel()) {

    val user = viewModel.user
    val isLoading = viewModel.isLoading
    val error = viewModel.error

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFFe0c3fc), Color(0xFF8ec5fc))))
            .padding(24.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                "👨‍🎨 Random Person",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(Modifier.height(32.dp))

            when {
                isLoading -> CircularProgressIndicator(color = Color.White)
                error != null -> Text("❌ $error", color = Color.Red)
                user != null -> {
                    AsyncImage(
                        model = user.picture.large,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                            .border(4.dp, Color.White, CircleShape)
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        "${user.name.first} ${user.name.last}",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        "Age: ${user.dob.age}  |  ${user.nat}",
                        fontSize = 18.sp,
                        color = Color.White
                    )
                    Spacer(Modifier.height(24.dp))

                }
            }

            Button(
                onClick = { viewModel.fetchRandomUser() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EA)),
                shape = RoundedCornerShape(50)
            ) {
                Text(
                    "🔄 New Person",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }


        }
    }


}