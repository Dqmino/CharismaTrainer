package codes.domino;

import codes.domino.screen.ScreenWriter;
import codes.domino.ui.TrainerUI;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.io.IOException;

public class CharismaTrainerMain implements NativeKeyListener {

    public static void main(String[] args) {
        TrainerUI.startUI();
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(new CharismaTrainerMain());
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        if (e.getKeyCode() != NativeKeyEvent.VC_F5) {
            return;
        }
        try {
            ScreenWriter.scanScreen();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}