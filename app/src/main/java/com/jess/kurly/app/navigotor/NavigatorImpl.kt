package com.jess.kurly.app.navigotor

import android.content.Context
import android.content.Intent
import com.jess.kurly.navigator.Direction
import com.jess.kurly.navigator.Navigator

class NavigatorImpl : Navigator {

    override fun getIntent(
        context: Context,
        direction: Direction,
    ): Intent {
        when (direction) {

            else -> Unit
        }

        return Intent()
    }
}
