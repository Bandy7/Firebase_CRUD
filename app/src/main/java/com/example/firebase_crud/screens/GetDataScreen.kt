package com.example.firebase_crud.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.firebase_crud.util.SharedViewModel
import com.example.firebase_crud.util.UserData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetDataScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    var userID: String by remember { mutableStateOf("") }
    var name: String by remember { mutableStateOf("") }
    var profession: String by remember { mutableStateOf("") }
    var age: String by remember { mutableStateOf("") }
    var ageInt: Int by remember { mutableIntStateOf(0) }

    val context = LocalContext.current

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.Start
        ){
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
            }
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.6f),
                    value = userID,
                    onValueChange = { userID = it },
                    label = {
                        Text(text = "UserID")
                    }
                )
                Button(
                    modifier = Modifier.width(100.dp),
                    onClick = {
                        sharedViewModel.retrieveData(
                            userID = userID,
                            context = context
                        ){ data ->
                            name = data.name
                            profession = data.profession
                            age = data.age.toString()
                            ageInt = age.toInt()
                        }
                    }
                ) {
                    Text(text = "Get Data")
                }
            }
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = { name = it },
                label = {
                    Text(text = "Name")
                }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = profession,
                onValueChange = { profession = it },
                label = {
                    Text(text = "Profession")
                }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = age,
                onValueChange = {
                    age = it
                    if (age.isNotEmpty()){
                        ageInt = age.toInt()
                    }
                },
                label = {
                    Text(text = "Age")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                onClick = {
                    val userData = UserData(
                        userID = userID,
                        name = name,
                        profession = profession,
                        age = ageInt
                    )
                    sharedViewModel.saveData(userData, context = context)
                }
            ) {
                Text(text = "Save")
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                onClick = {
                    sharedViewModel.deleteData(
                        userID = userID,
                        context = context,
                        navController = navController
                    )
                }
            ) {
                Text(text = "Delete")
            }
        }
    }
}






















