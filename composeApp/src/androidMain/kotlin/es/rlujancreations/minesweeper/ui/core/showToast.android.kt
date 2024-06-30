package es.rlujancreations.minesweeper.ui.core

import android.content.Context
import android.widget.Toast
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Created by Raúl L.C. on 30/6/24.
 */

object ToastProvider : KoinComponent {
    private val context: Context by inject()

    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}

actual fun showToast(message: String) {
    ToastProvider.showToast(message)
}