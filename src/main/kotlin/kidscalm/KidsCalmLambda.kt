package kidscalm

import com.amazon.speech.speechlet.Speechlet
import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler

class KidsCalmLambda : SpeechletRequestStreamHandler {

    constructor() : super(KidsCalmSpeechlet(), setOf("amzn1.ask.skill.73c3be5b-911d-41ea-9c62-65854bbdc2b1"))

}