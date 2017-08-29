package com.ideaJsRequiresSizePlugin

import java.awt.*
import javax.swing.Icon

enum class SizeIconSeverity {
    LOWEST,
    LOW,
    MIDDLE,
    HIGH,
    HIGHEST
}

fun sizeToString(size: Long): String {
    return if (size < 1024) {
        size.toString() + " B"
    } else if (size >= 1024 && size < 1024 * 1024) {
        Math.round(size.toDouble() / 1024).toString() + " kB"
    } else {
        (Math.round(size.toDouble() / 1024 / 1024 * 100).toDouble() / 100).toString() + " MB"
    }
}

// TODO: colors for severity
class SizeIcon(private val sizeValue: String, private val severity: SizeIconSeverity) : Icon {
    private val font = Font(Font.MONOSPACED, Font.PLAIN, 9)
    private val fontMetrics = Canvas().getFontMetrics(font)
    private val MIN_WIDTH = 40
    private val TEXT_OFFSET = 2

    override fun paintIcon(c: Component?, g: Graphics?, x: Int, y: Int) {
        if (g == null) return

        g.color = Color.GREEN
        g.fillRect(x, y, iconWidth, iconHeight)

        g.font = font
        g.color = Color.BLACK
        g.drawString(
                sizeValue,
                x + (iconWidth - fontMetrics.stringWidth(sizeValue)) / 2,
                y + (iconHeight - fontMetrics.height) / 2 + fontMetrics.ascent
        )
    }

    override fun getIconWidth(): Int = Math.max(fontMetrics.stringWidth(sizeValue) + TEXT_OFFSET, MIN_WIDTH)
    override fun getIconHeight(): Int = 16
}
