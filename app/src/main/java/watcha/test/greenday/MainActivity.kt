package watcha.test.greenday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import watcha.test.greenday.core.designsystem.theme.GreendayTheme
import watcha.test.greenday.feature.home.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreendayTheme {
                HomeScreen()
            }
        }
    }
}