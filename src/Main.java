import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String fullCsv = "campeonato-brasileiro-full.csv";
        String golsCsv = "campeonato-brasileiro-gols.csv";

        Repositorio repositorio = new Repositorio();
        ResultadosService resultadosService = new ResultadosService();

        try {

            List<Partida> partidas = repositorio.carregarPartidas(fullCsv);
            List<Gol> gols = repositorio.carregarGols(golsCsv);

            resultadosService.timeComMaisVitoriasNoAno(partidas,2008);


        } catch (IOException e) {
            System.err.println("Erro ao ler arquivos: " + e.getMessage());
            e.printStackTrace();
        }

    }

}
