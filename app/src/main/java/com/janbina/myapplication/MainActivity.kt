package com.janbina.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import com.airbnb.mvrx.*
import com.janbina.myapplication.ui.MyApplicationTheme

data class State(val text: String = "") : MavericksState

class ViewModel(initialState: State) : MavericksViewModel<State>(initialState) {
    fun updateText(new: String) = setState { copy(text = new) }
}

@InternalMavericksApi
class MainActivity : AppCompatActivity() {

//    private val viewModel: ViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val (text, setText) = remember { mutableStateOf("") }
//            val state by viewModel.stateFlow.collectAsState(initial = State())

            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val (viewModel, state) = mavericksViewModelAndState<ViewModel, State>()
                    Column {
                        TextField("Standard text field", text, setText)
                        TextField("Mavericks backed text field", state.text) { viewModel.updateText(it) }
                    }
                }
            }
        }
    }
}

@Composable
fun TextField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        label = { Text(text = label) },
        value = value,
        onValueChange = onValueChange
    )
}