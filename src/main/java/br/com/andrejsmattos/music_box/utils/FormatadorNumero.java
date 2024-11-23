package br.com.andrejsmattos.music_box.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatadorNumero {

    public static String formatarNumero(Long numero) {
        if (numero == null) {
            return null;
        }
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));
        return numberFormat.format(numero);
    }
}

