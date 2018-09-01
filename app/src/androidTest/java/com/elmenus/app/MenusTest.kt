package com.elmenus.app


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.elmenus.app.db.MenuDB
import com.elmenus.app.db.MenuDao
import com.elmenus.app.ui.menulist.MenuViewModel
import com.elmenus.app.ui.menulist.MenusListActivity
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MenusTest {

    @get:Rule
    val menuListActivity = ActivityTestRule<MenusListActivity>(MenusListActivity::class.java)

    private lateinit var viewModel: MenuViewModel
    private lateinit var dao: MenuDao

    @Before
    fun setup() {

        viewModel = menuListActivity.activity.viewModel
        dao = MenuDB.getInMemoryDatabase(InstrumentationRegistry.getTargetContext()).daoAccess()

    }

    /**
     * test cash data online after getting it from Api
     * condition connected network
     */

    @Test
    fun cacheDataOffline() {
        if (isNetworkConnected()) {
            viewModel.getMenus(1)
            Thread.sleep(2000)
            var menus = dao.getAllMenus("1 - %")

            Assert.assertTrue(menus.isNotEmpty())
        }

    }

    /**
     * test selecting data from room db using pageNo.
     */
    @Test
    fun getMenuWithPageNo() {
        if (isNetworkConnected()) {
            viewModel.getMenus(1)
            Thread.sleep(2000)
            var menus = dao.getAllMenus("1 - %")

            Assert.assertTrue(menus[0]?.name.startsWith("1 - "))
        }

    }

    /**
     * UI test when there is no internet and no cached data
     * Network error happen
     */
    @Test
    fun getDataWithoutInternet() {
        if (!isNetworkConnected()) {
            dao.deleteAllData()
            viewModel.getMenus(1)
            onView(withText(InstrumentationRegistry.getTargetContext().resources.getString(R.string.network_error)))
                    .check(matches(isDisplayed()))

        }


    }

    private fun isNetworkConnected(): Boolean {
        val cm = InstrumentationRegistry.getContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }


}
