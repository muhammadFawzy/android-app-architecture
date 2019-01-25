package com.example.app.presentation.feature.menulist

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.app.domain.usecase.GetMenusUseCase

class ViewModelFactory(private val getMenusUseCase: GetMenusUseCase = GetMenusUseCase()) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MenuViewModel::class.java)) {
            MenuViewModel(getMenusUseCase) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}