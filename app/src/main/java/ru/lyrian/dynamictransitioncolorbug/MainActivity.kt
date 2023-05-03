package ru.lyrian.dynamictransitioncolorbug

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.setup.OdysseyConfiguration
import ru.alexgladkov.odyssey.compose.setup.StartScreen
import ru.alexgladkov.odyssey.compose.setup.setNavigationContent
import ru.alexgladkov.odyssey.core.configuration.DisplayType
import ru.lyrian.dynamictransitioncolorbug.ui.theme.DynamicTransitionColorBugTheme

class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val isDarkTheme by mainViewModel.isDarkTransitionBackground.collectAsState()

            DynamicTransitionColorBugTheme {
                val configuration = OdysseyConfiguration(
                    canvas = this,
                    startScreen = StartScreen.First,
                    backgroundColor = if (isDarkTheme) Color.Black else Color.White,
                    displayType = DisplayType.FullScreen
                )

                setNavigationContent(configuration) {
                    screen(NavigationTree.FirstScreen.name) {
                        val rootController = LocalRootController.current

                        FirstScreenContent(
                            onNavigateClick = { rootController.push(NavigationTree.SecondScreen.name) },
                            onColorChangeClick = mainViewModel::setDarkTransitionBackground
                        )
                    }

                    screen(NavigationTree.SecondScreen.name) {
                        SecondScreenContent()
                    }
                }
            }
        }
    }
}

enum class NavigationTree {
    FirstScreen, SecondScreen
}

@Composable
fun CenteredContent(
    content: @Composable () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
        ) {
            content()
        }
    }
}

@Composable
fun FirstScreenContent(
    onNavigateClick: () -> Unit,
    onColorChangeClick: () -> Unit
) {
    CenteredContent {
        Button(
            onClick = onNavigateClick
        ) {
            Text("Next Screen")
        }

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = onColorChangeClick
        ) {
            Text("Set Dark Transition Background")
        }
    }
}

@Composable
fun SecondScreenContent() {
    CenteredContent {
        Greeting("Android")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}