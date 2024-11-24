package br.com.andrejsmattos.music_box.entities;

public enum GeneroMusical {
    POP("soul", "Soul"),
    ROCK("rock", "Rock"),
    JAZZ("jazz", "Jazz"),
    CLASSICO("classical", "Clássico"),
    HIP_HOP("hip-hop", "Hip-Hop"),
    RAP("rap", "Rap"),
    REGGAE("reggae", "Reggea"),
    BLUES("blues", "Blues"),
    COUNTRY("country", "Country"),
    ELETRONICA("electronic", "Eletrônica"),
    HOUSE("House", "House"),
    FUNK("funk", "Funk"),
    SAMBA("samba", "Samba"),
    MPB("mpb", "MPB"),
    BOSSA_NOVA("Bossa Nova", "Bossa Nova"),
    PAGODE("pagode", "Pagode"),
    FORRO("forro", "Forró"),
    SERTANEJO("sertanejo", "Sertanejo"),
    K_POP("k_pop", "K-Pop"),
    GOSPEL("gospel", "Gospel"),
    R_B("r_b", "R_b"),
    METAL("metal", "Metal"),
    PUNK("punk", "Punk"),
    SOUL("soul", "Soul"),
    INDIE("indie", "Indie"),
    FOLK("folk", "Folk"),
    TRAP("trap", "Trap"),
    DISCO("disco", "Disco"),
    OPERA("opera", "Opera"),
    TECHNO("techno", "Techno"),
    DUBSTEP("dubstep", "Dubstep"),
    LATIN("latin", "Latina"),
    AXE("axe", "Axé"),
    REGGAETON("reggaeton", "Reggaeton");

    private final String generoLastFm;
    private final String generoPortugues;


    GeneroMusical(String generoLastFm, String generoPortugues) {
        this.generoLastFm = generoLastFm;
        this.generoPortugues = generoPortugues;
    }

    public static GeneroMusical fromString(String text) {
        for (GeneroMusical genero : GeneroMusical.values()) {
            if (genero.generoLastFm.equalsIgnoreCase(text)) {
                return genero;
            }
        }
//        throw new IllegalArgumentException("Nenhum gênero musical foi encontrado: " + text);
        return null;
    }

    public static GeneroMusical fromPortugues(String text) {
        for (GeneroMusical genero: GeneroMusical.values()) {
            if (genero.generoPortugues.equalsIgnoreCase(text)) {
                return genero;
            }
        }
//        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
        return null;
    }
}