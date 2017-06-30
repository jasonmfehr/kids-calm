package quickcalm.handlers

import com.amazon.speech.json.SpeechletRequestEnvelope
import com.amazon.speech.speechlet.IntentRequest
import com.amazon.speech.speechlet.SpeechletResponse
import com.amazon.speech.ui.PlainTextOutputSpeech
import com.amazon.speech.ui.Reprompt

class QCHelp: QCHandler {

    override fun handlesIntent(intentName: String, req: SpeechletRequestEnvelope<IntentRequest>): Boolean {
        return "AMAZON.HelpIntent".equals(intentName)
    }

    override fun generate(req: SpeechletRequestEnvelope<IntentRequest>): SpeechletResponse {
        val speech = PlainTextOutputSpeech()
        val reprompt = Reprompt()
        val repromptSpeech = PlainTextOutputSpeech()

        speech.text = "This skill provides quick little activities that can be used when you just need a short break to refocus.  While these activities should be safe for most situations, you are ultimately responsible for ensuring the provided activity is safe for you in your current environment.  What would you like to do next?"

        repromptSpeech.text = speech.text
        reprompt.outputSpeech = repromptSpeech

        return SpeechletResponse.newAskResponse(speech, reprompt)
    }

}