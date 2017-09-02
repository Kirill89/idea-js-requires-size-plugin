package com.ideaJsRequiresSizePlugin

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.javascript.psi.JSArgumentList
import com.intellij.lang.javascript.psi.JSCallExpression
import com.intellij.lang.javascript.psi.JSFunction
import com.intellij.lang.javascript.psi.JSLiteralExpression
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.editor.markup.GutterIconRenderer
import com.intellij.openapi.util.TextRange
import com.intellij.openapi.vfs.VfsUtilCore
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import javax.swing.Icon

class JSFunctionsAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        val settings = ServiceManager.getService(element.project, PluginSettings::class.java)

        if (element is JSFunction) {
            val range = TextRange(element.textRange.startOffset, element.textRange.endOffset)
            val annotation = holder.createInfoAnnotation(range, null)

            annotation.gutterIconRenderer = object : GutterIconRenderer() {
                override fun equals(other: Any?) = false
                override fun hashCode() = 0
                override fun getIcon(): Icon = SizeIconNew(settings, 20000)
                override fun getTooltipText() = "default: 200 kb\ngzipped: 100 kb"
            }
        }

        if (element is JSCallExpression && element.text.startsWith("require(")) {
            val args = PsiTreeUtil.getChildOfType(element, JSArgumentList::class.java)
            if (args != null) {
                val modulePath = PsiTreeUtil.getChildOfType(args, JSLiteralExpression::class.java)

                println(modulePath)

                if (modulePath != null) {
                    val uri = modulePath.text.substring(1, modulePath.text.length - 1)

                    println(uri)

                    val file = VfsUtilCore.findRelativeFile(uri, element.containingFile.virtualFile)

                    println(file?.path)
                }
            }
        }
    }
}
