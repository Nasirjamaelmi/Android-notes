package se.ju.jana22oj.notes

import android.icu.text.CaseMap.Title
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime. *
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import se.ju.jana22oj.notes.ui.theme.NotesTheme


import java.util.UUID

data class Note(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val desc: String,
    var isChecked: MutableState<Boolean> = mutableStateOf(false)
)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val list = remember {
                mutableStateListOf<Note>()
            }
            val navController = rememberNavController()
            NotesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = Screen.MScreen.route) {
                        composable(route = Screen.MScreen.route) {
                            MainScreen(navController = navController, list = list)
                        }
                        composable(
                            route = Screen.DetailScreen.route + "/{id}",
                            arguments = listOf(
                                navArgument("id") {
                                    type = NavType.StringType
                                    nullable = false
                                }
                            )
                        ) {entry ->
                            val id = entry.arguments?.getString("id")
                            val note: Note? = list.find { it.id == id }
                            if(note != null)
                                DetailScreen(note = note)
                        }
                    }
                }
            }

            }
        }
    }




@Composable
fun MScreen(navController: NavController)
{
    var text by rememberSaveable {

        mutableStateOf("")
    }
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
    ) {
        TextField(value = text, onValueChange = {
            text = it
        },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier =  Modifier.height(8.dp))
        Button(onClick = {
                navController.navigate(Screen.DetailScreen.withArgs(text))
        },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "To DetailScreen")
        }

    }
}

@Composable
fun DetailScreen(note: Note) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Title: ${note.title}",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)

            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Description: ${note.desc}",
                style = TextStyle(fontSize = 16.sp)
            )
        }
    }
}

@Composable
fun MainScreen(navController: NavController, list: MutableList<Note>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        TextInputView(list = list)
        ListView(navController ,list = list)
    }
}
@Composable
fun TextInputView(list: MutableList<Note>)
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
        }) {
            Text("Add Note")
        }
    }

}



@Composable
fun ListView(navController: NavController, list: List<Note>) {
LazyColumn {
    items(list) { note ->
        RowView(navController, note)
    }
}
}



@Composable
fun RowView(navController: NavController, note: Note) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                println(note)
                println(note.id)
                println(note.title)
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
    }
}

//@Preview(showBackground = true)
//@Composable
//fun RowViewPreview() {
//     NotesTheme {
//        RowView(Note(title = "Hello", desc = ""))
//    }
//}