#include <stdio.h> // serve para lidar com entrada/saída (input/output) ex: printf, scanf


#define LINHAS 20
#define COLUNAS 80
#define NUMERO_TOTAL_DE_BARCOS 5

struct Barco {
    char id;
    int linha;
    int coluna;
};

void lerTabelaDeFicheiro(char tabela[LINHAS][COLUNAS], struct Barco barcos[NUMERO_TOTAL_DE_BARCOS], const char *nomeFicheiro) {

    int count = 0;

    FILE *ficheiro = fopen(nomeFicheiro, "r");

    if (ficheiro == NULL) {
        printf("Erro ao abrir o ficheiro %s\n", nomeFicheiro);
        return;
    }

    for (int i = 0; i < LINHAS; i++) {
        for (int j = 0; j < COLUNAS; j++) {

            char c = fgetc(ficheiro); // o fgetc serve para ler cada caractere um por um  ,a variavel c serve para armazenar temporariamente o caractere lido do ficheiro.

            if (c == '\n') {
                c = fgetc(ficheiro); // o fgetc(ficheiro) serve para lê um único caractere do ficheiro por vez.
            }
            tabela[i][j] = c; // armazena o valor de c em uma matriz
            if (c >= 'A' && c <= 'E' && count < NUMERO_TOTAL_DE_BARCOS) {  // percorre o ficheiro e armazena a posição de cada barco durante a leitura do ficheiro na estrutura barco
                barcos[count++] = (struct Barco){c, i, j};
            }
        }
    }
    fclose(ficheiro);
}

void mostrarFicheiro(char tabela[LINHAS][COLUNAS]) {
    for (int i = 0; i < LINHAS; i++) {
        for (int j = 0; j < COLUNAS; j++) {
            printf("%c", tabela[i][j]);
        }
        printf("\n");
    }
}

void consultarCoordenadas(struct Barco barcos[NUMERO_TOTAL_DE_BARCOS]) {
    char barco;
    printf("Escolha o barco que quer encontrar:\n");
    scanf(" %c", &barco);

    for (int i = 0; i < NUMERO_TOTAL_DE_BARCOS; i++) {

        if (barcos[i].id == barco) {
            printf("O barco %c encontra-se na linha %d e na coluna %d\n", barco, barcos[i].linha+1, barcos[i].coluna+1);
            return;
        }
    }
    printf("O barco %c nao foi encontrado.\n", barco);
}

void compararTabelas(struct Barco barcosAntes[NUMERO_TOTAL_DE_BARCOS], struct Barco barcosDepois[NUMERO_TOTAL_DE_BARCOS]) {
    char barcoEscolhido;

    printf("Escolha qual o barco que quer listar o movimento:\n");
    scanf(" %c", &barcoEscolhido);

    for (int i = 0; i < NUMERO_TOTAL_DE_BARCOS; i++) {

        if (barcosAntes[i].id == barcoEscolhido) {

            struct Barco antes = barcosAntes[i];
            struct Barco depois = barcosDepois[i];

            if (antes.linha == depois.linha && antes.coluna == depois.coluna) {
                printf("Movimento: O barco %c nao se moveu.\n", antes.id);
            } else {
                printf("Movimento: O barco moveu-se para "); // vai comparar as linha e colunas para ver o movimento
                if (depois.coluna < antes.coluna)
                    printf("a esquerda.\n");
                else if (depois.coluna > antes.coluna)
                    printf("a direita.\n");
                else if (depois.linha < antes.linha)
                    printf("cima.\n");
                else
                    printf("baixo.\n");
            }
            return;
        }
    }
    printf("Erro: Barco nao encontrado.\n");
}

void mainConsutarCoordenadas(struct Barco tabelaAntes[NUMERO_TOTAL_DE_BARCOS],struct Barco tabelaDepois[NUMERO_TOTAL_DE_BARCOS]) {
    char tabelaEscolhida = '0';
    printf("Qual a tabela que quer consultar? Tabela antes - 1 Tabela depois - 2\n");
    scanf(" %c", &tabelaEscolhida);
    switch (tabelaEscolhida) {
        case '1':
            consultarCoordenadas(tabelaAntes);
        break;
        case '2':
            consultarCoordenadas(tabelaDepois);
        break;
        default:
            printf("Opcao invalida!\n");
    }
}

void menu() {
    printf("\nMenu:\n");
    printf("1 - Imprimir tabela \"antes\"\n");
    printf("2 - Imprimir tabela \"depois\"\n");
    printf("3 - Consultar as coordenadas de um barco\n");
    printf("4 - Listar barcos que se moveram\n");
    printf("5 - Sair\n");
    printf("Escolha uma opcao: ");
}

void jogo(char escolha ,char tabelaAntes[LINHAS][COLUNAS], char tabelaDepois[LINHAS][COLUNAS], struct Barco barcosAntes[NUMERO_TOTAL_DE_BARCOS], struct Barco barcosDepois[NUMERO_TOTAL_DE_BARCOS]) {

    switch (escolha) {
        case '1':
            printf("\nTabela Antes:\n");
            mostrarFicheiro(tabelaAntes);
        break;
        case '2':
            printf("\nTabela Depois:\n");
            mostrarFicheiro(tabelaDepois);
        break;
        case '3':
            mainConsutarCoordenadas(barcosAntes, barcosDepois);
        break;
        case '4':
            compararTabelas(barcosAntes, barcosDepois);
        break;
        case '5':
            printf("A sair do programa...\n");
        break;
        default:
            printf("Opcao invalida!\n");
    }
}

int main(){

    char escolha;
    char tabelaAntes[LINHAS][COLUNAS];
    char tabelaDepois[LINHAS][COLUNAS];
    struct Barco barcosAntes[NUMERO_TOTAL_DE_BARCOS];
    struct Barco barcosDepois[NUMERO_TOTAL_DE_BARCOS];

    lerTabelaDeFicheiro(tabelaAntes, barcosAntes, "tabela_antes.txt");
    lerTabelaDeFicheiro(tabelaDepois, barcosDepois, "tabela_depois.txt");

    do {
       menu();
       scanf(" %c", &escolha);
       jogo(escolha,tabelaAntes,tabelaDepois,barcosAntes, barcosDepois);
    } while (escolha != '5');
    return 0;
}
