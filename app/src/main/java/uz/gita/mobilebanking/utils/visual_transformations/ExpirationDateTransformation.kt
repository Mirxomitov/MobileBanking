package uz.gita.mobilebanking.utils.visual_transformations

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

object ExpirationDateTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return maskFilter(text)
    }

    private fun maskFilter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= 4) text.text.substring(0..3) else text.text
        var out = ""
        for (i in trimmed.indices) {
            out += trimmed[i]
            if (listOf(1).contains(i)) out += "/"
        }

        val numberOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int) =
                if (offset <= 1) offset
                else offset + 1


            override fun transformedToOriginal(offset: Int) =
                if (offset <= 1) offset
                else offset - 1
        }

        return TransformedText(AnnotatedString(out), numberOffsetTranslator)
    }
}

