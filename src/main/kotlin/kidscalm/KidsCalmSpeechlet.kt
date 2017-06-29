package kidscalm

import com.amazon.speech.json.SpeechletRequestEnvelope
import com.amazon.speech.speechlet.*
import com.amazon.speech.ui.OutputSpeech
import com.amazon.speech.ui.PlainTextOutputSpeech
import com.amazon.speech.ui.Reprompt
import org.slf4j.LoggerFactory
import java.util.*

class KidsCalmSpeechlet : SpeechletV2 {

    private val LOGGER = LoggerFactory.getLogger("kidscalm.KidsCalmSpeechlet")

    private val RESPONSES_SENT_KEY = "responsesSent"
    private val REPROMPT_TEXT = "you can say things like suggestion, give me an idea, or tell me a game"

    internal val responses = listOf(
            "Do ten jumping jacks",
            "Do yoga child's pose",
            "Do yoga downward dog",
            "Take 3 slow deep breaths",
            "Hugs and kisses",
            "Do a short backrub"
    )

    override fun onSessionStarted(requestEnvelope: SpeechletRequestEnvelope<SessionStartedRequest>?) {
        LOGGER.info("onSessionStarted USER_ID=\"${requestEnvelope?.session?.user?.userId}\" SESSION_ID=\"${requestEnvelope?.session?.sessionId}\"")
        requestEnvelope?.session?.attributes?.put(RESPONSES_SENT_KEY, mutableListOf<Int>())
    }

    override fun onSessionEnded(requestEnvelope: SpeechletRequestEnvelope<SessionEndedRequest>?) {
        LOGGER.info("onSessionEnded USER_ID=\"${requestEnvelope?.session?.user?.userId}\" SESSION_ID=\"${requestEnvelope?.session?.sessionId}\"")
    }

    override fun onIntent(requestEnvelope: SpeechletRequestEnvelope<IntentRequest>?): SpeechletResponse {
        LOGGER.info("onIntent INTENT_NAME=\"${requestEnvelope?.request?.intent?.name}\" USER_ID=\"${requestEnvelope?.session?.user?.userId}\" SESSION_ID=\"${requestEnvelope?.session?.sessionId}\"")

        val speech = PlainTextOutputSpeech()
        val responsesSent = requestEnvelope?.session?.getAttribute(RESPONSES_SENT_KEY) ?: mutableListOf<Int>()

        speech.text = this.pickResponse(responsesSent as MutableList<Int>)

        return SpeechletResponse.newTellResponse(speech)
    }

    override fun onLaunch(requestEnvelope: SpeechletRequestEnvelope<LaunchRequest>?): SpeechletResponse {
        LOGGER.info("onLaunch USER_ID=\"${requestEnvelope?.session?.user?.userId}\" SESSION_ID=\"${requestEnvelope?.session?.sessionId}\"")

        return SpeechletResponse.newAskResponse(buildOuptutSpeech("Welcome to Quick Calm beta where you can get a quick activity when you need a short break.  ${REPROMPT_TEXT}"), buildReprompt())
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

    private fun buildReprompt(): Reprompt {
        val reprompt = Reprompt()

        reprompt.outputSpeech = buildOuptutSpeech(REPROMPT_TEXT)

        return reprompt
    }

    private fun buildOuptutSpeech(text: String): OutputSpeech {
        val speech = PlainTextOutputSpeech()
        speech.text = text

        return speech
    }

}