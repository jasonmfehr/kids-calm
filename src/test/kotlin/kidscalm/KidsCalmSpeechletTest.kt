package kidscalm

import com.amazon.speech.speechlet.SpeechletV2
import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler
import com.amazon.speech.ui.OutputSpeech
import com.amazon.speech.ui.PlainTextOutputSpeech
import com.amazon.speech.ui.Reprompt
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

class KidsCalmSpeechletTest {

    var fixture = KidsCalmSpeechlet()

    @Test
    fun testOnLaunchHappyPath() {
        val actual = fixture.onLaunch(null)
        val actualOutputSpeech = actual.outputSpeech as PlainTextOutputSpeech
        val actualRepromptSpeech = actual.reprompt.outputSpeech as PlainTextOutputSpeech

        assertThat(actual.shouldEndSession, `is`(false))
        assertThat(actualOutputSpeech.text, `is`("Welcome to Kids Calm."))
        assertThat(actualRepromptSpeech.text, `is`("Welcome to Kids Calm."))
    }


}
