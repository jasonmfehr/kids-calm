package kidscalm

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import quickcalm.assertSpeech

class KidsCalmSpeechletTest {

    private val fixture = KidsCalmSpeechlet()
    private val EXPECTED_REPROMPT_TEXT = "you can say things like suggestion, give me an idea, or tell me a game"

    @Test
    fun testOnLaunchHappyPath() {
        val actual = fixture.onLaunch(null)

        assertThat(actual.shouldEndSession, `is`(false))
        assertSpeech(actual.outputSpeech, "Welcome to Quick Calm beta where you can get a quick activity when you need a short break.  ${EXPECTED_REPROMPT_TEXT}")
        assertSpeech(actual.reprompt.outputSpeech, EXPECTED_REPROMPT_TEXT)
    }

}
