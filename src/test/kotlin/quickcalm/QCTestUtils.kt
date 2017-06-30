package quickcalm

import com.amazon.speech.json.SpeechletRequestEnvelope
import com.amazon.speech.slu.Intent
import com.amazon.speech.speechlet.IntentRequest
import com.amazon.speech.ui.OutputSpeech
import com.amazon.speech.ui.PlainTextOutputSpeech
import org.hamcrest.CoreMatchers
import org.junit.Assert

fun assertSpeech(actualSpeech: OutputSpeech, expectedText: String) {
    Assert.assertThat(actualSpeech, CoreMatchers.instanceOf(PlainTextOutputSpeech::class.java))
    Assert.assertThat((actualSpeech as PlainTextOutputSpeech).text, CoreMatchers.`is`(expectedText))
}

fun buildRequestEnvelope(intentName: String): SpeechletRequestEnvelope<IntentRequest> {
    val intent = Intent.builder().withName(intentName).build()
    val intentRequest = IntentRequest.builder()
            .withIntent(intent)
            .withRequestId("reqid")
            .build()

    return SpeechletRequestEnvelope.builder<IntentRequest>().withRequest(intentRequest).build()
}

fun buildRequestEnvelope(): SpeechletRequestEnvelope<IntentRequest> {
    return buildRequestEnvelope("NotSpecified")
}
