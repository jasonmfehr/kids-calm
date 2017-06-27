package kidscalm

import com.amazon.speech.speechlet.Speechlet
import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler

class KidsCalmLambda : SpeechletRequestStreamHandler {

    constructor() : super(KidsCalmSpeechlet(), setOf("amzn1.ask.skill.44f9a5a7-4d87-4f0e-8a5d-bcd301b14e54"))

}