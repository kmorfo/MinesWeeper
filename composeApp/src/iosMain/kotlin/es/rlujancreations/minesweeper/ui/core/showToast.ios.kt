package es.rlujancreations.minesweeper.ui.core

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import platform.UIKit.NSTextAlignmentCenter
import platform.UIKit.UIColor
import platform.UIKit.UILabel
import platform.UIKit.UIViewController
import platform.UIKit.UIWindow
import platform.UIKit.UIWindowLevelStatusBar

/**
 * Created by Raúl L.C. on 30/6/24.
 */

actual fun showToast(message: String) {
    val label = UILabel().apply {
        text = message
        textColor = UIColor.whiteColor
        backgroundColor = UIColor.blackColor.colorWithAlphaComponent(0.8)
        textAlignment = NSTextAlignmentCenter
        numberOfLines = 0
        layer.cornerRadius = 10.0
        clipsToBounds = true
        translatesAutoresizingMaskIntoConstraints = false
    }

    val window = UIWindow().apply {
        windowLevel = UIWindowLevelStatusBar + 1.0
        rootViewController = UIViewController().apply {
            view = label
        }
        makeKeyAndVisible()
    }

    MainScope().launch {
        println("Lanzado toast")
        delay(2000)
        println("ocultando toast")
        window.hidden = true
    }
}
