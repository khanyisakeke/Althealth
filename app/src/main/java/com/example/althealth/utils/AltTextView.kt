package com.example.althealth.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class AltTextView(context: Context, attributeSet: AttributeSet) : AppCompatTextView(context, attributeSet) {

    init {

        applyFont()

    }

    private fun applyFont() {

        val typeface: Typeface = Typeface.createFromAsset(context.assets,"Literata-Regular.ttf")
        setTypeface(typeface)

    }

}