package com.ideaJsRequiresSizePlugin

import java.awt.*
import javax.swing.Icon
import java.awt.Graphics2D

// TODO: Gradient
class SizeIconNew(private val settings: PluginSettings, private val size: Long) : Icon {
    override fun paintIcon(c: Component?, g: Graphics?, x: Int, y: Int) {
        val c2 = Color.decode("#008020")
        val c1 = Color.decode("#e00000")

        if (g == null) return

        (g as Graphics2D).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)

        val gradientPercent = if (size >= settings.maxFileSize) {
            1.0
        } else {
            size.toDouble() / settings.maxFileSize.toDouble()
        }

        g.color = Color(
                Math.round(c1.red * gradientPercent + c2.red * (1 - gradientPercent)).toInt(),
                Math.round(c1.green * gradientPercent + c2.green * (1 - gradientPercent)).toInt(),
                Math.round(c1.blue * gradientPercent + c2.blue * (1 - gradientPercent)).toInt()
        )

        g.fillOval(x, y, iconWidth, iconHeight)
    }

    override fun getIconWidth(): Int = 12
    override fun getIconHeight(): Int = 12
}
