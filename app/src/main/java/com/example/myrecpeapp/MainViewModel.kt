package com.example.myrecpeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {
    private val _categoriesState = mutableStateOf(ReceipeState())

    val categoriesState:State<ReceipeState> =_categoriesState

    init {
        fetchCategories()
    }
 private fun fetchCategories(){
     viewModelScope.launch {
         try {
            val response= receipeService.getCategories()
             _categoriesState.value=_categoriesState.value.copy(
                 loading = false,
                 list=response.categories,
                 error = null)

         }catch (e:Exception ){
             _categoriesState.value=_categoriesState.value.copy(
                 loading = false,
                 list= emptyList(),
                 error = "qualcosa è andato storto ${e.message}"
             )
         }finally {

         }

     }
 }
    data class ReceipeState(val loading:Boolean=true,
                            val list: List<Category> = emptyList(),
                            val error: String? = null
    )
}