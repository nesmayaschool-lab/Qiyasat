package com.nesmaya.qiyasat

import android.app.Activity
import android.os.Bundle
import android.graphics.Typeface
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import com.google.ar.core.ArCoreApk

class MainActivity : Activity() {

    private lateinit var statusText: TextView
    private lateinit var arButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buildFirstScreen()
        checkArCoreSupport()
    }

    private fun buildFirstScreen() {
        val content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            setPadding(28, 36, 28, 28)
        }

        val title = TextView(this).apply {
            text = "قياسات"
            textSize = 32f
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
        }

        val subtitle = TextView(this).apply {
            text = "أداة القياس الذكية"
            textSize = 18f
            gravity = Gravity.CENTER
            setPadding(0, 8, 0, 28)
        }

        statusText = TextView(this).apply {
            text = "جارٍ فحص توافق الجهاز..."
            textSize = 17f
            gravity = Gravity.CENTER
            setPadding(8, 20, 8, 20)
        }

        arButton = Button(this).apply {
            text = "القياس بالواقع المعزز"
            isEnabled = false
        }

        val fallbackButton = Button(this).apply {
            text = "أدوات القياس البديلة"
            setOnClickListener {
                statusText.text =
                    "هذه النسخة التأسيسية لا تنفذ القياس بعد. المرحلة التالية هي تشغيل الكاميرا وبناء أدوات القياس."
            }
        }

        content.addView(title, match())
        content.addView(subtitle, match())
        content.addView(statusText, match())
        content.addView(arButton, match())
        content.addView(fallbackButton, match())

        val scroll = ScrollView(this).apply { addView(content) }
        setContentView(scroll)
    }

    private fun checkArCoreSupport() {
        try {
            ArCoreApk.getInstance().checkAvailabilityAsync(this) { availability ->
                runOnUiThread {
                    val supported = availability.isSupported
                    if (supported) {
                        statusText.text =
                            "جهازك يدعم ARCore. سنتمكن من تفعيل وضع الواقع المعزز بعد إضافة شاشة الكاميرا ومحرك القياس."
                        arButton.isEnabled = true
                    } else {
                        statusText.text =
                            "لم يتم تأكيد دعم ARCore على هذا الجهاز. سيظل التطبيق قابلًا للتشغيل، وسنجهز وضع القياس البديل."
                        arButton.isEnabled = false
                    }
                }
            }
        } catch (e: Exception) {
            statusText.text =
                "تعذر فحص ARCore الآن. سيظل التطبيق قابلًا للتشغيل، ويمكن إعادة الفحص لاحقًا."
            arButton.isEnabled = false
        }
    }

    private fun match(): LinearLayout.LayoutParams =
        LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            bottomMargin = 14
        }
}
