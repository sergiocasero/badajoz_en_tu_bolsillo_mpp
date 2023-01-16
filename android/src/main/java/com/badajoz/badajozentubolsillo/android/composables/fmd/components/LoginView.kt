package com.badajoz.badajozentubolsillo.android.composables.fmd.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.badajoz.badajozentubolsillo.android.R
import com.badajoz.badajozentubolsillo.viewmodel.fmd.FmdEvent

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginView(onEvent: (FmdEvent) -> Unit) {
    // remember scroll state
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val username = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val keyboard = LocalSoftwareKeyboardController.current

        // Image(
        //     painter = painterResource(id = R.drawable.fmd_logo), contentDescription =
        //     stringResource(id = R.string.fmd_logo)
        // )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.fmd_login_1),
            modifier = Modifier.padding(start = 32.dp, end = 32.dp),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.fmd_login_2),
            modifier = Modifier.padding(start = 32.dp, end = 32.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.fmd_login_3),
            modifier = Modifier.padding(start = 32.dp, end = 32.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            label = { Text(stringResource(id = R.string.username)) },
            value = username.value,
            onValueChange = { username.value = it },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            label = { Text(stringResource(id = R.string.password)) },
            value = password.value,
            onValueChange = { password.value = it },
            visualTransformation = PasswordVisualTransformation(),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Go),
            keyboardActions = KeyboardActions(onGo = { keyboard?.hide() })
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { onEvent(FmdEvent.Login(username.value, password.value)) }) {
            Text(stringResource(id = R.string.login))
        }
    }
}

@Preview
@Composable
fun LoginViewPreview() {
    LoginView(onEvent = {})
}