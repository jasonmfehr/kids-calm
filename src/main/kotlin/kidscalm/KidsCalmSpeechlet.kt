package kidscalm

import com.amazon.speech.json.SpeechletRequestEnvelope
import com.amazon.speech.speechlet.*
import com.amazon.speech.ui.PlainTextOutputSpeech
import com.amazon.speech.ui.Reprompt
import org.slf4j.LoggerFactory

class KidsCalmSpeechlet : SpeechletV2 {
    private val LOGGER = LoggerFactory.getLogger("kidscalm.KidsCalmSpeechlet")

    override fun onSessionStarted(requestEnvelope: SpeechletRequestEnvelope<SessionStartedRequest>?) {
        LOGGER.info("onSessionStarted USER_ID=\"${requestEnvelope?.session?.user?.userId}\" SESSION_ID=\"${requestEnvelope?.session?.sessionId}\"")
    }

    override fun onSessionEnded(requestEnvelope: SpeechletRequestEnvelope<SessionEndedRequest>?) {
        LOGGER.info("onSessionEnded USER_ID=\"${requestEnvelope?.session?.user?.userId}\" SESSION_ID=\"${requestEnvelope?.session?.sessionId}\"")
    }

    override fun onIntent(requestEnvelope: SpeechletRequestEnvelope<IntentRequest>?): SpeechletResponse {
        LOGGER.info("onIntent INTENT_NAME=\"${requestEnvelope?.request?.intent?.name}\" USER_ID=\"${requestEnvelope?.session?.user?.userId}\" SESSION_ID=\"${requestEnvelope?.session?.sessionId}\"")

        val speech = PlainTextOutputSpeech()
        val reprompt = Reprompt()

        speech.text = "Intent not yet implemented."
        reprompt.outputSpeech = speech

        return SpeechletResponse.newAskResponse(speech, reprompt)
    }

    override fun onLaunch(requestEnvelope: SpeechletRequestEnvelope<LaunchRequest>?): SpeechletResponse {
        LOGGER.info("onLaunch USER_ID=\"${requestEnvelope?.session?.user?.userId}\" SESSION_ID=\"${requestEnvelope?.session?.sessionId}\"")

        val speech = PlainTextOutputSpeech()
        val reprompt = Reprompt()

        speech.text = "Welcome to Kids Calm."
        reprompt.outputSpeech = speech

        return SpeechletResponse.newAskResponse(speech, reprompt)
    }
}