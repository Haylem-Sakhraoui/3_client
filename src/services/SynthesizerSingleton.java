/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.Locale;
import javax.speech.EngineException;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import com.sun.speech.freetts.jsapi.FreeTTSEngineCentral;
import com.sun.speech.freetts.util.Utilities;


import javax.speech.AudioException;

import javax.speech.Central;
import javax.speech.synthesis.*;

public class SynthesizerSingleton {

    private static Synthesizer synthesizer;

    private SynthesizerSingleton() throws AudioException {
        try {
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
            SynthesizerModeDesc desc = new SynthesizerModeDesc(null, "general", Locale.US, null, null);
            synthesizer = Central.createSynthesizer(desc);
            synthesizer.allocate();
            synthesizer.resume();
        } catch (EngineException e) {
            e.printStackTrace();
        }
    }

    public static Synthesizer getInstance() throws AudioException {
        if (synthesizer == null) {
            new SynthesizerSingleton();
        }
        return synthesizer;
    }
}











//import java.util.Locale;
//
//import javax.speech.AudioException;
//import javax.speech.Central;
//import javax.speech.EngineException;
//import javax.speech.EngineStateError;
//import javax.speech.synthesis.Synthesizer;
//import javax.speech.synthesis.SynthesizerModeDesc;
///**
// *
// * @author HAYLEM SAKHRAOUI
// */
//public class SynthesizerSingleton {
//    
//    private static Synthesizer synthesizer;
//    
//    private SynthesizerSingleton() {}
//    
//    public static Synthesizer getSynthesizer() {
//        if (synthesizer == null) {
//            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
//            try {
//                Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
//                SynthesizerModeDesc desc = new SynthesizerModeDesc(Locale.US);
//                synthesizer = Central.createSynthesizer(desc);
//                synthesizer.allocate();
//                synthesizer.resume();
//            } catch (IllegalArgumentException | EngineException | AudioException | EngineStateError e) {
//                e.printStackTrace();
//            }
//        }
//        return synthesizer;
//    }
//    
//}
