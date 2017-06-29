package kidscalm

import com.amazon.speech.json.SpeechletRequestEnvelope
import com.amazon.speech.speechlet.IntentRequest
import com.amazon.speech.speechlet.Session
import com.amazon.speech.speechlet.SessionStartedRequest
import com.amazon.speech.speechlet.User
import com.amazon.speech.ui.OutputSpeech
import com.amazon.speech.ui.PlainTextOutputSpeech
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.assertThat
import org.junit.Test

class KidsCalmSpeechletTest {

    private val fixture = KidsCalmSpeechlet()
    private val EXPECTED_ATTRIBUTE_KEY = "responsesSent"
    private val EXPECTED_REPROMPT_TEXT = "you can say things like suggestion, give me an idea, or tell me a game"

    @Test
    fun testOnLaunchHappyPath() {
        val actual = fixture.onLaunch(null)

        assertThat(actual.shouldEndSession, `is`(false))
        assertSpeech(actual.outputSpeech, "Welcome to Quick Calm beta where you can get a quick activity when you need a short break.  ${EXPECTED_REPROMPT_TEXT}")
        assertSpeech(actual.reprompt.outputSpeech, EXPECTED_REPROMPT_TEXT)
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
            val actualResponse = fixture.onIntent(envelope)
            val responsesList = session.getAttribute(EXPECTED_ATTRIBUTE_KEY) as MutableList<Int>

            assertThat(actualResponse.shouldEndSession, `is`(true))
            assertThat(actualResponse.reprompt, nullValue())

            if(i % maxResponses == 0){
                assertThat(responsesList.size, `is`(1))
            }else{
                assertThat(responsesList.distinct().size, `is`(responsesList.size))
            }
        }
    }

    private fun assertSpeech(actualSpeech: OutputSpeech, expectedText: String) {
        assertThat(actualSpeech, instanceOf(PlainTextOutputSpeech::class.java))
        assertThat((actualSpeech as PlainTextOutputSpeech).text, `is`(expectedText))
    }

}
