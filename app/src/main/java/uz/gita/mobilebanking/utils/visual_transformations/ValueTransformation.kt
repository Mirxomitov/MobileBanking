import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText

object ValueTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= 20) text.text.substring(0..19) else text.text
        val formatted = buildString {
            for (i in trimmed.indices) {
                append(trimmed[i])
                if (i % 8 == 0 && i != 0) append(' ')
                else if (i % 8 == 1 || i % 8 == 5) append(' ')
            }
        }

        val offsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                var additionalOffset = 0
                for (i in 0 until offset) {
                    if (i % 8 == 0 && i != 0) additionalOffset++
                    else if (i % 8 == 1 || i % 8 == 5) additionalOffset++
                }
                return offset + additionalOffset
            }

            override fun transformedToOriginal(offset: Int): Int {
                var additionalOffset = 0
                for (i in 0 until offset) {
                    if (i % 9 == 2 || i % 9 == 6) additionalOffset++
                    else if (i % 9 == 1 && i != 1) additionalOffset++
                }
                return offset - additionalOffset
            }
        }

        return TransformedText(AnnotatedString(formatted.trimEnd()), offsetTranslator)
    }
}
