package com.example.myrecpeapp

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeScreen(modifier: Modifier =Modifier,
                 viewstate :MainViewModel.ReceipeState,
                 navigateToDetail:(Category)->Unit){
  //val receipeViewModel:MainViewModel= viewModel()

  Box(modifier = Modifier.fillMaxSize()){
      when {
          viewstate.loading->{
              CircularProgressIndicator(     progress = 1.0F,
                  strokeWidth = 10.dp,
                  modifier = Modifier.size(100.dp, 100.dp))
            //Toast(LocalContext, "Sto caricando", Toast.LENGTH_LONG).show()

          }
          viewstate.error!=null->{
              Text(text = "Non è possibile caricare i dati")
          }
          else->{
              CategoryScreen(viewstate.list,navigateToDetail)
          }
      }
  }}
    @Composable
    fun CategoryItem(category: Category,navigateToDetail:(Category)->Unit) {

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp).clickable {  navigateToDetail(category)},
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = rememberAsyncImagePainter(category.strCategoryThumb ),
                contentDescription ="Immagine del piatto",
                modifier=Modifier.fillMaxSize().aspectRatio(1f))


            Text(text = category.strCategory,
                color = Color.Black,
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier=Modifier.padding(top = 8.dp)   )



        }
    }

@Composable
fun CategoryScreen(categories:List<Category>,navigateToDetail:(Category)->Unit){
      LazyVerticalGrid(GridCells.Fixed(2),modifier= Modifier.fillMaxSize()){
            items(categories){
                category-> CategoryItem(category=category,navigateToDetail)

            }

      }
}



