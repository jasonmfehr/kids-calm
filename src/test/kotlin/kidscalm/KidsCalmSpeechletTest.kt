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
        val actualOutputSpeech = actual.outputSpeech
        val actualRepromptSpeech = actual.reprompt

        if(actualOutputSpeech is PlainTextOutputSpeech){
            assertThat(actualOutputSpeech.text, `is`("Welcome to Kids Calm."))
        }else{
            fail("expected type of PlainTextOutputSpeech but got ${actualOutputSpeech.javaClass.canonicalName}")
        }

        if(actualRepromptSpeech is Reprompt){
            val actualRepromptOutputSpeech = actualRepromptSpeech.outputSpeech
            if(actualRepromptOutputSpeech is PlainTextOutputSpeech) {
                assertThat(actualRepromptOutputSpeech.text, `is`("Welcome to Kids Calm."))
            }else{
                fail("expected type of PlainTextOutputSpeech but got ${actualOutputSpeech.javaClass.canonicalName}")
            }
        }else {
            fail("expected type of Reprompt but got ${actualRepromptSpeech.javaClass.canonicalName}")
        }
    }


}
