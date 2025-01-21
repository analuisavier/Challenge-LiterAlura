package br.com.alura.LiterAlura.controle;

import br.com.alura.LiterAlura.servico.EstatisticaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticaControlador {

    @Autowired
    private EstatisticaServico estatisticaServico;

    @GetMapping("/downloads")
    public ResponseEntity<Map<String, Object>> obterEstatisticasDownloads() {
        try {
            return (ResponseEntity<Map<String, Object>>) ResponseEntity.ok();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Erro ao obter estatísticas", "error", e.getMessage()));
        }
    }
}
