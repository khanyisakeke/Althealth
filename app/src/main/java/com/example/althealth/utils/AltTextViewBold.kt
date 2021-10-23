package com.example.althealth.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView

class AltTextViewBold(context: Context, attributeSet: AttributeSet) : AppCompatTextView(context, attributeSet) {

    init {

        //Call the function to apply the font to the components.
        applyFont()

    }

    private fun applyFont() {

        //This is used to get the font from the assets folder and apply it to the textView
        val typeface: Typeface = Typeface.createFromAsset(context.assets,"Literata-Bold.ttf")
        setTypeface(typeface)

    }

}