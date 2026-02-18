package com.example.cristina_salazar_retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.material3.Surface

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cristina_salazar_retrofit.presentation.viewmodel.MainViewModel
import com.example.cristina_salazar_retrofit.ui.screens.ProductosScreen
import com.example.cristina_salazar_retrofit.ui.theme.RetrofitProductsApiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetrofitProductsApiTheme {
                Surface() {
                    val vm: MainViewModel = viewModel()
                    ProductosScreen(vm)
                }
            }
        }
    }
}
