import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ResultadosService {

    public void timeComMaisVitoriasNoAno(List<Partida> partidas, int ano) {

        Map<String, Long> contagemVitorias = partidas.stream()
                .filter(p -> p.getAno() == ano)
                .flatMap(p -> p.getVencedor().stream())
                .collect(Collectors.groupingBy(v -> v, Collectors.counting()));

        Long maxVitorias = contagemVitorias.values().stream()
                .max(Long::compare)
                .orElse(0L);

        List<Map.Entry<String, Long>> timesEmpatados = contagemVitorias.entrySet().stream()
                .filter(entry -> entry.getValue().equals(maxVitorias))
                .collect(Collectors.toList());

        if (timesEmpatados.isEmpty()){
            System.out.println("Nenhum vencedor encontrado para o ano informado");
        } else {
            timesEmpatados.forEach( time ->
                    System.out.println(time.getKey() + " (" + time.getValue() + " vitórias)")
            );
        }
    }

    public void partidaComMaisGols (List<Partida> partidas) {

        Optional<Partida> resultado = partidas.stream()
                .max(Comparator.comparingLong(Partida::getTotalGols));

        resultado.ifPresentOrElse(
                p -> {
                    String placar = String.format("Partida ID %s: %s %d x %d %s (Total de %d gols)",
                            p.getIdPartida(),
                            p.getTimeMandante(),
                            p.getGolsMandante(),
                            p.getGolsVisitante(),
                            p.getTimeVisitante(),
                            p.getTotalGols()
                            );
                    System.out.println(placar);
                },
                () -> System.out.println("Nenhuma partida encontrada")
        );
    }
}
