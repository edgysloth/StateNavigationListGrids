package com.example.statenavigationlistgrids

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.statenavigationlistgrids.ui.theme.StateNavigationListGridsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            StateNavigationListGridsTheme {
               // CounterScreen()
                //AppNavigation()
                LandG()
                //gridTrial()
            }
        }
    }

}
@Composable
fun CounterScreen(){
    var count by remember {
        mutableStateOf(0)
    }
    var text by remember{
        mutableStateOf(" ")
    }
    Column(
        modifier= Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text("The count value is $count")
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick={count++}){
            Text(text="Increment")
        }
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text(text = "Enter Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "screen1") {
        composable("screen1") { Screen1(navController) }
        composable("screen2") { Screen2(navController) }
        composable("screen3") { Screen3(navController) }
        composable("screen4/{data}", arguments = listOf(navArgument("data") { type = NavType.StringType })) { navBackStackEntry: NavBackStackEntry ->
            val data = navBackStackEntry.arguments?.getString("data") ?: ""
            Screen4(navController, data)
        }
    }
}
@Composable
fun Screen1(navController: NavController){
    Column(modifier = Modifier
        .background(Color.Black)
        .fillMaxSize()){
        Text(text="This is Screen 1", fontSize = 30.sp,color= Color.White)
        Button(onClick = {navController.navigate("Screen2")}){
            Text(text="Go to screen2")
        }
    }
}

@Composable
fun Screen2(navController: NavController){
    Column(modifier = Modifier
        .background(Color.Black)
        .fillMaxSize()){
        Text(text="This is Screen 2", fontSize = 30.sp,color= Color.White)
        Button(onClick = {navController.navigate("Screen3")}){
            Text(text="Go to screen3")
        }
    }
}

@Composable
fun Screen3(navController: NavController){
    var text by remember {
        mutableStateOf("")
    }
    Column{
        Text(text="This is Screen 3", fontSize = 30.sp, color = Color.Red)
        OutlinedTextField(value = text, onValueChange = {
            text=it
        })
        Button(onClick = {navController.navigate("Screen4/$text")}){
            Text(text="Go to screen4")
        }
    }
}

@Composable
fun Screen4(navController: NavController,data:String){
    Column(modifier = Modifier
        .background(Color.Black)
        .fillMaxSize()){
        Text(text="This is Screen 4 and data is $data", fontSize = 30.sp,color= Color.White)
        Button(onClick = {navController.navigate("Screen1")}){
            Text(text="Go to screen1")
        }
    }
}

@Composable
fun LandG(){
    var itemsList=List(50){"items : $it"}
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "List")
        LazyColumn(
            modifier = Modifier
                .height(400.dp)
                .width(100.dp)
                .padding(5.dp)
                .border(2.dp, Color.Black)
        ) {
            items(itemsList) { item ->
                BasicText(text = item, modifier = Modifier.padding(2.dp))
            }
        }
    }
}

@Composable
fun gridTrial(){
    var itemsList=List(100){"items : $it"}
    LazyVerticalGrid(
        columns = GridCells.Adaptive(100.dp),
        modifier = Modifier
            .height(500.dp)
            .padding(2.dp)
            .border(2.dp, Color.Black)
    ) {
        items(itemsList.size) { index ->
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .border(1.dp, Color.Gray)
                    .padding(8.dp)
            ) {
                BasicText(text = itemsList[index])
            }
        }
    }

}


