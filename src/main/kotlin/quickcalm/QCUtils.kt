package quickcalm

import com.amazon.speech.ui.OutputSpeech
import com.amazon.speech.ui.PlainTextOutputSpeech
import com.amazon.speech.ui.Reprompt

val REPROMPT_TEXT = "you can say things like suggestion, give me an idea, or tell me a game"

fun buildReprompt(): Reprompt {
    val reprompt = Reprompt()

    reprompt.outputSpeech = buildOutputSpeech(REPROMPT_TEXT)

    return reprompt
}

fun buildOutputSpeech(text: String): OutputSpeech {
    val speech = PlainTextOutputSpeech()
    speech.text = text

    return speech
}
