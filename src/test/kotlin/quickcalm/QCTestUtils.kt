package quickcalm

import com.amazon.speech.ui.OutputSpeech
import com.amazon.speech.ui.PlainTextOutputSpeech
import org.hamcrest.CoreMatchers
import org.junit.Assert

fun assertSpeech(actualSpeech: OutputSpeech, expectedText: String) {
    Assert.assertThat(actualSpeech, CoreMatchers.instanceOf(PlainTextOutputSpeech::class.java))
    Assert.assertThat((actualSpeech as PlainTextOutputSpeech).text, CoreMatchers.`is`(expectedText))
}
