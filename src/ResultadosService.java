import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ResultadosService {

    public void timeComMaisVitoriasNoAno(List<Partida> partidas, int ano) {

        Optional<Map.Entry<String, Long>> resultado = partidas.stream()
                .filter(p -> p.getAno() == ano)
                .flatMap(p -> p.getVencedor().stream())
                .collect(Collectors.groupingBy(v -> v, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue());

        if (resultado.isEmpty()){
            System.out.println("Nenhum vencedor encontrado para o ano informado");
        } else {
            System.out.println("O time com mais vitórias em " + ano
                    + " foi o " + resultado.get().getKey()
                    + " com " + resultado.get().getValue() + " vitórias");
        }


    }
}
