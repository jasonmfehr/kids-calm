package quickcalm.handlers

import com.amazon.speech.json.SpeechletRequestEnvelope
import com.amazon.speech.slu.Intent
import com.amazon.speech.speechlet.IntentRequest
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import quickcalm.assertSpeech
import quickcalm.buildRequestEnvelope

class QCHelpTest {

    private val EXPECTED_TEXT = "This skill provides quick little activities that can be used when you just need a short break to refocus.  While these activities should be safe for most situations, you are ultimately responsible for ensuring the provided activity is safe for you in your current environment.  What would you like to do next?"

    @Test
    fun testGenerateHappyPath() {
        val actual = fixture().generate(buildRequestEnvelope())

        assertThat(actual.shouldEndSession, `is`(false))
        assertSpeech(actual.outputSpeech, EXPECTED_TEXT)
        assertSpeech(actual.reprompt.outputSpeech, EXPECTED_TEXT)
    }

    @Test
    fun testHandlesHelp() {
        assertThat(fixture().handlesIntent("AMAZON.HelpIntent", buildRequestEnvelope()), `is`(true))
    }

    private fun fixture(): QCHandler {
        return QCHelp()
    }

}