package br.com.andrejsmattos.music_box.services;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
