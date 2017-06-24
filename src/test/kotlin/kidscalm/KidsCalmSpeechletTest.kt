package kidscalm

import com.amazon.speech.json.SpeechletRequestEnvelope
import com.amazon.speech.speechlet.IntentRequest
import com.amazon.speech.speechlet.Session
import com.amazon.speech.speechlet.SessionStartedRequest
import com.amazon.speech.speechlet.User
import com.amazon.speech.ui.PlainTextOutputSpeech
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.assertThat
import org.junit.Test

class KidsCalmSpeechletTest {

    private val fixture = KidsCalmSpeechlet()
    private val EXPECTED_ATTRIBUTE_KEY = "responsesSent"


    @Test
    fun testOnLaunchHappyPath() {
        val actual = fixture.onLaunch(null)
        val actualOutputSpeech = actual.outputSpeech as PlainTextOutputSpeech
        val actualRepromptSpeech = actual.reprompt.outputSpeech as PlainTextOutputSpeech

        assertThat(actual.shouldEndSession, `is`(false))
        assertThat(actualOutputSpeech.text, `is`("Welcome to Kids Calm."))
        assertThat(actualRepromptSpeech.text, `is`("Welcome to Kids Calm."))
    }

    @Test
    fun testOnIntentHappyPath() {
        val session = Session.builder().withSessionId("foo").build()
        val envelope = SpeechletRequestEnvelope.builder<IntentRequest>().withSession(session).build()
        val sessionStarted = SpeechletRequestEnvelope.builder<SessionStartedRequest>().withSession(session).build()
        val maxResponses = fixture.responses.size

        fixture.onSessionStarted(sessionStarted)
        assertThat(session.attributes.containsKey(EXPECTED_ATTRIBUTE_KEY), `is`(true))
        assertThat(session.attributes.get(EXPECTED_ATTRIBUTE_KEY), instanceOf(MutableList::class.java))

        for(i in 0..maxResponses*3) {
            fixture.onIntent(envelope)
            val responsesList = session.getAttribute(EXPECTED_ATTRIBUTE_KEY) as MutableList<Int>

            if(i % maxResponses == 0){
                assertThat(responsesList.size, `is`(1))
            }else{
                assertThat(responsesList.distinct().size, `is`(responsesList.size))
            }
        }
    }

}
