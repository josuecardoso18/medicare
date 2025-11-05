package br.senac.rj.backend.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author reinaldo.jose
 * Faz tratamento da senha.
 */
public class PasswordUtil {

    // Gera hash da senha usando SHA-256
    public static String gerarHash(String senha) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(senha.getBytes(StandardCharsets.UTF_8));

            // Converte bytes para hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar hash da senha", e);
        }
    }

    // Verifica se a senha informada bate com o hash armazenado
    public static boolean verificarSenha(String senha, String hashArmazenado) {
        String hashTentativa = gerarHash(senha);
        return hashTentativa.equals(hashArmazenado);
    }
}
