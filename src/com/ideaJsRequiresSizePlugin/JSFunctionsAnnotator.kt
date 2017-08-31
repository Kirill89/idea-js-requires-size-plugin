package com.ideaJsRequiresSizePlugin

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.javascript.psi.JSFunction
import com.intellij.openapi.editor.markup.GutterIconRenderer
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import javax.swing.Icon

class JSFunctionsAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        // Example how to get settings:
        // val settings = ServiceManager.getService(element.project, PluginSettings::class.java)

        if (element is JSFunction) {
            val range = TextRange(element.textRange.startOffset, element.textRange.endOffset)
            val annotation = holder.createInfoAnnotation(range, null)

            annotation.gutterIconRenderer = object : GutterIconRenderer() {
                override fun equals(other: Any?) = false
                override fun hashCode() = 0
                override fun getIcon(): Icon = SizeIcon(sizeToString(2000), SizeIconSeverity.LOW)
            }
        }
    }
}
