package quickcalm.handlers

import com.amazon.speech.json.SpeechletRequestEnvelope
import com.amazon.speech.speechlet.IntentRequest
import com.amazon.speech.speechlet.Session
import com.amazon.speech.speechlet.SessionStartedRequest
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test
import quickcalm.buildRequestEnvelope

class QCPrimaryTest {

    private val EXPECTED_ATTRIBUTE_KEY = "responsesSent"

    @Test
    fun testGenerateHappyPath() {
        val fixture = fixture()
        val session = Session.builder().withSessionId("foo").build()
        val envelope = SpeechletRequestEnvelope.builder<IntentRequest>().withSession(session).build()
        val maxResponses = fixture.responses.size

        for(i in 0..maxResponses*3) {
            val actualResponse = fixture.generate(envelope)
            val responsesList = session.getAttribute(EXPECTED_ATTRIBUTE_KEY) as MutableList<Int>

            Assert.assertThat(actualResponse.shouldEndSession, CoreMatchers.`is`(true))
            Assert.assertThat(actualResponse.reprompt, CoreMatchers.nullValue())

            if(i % maxResponses == 0){
                Assert.assertThat(responsesList.size, CoreMatchers.`is`(1))
            }else{
                Assert.assertThat(responsesList.distinct().size, CoreMatchers.`is`(responsesList.size))
            }
        }
    }

    @Test
    fun testHandlesHelp() {
        Assert.assertThat(fixture().handlesIntent("CalmSuggestions", buildRequestEnvelope()), CoreMatchers.`is`(true))
    }

    private fun fixture(): QCPrimary {
        return QCPrimary()
    }
}