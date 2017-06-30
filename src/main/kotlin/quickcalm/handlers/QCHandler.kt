package quickcalm.handlers

import com.amazon.speech.json.SpeechletRequestEnvelope
import com.amazon.speech.speechlet.IntentRequest
import com.amazon.speech.speechlet.SpeechletResponse

interface QCHandler {
    /**
     * Called by the speechlet to determine if the handler implementation should execute the IntentRequest that
     * has come into the skill.
     *
     * @param intentName name of the intent, this will be the same value as provided in req.request.intent.name,
     *                   but since most handlers will likely use just the intent name when determining if they
     *                   will handle the request, this parameter is provided for convenience
     * @param req SpeechletRequestEnvelope that was passed into the speechlet
     *
     * @return true if the implementation handles the request or false if it does not
     */
    fun handlesIntent(intentName: String, req: SpeechletRequestEnvelope<IntentRequest>): Boolean

    /**
     * Actual method used to execute on the request from the main speechlet.
     *
     * @param req SpeechletRequestEnvelope that was passed into the speechlet
     *
     * @return SpeechletResponse that will be passed directly back as the return of the SpeechletV2.onIntent() function
     */
    fun generate(req: SpeechletRequestEnvelope<IntentRequest>): SpeechletResponse
}