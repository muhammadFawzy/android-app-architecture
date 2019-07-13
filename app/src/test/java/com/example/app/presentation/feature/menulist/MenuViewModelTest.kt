package com.example.app.presentation.feature.menulist

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.app.data.MenusRepository
import com.example.app.data.source.remote.MenusRemoteDataSource
import com.example.app.domain.usecase.GetMenusUseCase
import com.example.app.presentation.feature.menulist.mocking.RetrofitStubbing
import org.junit.Rule
import org.junit.Test

class MenuViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun `getting menus given pageNumber then show loading`() {

        //arrange
        val menusRepository = MenusRepository(MenusRemoteDataSource(RetrofitStubbing(enqueue = false)))
        val useCase = GetMenusUseCase(menusRepository)
        val menuViewModel = MenuViewModel(useCase)

        //act
        menuViewModel.getMenus()

        //assert
        assert(menuViewModel.loading.value == true)
    }

    @Test
    fun `getting menus given pageNumber and Successful response then add items to adapter`() {
        //arrange
        val menusRepository = MenusRepository(MenusRemoteDataSource(RetrofitStubbing()))
        val useCase = GetMenusUseCase(menusRepository)
        val menuViewModel = MenuViewModel(useCase)

        //act
        menuViewModel.getMenus()

        //assert
        assert(menuViewModel.menus.value?.size == 1)
    }

    @Test
    fun `getting menus given pageNumber and UnSuccessful response then show error of text view`() {

        //arrange
        val menusRepository = MenusRepository(MenusRemoteDataSource(RetrofitStubbing(false)))
        val useCase = GetMenusUseCase(menusRepository)
        val menuViewModel = MenuViewModel(useCase)

        //act
        menuViewModel.getMenus()

        //assert
        assert(menuViewModel.networkError.value == View.VISIBLE)

    }


}