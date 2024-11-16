// Para fins informativos, esse programa foi feito em:
// Java version: JavaSE 22
// IDE: Eclipse (4.32.0)
// OS: Windows 10
package jogoDaVelhaTerminal;
import java.util.Scanner;
public class JogoDaVelha {
   private static final int TAMANHO = 3;
   private static final char VAZIO = '-';
   private static final char JOGADOR_X = 'X';
   private static final char JOGADOR_O = 'O';
   public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       char[][] tabuleiro = new char[TAMANHO][TAMANHO];
       boolean jogoAtivo = true;
       // criação da matriz que representa o tabuleiro
       inicializarTabuleiro(tabuleiro);
       System.out.println("Bem-vindo ao Jogo da Velha!");
       char jogadorAtual = JOGADOR_X;
       while (jogoAtivo) {
       	//mostra o tabuleiro atual na tela
           exibirTabuleiro(tabuleiro);
           System.out.println("Jogador " + jogadorAtual + ", é sua vez!");
           int linha, coluna;
           while (true) {
           	// captura a entrada do usuário para a linha
               System.out.print("Digite a linha (1-3): ");
               linha = scanner.nextInt() - 1;
              
           	// captura a entrada do usuário para a coluna
               System.out.print("Digite a coluna (1-3): ");
               coluna = scanner.nextInt() - 1;
              
               // verificação se a jogada foi valida ou não
               if (linha >= 0 && linha < TAMANHO && coluna >= 0 && coluna < TAMANHO) {
                   if (tabuleiro[linha][coluna] == VAZIO) {
                       tabuleiro[linha][coluna] = jogadorAtual;
                       break;
                   } else {
                       System.out.println("Essa posição já está ocupada! Tente novamente.");
                   }
               } else {
                   System.out.println("Entrada inválida! Tente novamente.");
               }
           }
           // Verifica as condições de vitória ou empate, parte do tratamento de excessões
           if (verificarVencedor(tabuleiro, jogadorAtual)) {
               exibirTabuleiro(tabuleiro);
               System.out.println("Parabéns, jogador " + jogadorAtual + "! Você venceu!");
               jogoAtivo = false;
           } else if (verificarEmpate(tabuleiro)) {
               exibirTabuleiro(tabuleiro);
               System.out.println("Empate! O tabuleiro está cheio.");
               jogoAtivo = false;
           } else {
               // Caso não haja empate e nem vitória vai trocar o jogador
               jogadorAtual = (jogadorAtual == JOGADOR_X) ? JOGADOR_O : JOGADOR_X;
           }
       }
       System.out.print("Deseja jogar novamente? (s/n): ");
       if (scanner.next().equalsIgnoreCase("s")) {
           main(args); // Reinicia o jogo
       } else {
           System.out.println("Obrigado por jogar!");
           scanner.close();
       }
   }
  
   //Cria o tabuleiro, e coloca - nos lugares vazios
   private static void inicializarTabuleiro(char[][] tabuleiro) {
       for (int i = 0; i < TAMANHO; i++) {
           for (int j = 0; j < TAMANHO; j++) {
               tabuleiro[i][j] = VAZIO;
           }
       }
   }
  
   //função para mostrar o tabuleiro atual na tela
   private static void exibirTabuleiro(char[][] tabuleiro) {
       System.out.println("Tabuleiro atual:");
       for (int i = 0; i < TAMANHO; i++) {
           for (int j = 0; j < TAMANHO; j++) {
               System.out.print(tabuleiro[i][j] + " ");
           }
           System.out.println();
       }
   }
  
   // Verifica linhas, colunas e diagonais
   private static boolean verificarVencedor(char[][] tabuleiro, char jogador) {
      
       for (int i = 0; i < TAMANHO; i++) {
           if ((tabuleiro[i][0] == jogador && tabuleiro[i][1] == jogador && tabuleiro[i][2] == jogador) || // Linha
               (tabuleiro[0][i] == jogador && tabuleiro[1][i] == jogador && tabuleiro[2][i] == jogador)) { // Coluna
               return true;
           }
       }
       // Verifica diagonais
       if ((tabuleiro[0][0] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][2] == jogador) ||
           (tabuleiro[0][2] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][0] == jogador)) {
           return true;
       }
       return false;
   }
   //verifica se houve empate
   private static boolean verificarEmpate(char[][] tabuleiro) {
       for (int i = 0; i < TAMANHO; i++) {
           for (int j = 0; j < TAMANHO; j++) {
               if (tabuleiro[i][j] == VAZIO) {
                   return false; // Ainda há células disponíveis
               }
           }
       }
       return true;
   }
}
