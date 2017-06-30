package quickcalm.handlers

import com.amazon.speech.json.SpeechletRequestEnvelope
import com.amazon.speech.speechlet.IntentRequest
import com.amazon.speech.speechlet.SpeechletResponse
import com.amazon.speech.ui.PlainTextOutputSpeech

class QCExit: QCHandler {

    override fun handlesIntent(intentName: String, req: SpeechletRequestEnvelope<IntentRequest>): Boolean {
        return "AMAZON.StopIntent".equals(intentName) || "AMAZON.CancelIntent".equals(intentName)
    }

    override fun generate(req: SpeechletRequestEnvelope<IntentRequest>): SpeechletResponse {
        val speech = PlainTextOutputSpeech()

        speech.text = "goodbye"

        return SpeechletResponse.newTellResponse(speech)
    }

}