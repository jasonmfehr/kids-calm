package kidscalm

import com.amazon.speech.speechlet.Speechlet
import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler

val supportedApps = HashSet<String>()


class KidsCalmLambda : SpeechletRequestStreamHandler {

    constructor() : super(KidsCalmSpeechlet(), supportedApps)

}