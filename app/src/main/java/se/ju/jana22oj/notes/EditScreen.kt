package se.ju.jana22oj.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun Edit(navController: NavController, list: MutableList<Note> , note: Note)
{
    var title by rememberSaveable {
        mutableStateOf(note.title)
    }
    var desc by rememberSaveable {
        mutableStateOf(note.desc)
    }
    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement =  Arrangement.spacedBy(8.dp)
    ){
        OutlinedTextField(value = title,
            onValueChange = { title = it },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(value = desc,
            onValueChange = { desc = it },
            modifier = Modifier.fillMaxWidth())
        Button(onClick = {

            title = ""
            desc = ""
            navController.navigateUp()

        }) {
            Text("Save Note")
        }
    }

}


