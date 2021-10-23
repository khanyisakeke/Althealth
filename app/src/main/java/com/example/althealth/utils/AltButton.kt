package com.example.althealth.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatButton
import com.example.althealth.R

class AltButton(context: Context, attributeSet: AttributeSet) :AppCompatButton(context, attributeSet){

    init {
        applyFont()

        }

    private fun applyFont() {

        val typeface: Typeface = Typeface.createFromAsset(context.assets,"Literata-Regular.ttf")
        setTypeface(typeface)

    }

}