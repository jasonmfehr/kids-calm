package quickcalm.handlers

import com.amazon.speech.json.SpeechletRequestEnvelope
import com.amazon.speech.speechlet.IntentRequest
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat

import org.junit.Test
import quickcalm.assertSpeech

class QCExitTest {

    @Test
    fun testHappyPath() {
        val fixture = QCExit() as QCHandler
        val actual = fixture.generate(SpeechletRequestEnvelope.builder<IntentRequest>().build())

        assertThat(actual.shouldEndSession, `is`(true))
        assertSpeech(actual.outputSpeech, "goodbye")
    }

}