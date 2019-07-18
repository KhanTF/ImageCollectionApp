package ru.rage.image.presentation.navigation

import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.Navigation

class NavControllerRouter {

    private var navController: NavController? = null

    fun perform(f: NavController.() -> Unit) {
        navController?.f()
    }

    class NavControllerHolder(
        private val navControllerRouter: NavControllerRouter,
        private val activity: Activity,
        private val navHostId: Int
    ) {

        private fun getController(): NavController? = Navigation.findNavController(activity, navHostId)

        fun getRequireController(): NavController = getController() ?: throw IllegalArgumentException("Controller not exist or activity not initiated yet")

        fun attach() {
            navControllerRouter.navController = getController()
        }

        fun detach() {
            navControllerRouter.navController = null
        }

    }

}