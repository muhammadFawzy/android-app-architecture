package com.example.app.domain.usecase

import com.example.app.data.MenusRepository

class GetMenusUseCase {
    fun getAllMenus(pageNo: Int, menusRepository: MenusRepository = MenusRepository()) = menusRepository.getAllMenus(pageNo)
}