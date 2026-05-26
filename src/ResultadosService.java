import java.util.*;
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

    public void jogadorQueFezMaisGol(List<Gol> gols) {
        Map<String, Long> contagemGols =
        gols.stream()

                .map(g -> g.getJogador())
                .collect(Collectors.groupingBy(v -> v, Collectors.counting()));

        Long maxGols = contagemGols.values().stream()
                .max(Long::compare)
                .orElse(0L);

        List<Map.Entry<String, Long>> jogadoresEmpatados = contagemGols.entrySet().stream()
                .filter(entry -> entry.getValue().equals(maxGols))
                .collect(Collectors.toList());

        if (jogadoresEmpatados.isEmpty()){
            System.out.println("Nenhum jogador encontrado");
        } else {
            jogadoresEmpatados.forEach( jogador ->
                    System.out.println(jogador.getKey() + " (" + jogador.getValue() + " gols)")
            );
        }


    }

    public void jogadorFezMaisGolPorTipo(List<Gol> gols, TipoGol tipoGol) {
        Map<String, Long> contagemGols = gols.stream()
                .filter(g -> g.getTipoGol().equals(tipoGol))
                .map(g -> g.getJogador())
                .collect(Collectors.groupingBy(v -> v, Collectors.counting()));

        Long maxGols = contagemGols.values().stream()
                .max(Long::compare)
                .orElse(0L);

        List<Map.Entry<String, Long>> jogadoresEmpatados = contagemGols.entrySet().stream()
                .filter(entry -> entry.getValue().equals(maxGols))
                .collect(Collectors.toList());

        if (jogadoresEmpatados.isEmpty()){
            System.out.println("Nenhum jogador encontrado para tipo de gol" + tipoGol.name());
        } else {
            jogadoresEmpatados.forEach( jogador ->
                    System.out.println(jogador.getKey() + " (" + jogador.getValue() + " gols " + tipoGol.name() + ") " )
            );
        }
    }

    public void jogadorComMaisCartoesPorTipo(List<Cartao> cartoes, TipoCartao tipoCartao) {

        Map<String, Integer> quantidadeCartoes = new HashMap<>();

        for (Cartao cartao : cartoes) {

            if (cartao.getTipoCartao().equals(tipoCartao)) {

                String jogador = cartao.getJogador();

                quantidadeCartoes.put(
                        jogador,
                        quantidadeCartoes.getOrDefault(jogador, 0) + 1
                );
            }
        }

        if (quantidadeCartoes.isEmpty()) {
            System.out.println("Nenhum cartão " + tipoCartao.name() + " encontrado.");
            return;
        }
        String jogadorMaisCartoes = "";
        int maiorQuantidade = 0;

        for (Map.Entry<String, Integer> entry : quantidadeCartoes.entrySet()) {

            if (entry.getValue() > maiorQuantidade) {

                jogadorMaisCartoes = entry.getKey();
                maiorQuantidade = entry.getValue();
            }
        }

        System.out.println(
                jogadorMaisCartoes + " recebeu " +
                        maiorQuantidade + " cartões."
        );
    }
}
