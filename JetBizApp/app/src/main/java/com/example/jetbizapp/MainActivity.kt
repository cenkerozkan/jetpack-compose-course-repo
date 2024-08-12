package com.example.jetbizapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetbizapp.R
import com.example.jetbizapp.ui.theme.JetBizAppTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetBizAppTheme { Scaffold(modifier = Modifier.fillMaxSize()) { CreateBizCard() } }
        }
    }
}

@Composable
fun CreateBizCard() {
    /*
        The Course I am following is using
        material 2, but I use material 3. There
        are some differences between those two,
        so the current usages are like in this code.
    */

    // This is a state variable.
    val buttonClickedState = remember {
        mutableStateOf(false)
    }
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        // The parameter dp is a unit of Android
        // for screen measurements.
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 40.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateImageProfile()
                HorizontalDivider(
                    // It was called in Divider in material 2
                    // but now it is called in HorizontalDivider.
                    // In the course, instructor uses
                    // startIndent as a parameter but I think
                    // it is deprecated, using modifier with
                    // width solves the problem for reducing
                    // the width of the HorizontalDivider.
                    // modifier = Modifier.width(130.dp)
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.LightGray,
                    thickness = 1.dp,
                )
                CreateInfo()
                Button(onClick = { buttonClickedState.value = !buttonClickedState.value }) {
                    Text(text = "Portfolio", style = MaterialTheme.typography.titleMedium)
                }
                if (buttonClickedState.value) {
                    Log.d("Value: ", "${buttonClickedState.value}")
                    Content()
                }
            }
        }
    }
}

// We use Preview to observe during development.
//@Preview(showBackground = true)
@Composable
fun Content() {
    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(5.dp)) {
        Surface(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp, color = Color.LightGray)
        ) {
            Portfolio(data = listOf("Project 1", "Project 2", "Project 3"))
        }
    }
}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn {
        // NOTE: Repeat this lambda structure later !
        items(data){ item ->
            Text(text = item)
        }
    }
}

@Composable
private fun CreateInfo() {
    Column(
        modifier = Modifier.padding(5.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // In typography, instructor uses material 2
        // but there are no instances like subtitle1
        // or h4, So I use the existing ones in material 3
        Text(
            text = "Miles P.",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Text(text = "Android Compose Programmer", modifier = Modifier.padding(3.dp))

        Text(
            text = "@themilesCompose",
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        tonalElevation = 4.dp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
        shadowElevation = 3.dp,
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "profile image",
            modifier = Modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Hello $name!", modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetBizAppTheme { CreateBizCard() }
}
