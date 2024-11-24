package br.com.andrejsmattos.music_box.utils;

public class DeletaLinksHtml {
    public static String removerLinksHtml(String texto) {
        return texto.replaceAll("<a href=\"[^\"]*\">.*?</a>", "");
    }

}


