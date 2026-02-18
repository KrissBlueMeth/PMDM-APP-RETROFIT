package com.example.cristina_salazar_retrofit.ui.screens

import com.example.cristina_salazar_retrofit.data.model.ItemDto
import com.example.cristina_salazar_retrofit.presentation.viewmodel.MainViewModel
import com.example.cristina_salazar_retrofit.presentation.viewmodel.UiState

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductosScreen(vm: MainViewModel) {
    val state = vm.state

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Catálogo de Productos",
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1A237E), // Azul profundo
                    titleContentColor = Color.White
                )
            )
        },
        containerColor = Color(0xFFF5F5F5) // Fondo ligeramente gris para que las tarjetas resalten
    ) { paddingValues ->

        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            when (state) {
                is UiState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = Color(0xFF1A237E))
                    }
                }

                is UiState.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Hubo un problema al cargar los datos", color = Color.Gray)
                        Text("${state.msg}", style = MaterialTheme.typography.bodySmall)
                        Button(
                            onClick = { vm.loadProducts() },
                            modifier = Modifier.padding(top = 16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A237E))
                        ) {
                            Text("Reintentar")
                        }
                    }
                }

                is UiState.Success -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(12.dp), // Espaciado alrededor de la lista
                        verticalArrangement = Arrangement.spacedBy(12.dp) // Espacio entre tarjetas
                    ) {
                        items(state.data) { producto ->
                            ProductoItemSimple(producto)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProductoItemSimple(producto: ItemDto) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.size(90.dp)
            ) {
                AsyncImage(
                    model = producto.image,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = producto.name,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF333333)
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Surface(
                    color = Color(0xFFE8EAF6), // Azul muy clarito
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Text(
                        text = producto.category.uppercase(),
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        color = Color(0xFF3F51B5)
                    )
                }

                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Text(
                        text = "${producto.price}",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFA000) // Color Ámbar
                        )
                    )
                    Text(
                        text = "€",
                        modifier = Modifier.padding(start = 2.dp, bottom = 4.dp),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFFFFA000)
                    )
                }
            }
        }
    }
}