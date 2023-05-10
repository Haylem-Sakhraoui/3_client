package services;

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.util.Locale;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.speech.synthesis.Voice;


public class ProfileSpeechService {

    public static void speak(String text) {
        try {
            // Create a new SynthesizerModeDesc object with the desired settings
            SynthesizerModeDesc desc = new SynthesizerModeDesc(null, "general", Locale.US, null, null);

            // Get the synthesizer instance and allocate it
            Synthesizer synthesizer = Central.createSynthesizer(desc);
            synthesizer.allocate();

            // Get the voice and set the synthesizer's voice
            Voice voice = new Voice(null, Voice.GENDER_FEMALE, Voice.AGE_YOUNGER_ADULT, null);
            synthesizer.getSynthesizerProperties().setVoice(voice);

            // Speak the text
            synthesizer.speakPlainText(text, null);
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);

            // Deallocate the synthesizer
            synthesizer.deallocate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}














//public class ProfileSpeechService {
//    private static Synthesizer synthesizer;
//
//    public static void init() throws Exception {
//        if (synthesizer == null) {
//            SynthesizerModeDesc desc = new SynthesizerModeDesc(null, "general", Locale.US, null, null);
//            synthesizer = Central.createSynthesizer(desc);
//            synthesizer.allocate();
//            synthesizer.resume();
//        }
//    }
//
//    public static void say(String text, StringProperty speechProperty) throws Exception {
//        synthesizer.speakPlainText(text, null);
//        synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
//        Platform.runLater(() -> {
//            speechProperty.set(text);
//        });
//    }
//
//    public static void close() throws Exception {
//        synthesizer.deallocate();
//    }
//}