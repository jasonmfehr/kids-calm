package quickcalm.handlers

import com.amazon.speech.json.SpeechletRequestEnvelope
import com.amazon.speech.speechlet.IntentRequest
import com.amazon.speech.speechlet.SpeechletResponse

interface QCHandler {
    fun generate(req: SpeechletRequestEnvelope<IntentRequest>): SpeechletResponse
}