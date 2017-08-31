package com.ideaJsRequiresSizePlugin

import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import java.awt.FlowLayout
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

class Configuration(project: Project) : Configurable {
    private val settings = ServiceManager.getService(project, PluginSettings::class.java)
    private val textField = JTextField(settings.maxFileSize.toString())

    init {
        textField.columns = 10
    }

    override fun isModified(): Boolean {
        return try {
            settings.maxFileSize != textField.text.toLong()
        } catch (e: NumberFormatException) {
            false
        }
    }

    override fun getDisplayName(): String {
        return "JS requires size"
    }

    override fun apply() {
        try {
            settings.maxFileSize = textField.text.toLong()
        } catch (e: NumberFormatException) {
        }
    }

    override fun createComponent(): JComponent? {
        val pane = JPanel()

        pane.layout = FlowLayout(FlowLayout.LEFT)

        pane.add(JLabel("Maximum file size:"))
        pane.add(textField)
        pane.add(JLabel("bytes. Highlight bigger files red color."))

        return pane
    }
}