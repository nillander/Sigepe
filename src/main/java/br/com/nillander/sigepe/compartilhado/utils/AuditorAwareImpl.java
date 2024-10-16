package br.com.nillander.sigepe.compartilhado.utils;

import org.springframework.data.domain.AuditorAware;
import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    @SuppressWarnings("null")
    public Optional<String> getCurrentAuditor() {
        // Aqui você pode implementar a lógica para pegar o usuário atual (como do
        // Spring Security ou da sessão)
        return Optional.of("Sistema"); // Por enquanto, vamos retornar "Sistema" como padrão
    }
}
