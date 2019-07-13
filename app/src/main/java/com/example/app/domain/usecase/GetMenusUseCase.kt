package com.example.app.domain.usecase

import com.example.app.data.MenusRepository

class GetMenusUseCase(private val menusRepository: MenusRepository = MenusRepository()) {
    fun getAllMenus(pageNo: Int) = menusRepository.getAllMenus(pageNo)
}