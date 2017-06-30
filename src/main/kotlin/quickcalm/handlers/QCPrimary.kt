package quickcalm.handlers

import com.amazon.speech.json.SpeechletRequestEnvelope
import com.amazon.speech.speechlet.IntentRequest
import com.amazon.speech.speechlet.SpeechletResponse
import com.amazon.speech.ui.PlainTextOutputSpeech
import java.util.*

class QCPrimary: QCHandler {

    private val RESPONSES_SENT_KEY = "responsesSent"

    internal val responses = listOf(
            "Do ten jumping jacks",
            "Do yoga child's pose",
            "Do yoga downward dog",
            "Take 3 slow deep breaths",
            "Hugs and kisses",
            "Do a short backrub"
    )

    override fun handlesIntent(intentName: String, req: SpeechletRequestEnvelope<IntentRequest>): Boolean {
        return "CalmSuggestions".equals(intentName)
    }

    override fun generate(req: SpeechletRequestEnvelope<IntentRequest>): SpeechletResponse {
        val speech = PlainTextOutputSpeech()
        val responsesSent: Any?

        if(!(req?.session?.attributes?.containsKey(RESPONSES_SENT_KEY) ?: false)){
            req?.session?.setAttribute(RESPONSES_SENT_KEY, mutableListOf<Int>())
        }

        responsesSent = req?.session?.getAttribute(RESPONSES_SENT_KEY) ?: mutableListOf<Int>()

        speech.text = this.pickResponse(responsesSent as MutableList<Int>)

        return SpeechletResponse.newTellResponse(speech)
    }

    private fun pickResponse(providedResponses: MutableList<Int>): String {
        if(providedResponses.size == responses.size){
            providedResponses.clear()
        }

        var respIndex: Int
        do {
            respIndex = Random().nextInt(responses.size)
        } while(providedResponses.contains(respIndex))

        providedResponses.add(respIndex)

        return responses[respIndex]
    }

}