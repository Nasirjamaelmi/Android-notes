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
import androidx.compose.ui.graphics.Color
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
    var ShowError by rememberSaveable {
        mutableStateOf(false)
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
            if(title.length in 3..50 && desc.length <= 120) {
                note.title = title
                note.desc = desc
                title = ""
                desc = ""
                navController.navigateUp()
            }
            else{
                ShowError = true
            }
        }) {
            Text("Save Note")
        }
        if(ShowError)
        {
            Text("Invalid: Change title or text", color = Color.Red)
        }
    }

}


