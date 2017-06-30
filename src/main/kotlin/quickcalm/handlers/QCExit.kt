package quickcalm.handlers

import com.amazon.speech.json.SpeechletRequestEnvelope
import com.amazon.speech.speechlet.IntentRequest
import com.amazon.speech.speechlet.SpeechletResponse
import com.amazon.speech.ui.PlainTextOutputSpeech

class QCExit: QCHandler {

    override fun generate(req: SpeechletRequestEnvelope<IntentRequest>): SpeechletResponse {
        val speech = PlainTextOutputSpeech()

        speech.text = "goodbye"

        return SpeechletResponse.newTellResponse(speech)
    }

}