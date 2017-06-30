package quickcalm.handlers

import com.amazon.speech.json.SpeechletRequestEnvelope
import com.amazon.speech.speechlet.IntentRequest
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat

import org.junit.Test
import quickcalm.assertSpeech
import quickcalm.buildRequestEnvelope

class QCExitTest {

    @Test
    fun testGenerateHappyPath() {
        val actual = fixture().generate(SpeechletRequestEnvelope.builder<IntentRequest>().build())

        assertThat(actual.shouldEndSession, `is`(true))
        assertSpeech(actual.outputSpeech, "goodbye")
    }

    @Test
    fun testHandlesStop() {
        assertThat(fixture().handlesIntent("AMAZON.StopIntent", buildRequestEnvelope()), `is`(true))
    }

    @Test
    fun testHandlesCancel() {
        assertThat(fixture().handlesIntent("AMAZON.CancelIntent", buildRequestEnvelope()), `is`(true))
    }

    private fun fixture(): QCHandler {
        return QCExit()
    }

}