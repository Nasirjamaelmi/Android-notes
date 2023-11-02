package se.ju.jana22oj.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Overview(navController: NavController, list: MutableList<Note>, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Notes" ) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screen.MainScreen.route) }) {
                Icon(Icons.Default.Add, contentDescription = "Add Note")
            }
        }
    ){ innerPadding ->
        LazyColumn(modifier = Modifier
            .padding(innerPadding)

        ){
            items(list) { note ->
                RowView(navController, note , list)
            }
        }
    }
}



@Composable
fun RowView(navController: NavController, note: Note , list: MutableList<Note>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(Screen.DetailScreen.route + "/" + note.id)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = note.isChecked.value,
            onCheckedChange = {
                note.isChecked.value = !note.isChecked.value

            }
        )

        Text(note.title)
        Spacer(modifier = Modifier.width(5.dp))
        if(note.isChecked.value){
          FloatingActionButton(onClick = {list.remove(note)})
          {
              Icon(Icons.Default.Delete, contentDescription = "Delete Note")
          }

        }
    }
}


