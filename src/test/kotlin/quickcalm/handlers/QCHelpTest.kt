package quickcalm.handlers

import com.amazon.speech.json.SpeechletRequestEnvelope
import com.amazon.speech.speechlet.IntentRequest
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import quickcalm.assertSpeech

class QCHelpTest {

    private val EXPECTED_TEXT = "This skill provides quick little activities that can be used when you just need a short break to refocus.  While these activities should be safe for most situations, you are ultimately responsible for ensuring the provided activity is safe for you in your current environment.  What would you like to do next?"

    @Test
    fun testHappyPath() {
        val fixture = QCHelp() as QCHandler
        val actual = fixture.generate(SpeechletRequestEnvelope.builder<IntentRequest>().build())

        assertThat(actual.shouldEndSession, `is`(false))
        assertSpeech(actual.outputSpeech, EXPECTED_TEXT)
        assertSpeech(actual.reprompt.outputSpeech, EXPECTED_TEXT)
    }

}