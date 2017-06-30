package kidscalm

import com.amazon.speech.json.SpeechletRequestEnvelope
import com.amazon.speech.speechlet.*
import org.slf4j.LoggerFactory
import quickcalm.REPROMPT_TEXT
import quickcalm.buildOutputSpeech
import quickcalm.buildReprompt
import quickcalm.handlers.QCExit
import quickcalm.handlers.QCHandler
import quickcalm.handlers.QCHelp
import quickcalm.handlers.QCPrimary
import quickcalm.buildOutputSpeech

class KidsCalmSpeechlet : SpeechletV2 {

    private val LOGGER = LoggerFactory.getLogger("kidscalm.KidsCalmSpeechlet")
    private val HANDLER_LIST = listOf<QCHandler>(QCPrimary(), QCExit(), QCHelp())

    override fun onSessionStarted(requestEnvelope: SpeechletRequestEnvelope<SessionStartedRequest>?) {
        LOGGER.info("onSessionStarted USER_ID=\"${requestEnvelope?.session?.user?.userId}\" SESSION_ID=\"${requestEnvelope?.session?.sessionId}\"")
        //requestEnvelope?.session?.attributes?.put(RESPONSES_SENT_KEY, mutableListOf<Int>())
    }

    override fun onSessionEnded(requestEnvelope: SpeechletRequestEnvelope<SessionEndedRequest>?) {
        LOGGER.info("onSessionEnded USER_ID=\"${requestEnvelope?.session?.user?.userId}\" SESSION_ID=\"${requestEnvelope?.session?.sessionId}\"")
    }

    override fun onIntent(requestEnvelope: SpeechletRequestEnvelope<IntentRequest>?): SpeechletResponse {
        val intentName = requestEnvelope?.request?.intent?.name
        LOGGER.info("onIntent INTENT_NAME=\"${intentName}\" USER_ID=\"${requestEnvelope?.session?.user?.userId}\" SESSION_ID=\"${requestEnvelope?.session?.sessionId}\"")

        if(intentName != null && requestEnvelope != null){
            for (handler in HANDLER_LIST) {
                if (handler.handlesIntent(intentName, requestEnvelope)) {
                    return handler.generate(requestEnvelope)
                }
            }
        }

        //this response will be sent if no handler was found that handles the request
        //or if either requestEnvelope.request.intent.name of requestEnvelope is null
        return SpeechletResponse.newTellResponse(buildOutputSpeech("error occurred, goodbye"))
    }

    override fun onLaunch(requestEnvelope: SpeechletRequestEnvelope<LaunchRequest>?): SpeechletResponse {
        LOGGER.info("onLaunch USER_ID=\"${requestEnvelope?.session?.user?.userId}\" SESSION_ID=\"${requestEnvelope?.session?.sessionId}\"")

        return SpeechletResponse.newAskResponse(buildOutputSpeech("Welcome to Quick Calm beta where you can get a quick activity when you need a short break.  ${REPROMPT_TEXT}"), buildReprompt())
    }

}