package com.jess.kurly.navigator

import android.content.Context
import android.content.Intent

interface Navigator {

    fun getIntent(
        context: Context,
        direction: Direction,
    ): Intent
}
