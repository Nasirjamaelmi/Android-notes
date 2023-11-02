package se.ju.jana22oj.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController



@Composable
fun TextInputView(navController: NavController, list: MutableList<Note>)
{
    var title by rememberSaveable {
        mutableStateOf("")
    }
    var desc by rememberSaveable {
        mutableStateOf("")
    }
    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement =  Arrangement.spacedBy(8.dp)
    ){
        OutlinedTextField(value = title,
            onValueChange = { title = it },
            placeholder = { Text("Enter Title")},
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(value = desc,
            onValueChange = { desc = it },
            placeholder = {Text("Enter Desc")},
            modifier = Modifier.fillMaxWidth())
        Button(onClick = {
            list.add(Note(title = title, desc = desc))
            title = ""
            desc = ""
            navController.navigateUp()

        }) {
            Text("Add Note")
        }
    }

}


