package br.com.andrejsmattos.music_box.utils;

import java.time.Duration;

public class FormatadorDuracao {

    public static String formatarDuracao(Long millis) {
        if (millis == null) {
            return null;
        }
        Duration duration = Duration.ofMillis(millis);
        long totalSeconds = duration.getSeconds();
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
