package emailClient.service;

import java.util.Base64;

public class TextEncoderDecoder {

    private static final Base64.Encoder encoder = Base64.getEncoder();

    private static final Base64.Decoder decoder = Base64.getDecoder();

    public String encode(String text) {
        try {
            return encoder.encodeToString(text.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decode(String text) {
        try {
            return new String(decoder.decode(text.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
