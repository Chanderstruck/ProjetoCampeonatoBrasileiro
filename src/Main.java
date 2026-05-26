import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String fullCsv = "campeonato-brasileiro-full.csv";
        String golsCsv = "campeonato-brasileiro-gols.csv";
        String cartoesCsv = "campeonato-brasileiro-cartoes.csv";

        Repositorio repositorio = new Repositorio();
        ResultadosService resultadosService = new ResultadosService();

        Scanner entrada = new Scanner(System.in);

        try {

            List<Partida> partidas = repositorio.carregarPartidas(fullCsv);
            List<Gol> gols = repositorio.carregarGols(golsCsv);
            List<Cartao> cartoes = repositorio.carregarCartoes(cartoesCsv);

            //Exibir menu
            int opcao;

            do {

                System.out.println("\n===== MENU FUTEBOL =====");
                System.out.println("1 - O time que mais venceu jogos no ano 2008");
                System.out.println("2 - O Estado que teve menos jogos entre 2003 e 2022");
                System.out.println("3 - O jogador que mais fez gols");
                System.out.println("4 - O jogador que mais fez gols de pênaltis");
                System.out.println("5 - O jogador que mais fez gols contra");
                System.out.println("6 - O jogador que mais recebeu cartões amarelos");
                System.out.println("7 - O jogador que mais recebeu cartões vermelhos");
                System.out.println("8 - O placar da partida com mais gols");
                System.out.println("-1 - Sair");
                System.out.print("Escolha uma opção: ");

                opcao = Integer.parseInt(entrada.nextLine());

                switch (opcao) {

                    case 1:
                        System.out.println("\nTime que mais venceu jogos no ano 2008:");
                        resultadosService.timeComMaisVitoriasNoAno(partidas,2008);
                        break;

                    case 2:
                        System.out.println("\nEstado que teve menos jogos entre 2003 e 2022:");
                        resultadosService.estadoComMenosJogosPorPeriodo(partidas,2003,2022);
                        break;

                    case 3:
                        System.out.println("\nJogador que mais fez gols:");
                        resultadosService.jogadorQueFezMaisGol(gols);
                        break;

                    case 4:
                        System.out.println("\nJogador que mais fez gols de pênaltis:");
                        resultadosService.jogadorFezMaisGolPorTipo(gols, TipoGol.PENALTI);
                        break;

                    case 5:
                        System.out.println("\nJogador que mais fez gols contra:");
                        resultadosService.jogadorFezMaisGolPorTipo(gols, TipoGol.CONTRA);
                        break;

                    case 6:
                        System.out.println("\nJogador que mais recebeu cartões amarelos:");
                        resultadosService.jogadorComMaisCartoesPorTipo(cartoes, TipoCartao.AMARELO);
                        break;

                    case 7:
                        System.out.println("\nJogador que mais recebeu cartões vermelhos:");
                        resultadosService.jogadorComMaisCartoesPorTipo(cartoes, TipoCartao.VERMELHO);
                        break;

                    case 8:
                        System.out.println("\nPlacar da partida com mais gols:");
                        resultadosService.partidaComMaisGols(partidas);
                        break;

                    case -1:
                        System.out.println("\nPrograma encerrado.");
                        break;

                    default:
                        System.out.println("\nOpção inválida!");
                }

            } while (opcao != -1);

            entrada.close();


        } catch (IOException e) {
            System.err.println("Erro ao ler arquivos: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Usar apenas números.");
        } finally {
            entrada.close();
        }



    }

}
