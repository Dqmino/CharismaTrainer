package codes.domino.screen;

import codes.domino.constant.Dialogues;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

import static codes.domino.screen.ScreenReader.postOCRRequest;

public class ScreenWriter {
    private static final Robot robot;
    private static final Rectangle screenRect = new Rectangle(789, 443, 402, 61);

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public static void scanScreen() throws IOException {
        clickText();

        BufferedImage screenCapture = robot.createScreenCapture(screenRect);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(screenCapture, "png", baos);
        baos.close();

        String text = postOCRRequest(Base64.getEncoder().encodeToString(
                baos.toByteArray())).getString("ParsedText")
                .replace("Try some small talk on someone nearby.", "").replace("'", "")
                .replace("\n", " ").replace("\r", "");

        for (Dialogues dialogue : Dialogues.values()) {
            if (dialogue.isSimilar(text)) {
                System.out.println("Found: " + dialogue.name());
                sendKeys(dialogue.content());
                break;
            }
        }
    }

    private static void sendKeys(String keys) {
        for (char c : keys.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                throw new RuntimeException(
                        "Key code not found for character '" + c + "'");
            }
            if (c == '?') {
                pressQuestionMark();
                continue;
            }
            pressKey(keyCode);
        }
        pressKey(KeyEvent.VK_ENTER);
    }

    private static void pressKey(int keyCode) {
        robot.keyPress(keyCode);
        robot.delay(10);
        robot.keyRelease(keyCode);
        robot.delay(10);
    }

    private static void pressQuestionMark() {
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_SLASH);
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_SLASH);
        robot.delay(10);
    }

    private static void clickText() {
        robot.mouseMove(955, 505);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(100);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(1000);
    }

}
