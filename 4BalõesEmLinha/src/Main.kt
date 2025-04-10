import java.io.File

val balaoAzul = "\u001b[34m\u03D9\u001b[0m"
val balaoVermelho ="\u001b[31m\u03D9\u001b[0m"
val sair = "Sair"
val gravar = "Gravar"
val sugestao = "?"
val nome = ""

fun validaTabuleiro(numLinhas:Int ,numColunas:Int): Boolean {
    return when {
        numLinhas == 5 && numColunas == 6 -> true
        numLinhas == 6 && numColunas == 7 -> true
        numLinhas == 7 && numColunas == 8 -> true
        else -> false
    }
}
fun nomeValido(nome: String): Boolean {
    var posicao = 0
    if (nome.length>=3 && nome.length<=12) {
        while (posicao < nome.length) {
            if (nome[posicao] == ' ') {
                return false
            }
            posicao++
        }
        return true
    }
    return false
}
fun criaTopoTabuleiro(numColuna:Int):String {
    var coluna = 0
    var resultado = ""
    while (coluna < numColuna) {
        if (coluna == 0 ) {
            resultado += '\u2554'
        }
        if( coluna == numColuna-1) {
            resultado += "\u2550\u2550\u2550\u2557"
        }else{
            resultado += "\u2550\u2550\u2550\u2550"
        }
        coluna++
    }
    return resultado
}
fun criaLegendaHorizontal(numColunas: Int): String {
    if (numColunas < 1 || numColunas > 26) {
        return ""
    }
    var resultado = "  "
    for (posicao in 0 until numColunas) {
        resultado += ('A' + posicao)
        if (posicao < numColunas - 1) {
            resultado += " | "
        }
    }
    return "$resultado  "
}
fun processaColuna(numColuna: Int, coluna: String?): Int? {
    val intervaloPermitido = 'A' .. 'A'+ numColuna-1
    if (coluna.isNullOrEmpty()){
        return null
    }
    val primeiroCaractere = coluna[0]

    if (primeiroCaractere !in intervaloPermitido || primeiroCaractere.isLowerCase() || primeiroCaractere == ' ') {
        return null
    }
    return (primeiroCaractere - 'A')
}
fun criaTabuleiroVazio(numLinhas: Int, numColunas: Int): Array<Array<String?>> {
    val tabuleiro: Array<Array<String?>> = Array(numLinhas) { Array(numColunas) { null } }

    return tabuleiro
}
fun criaTabuleiro(tabuleiro: Array<Array<String?>>, mostraLegenda: Boolean = true): String {
    val numLinhas = tabuleiro.size
    val numColunas = tabuleiro[0].size
    var tabuleiroV = criaTopoTabuleiro(numColunas)

    for (linha in 0 until numLinhas) {
        tabuleiroV += "\n\u2551"

        for (coluna in 0 until numColunas) {
            val resultado = tabuleiro[linha][coluna] ?: " "
            tabuleiroV += " $resultado"

            if (coluna < numColunas - 1) {
                tabuleiroV += " |"
            }
        }

        tabuleiroV += " \u2551"
    }

    if (mostraLegenda) {
        tabuleiroV += "\n" + criaLegendaHorizontal(numColunas)
    }

    return tabuleiroV
}
fun contaBaloesLinha(tabuleiro: Array<Array<String?>>, numLinhas: Int): Int {
    var count = 0
    if (numLinhas in tabuleiro.indices) {
        for (coluna in tabuleiro[numLinhas].indices) {
            if (tabuleiro[numLinhas][coluna] == balaoVermelho || tabuleiro[numLinhas][coluna] == balaoAzul) {
                count++
            }
        }
    }
    return count
}
fun contaBaloesColuna(tabuleiro:Array<Array<String?>>,numColunas: Int):Int{
    if (numColunas < 0 || numColunas >= tabuleiro[0].size) {
        return 0
    }
    var count = 0
    var countBalaoAzul=0
    for (linha in 0 until tabuleiro.size) {
        if(tabuleiro[linha][0] == balaoAzul){
            count++
        }
        if (tabuleiro[linha][numColunas] == balaoVermelho || tabuleiro[linha][numColunas] == balaoAzul) {
            count++
        }
    }
    return count
}
fun colocaBalao(tabuleiro: Array<Array<String?>>,numColunas: Int, humano: Boolean):Boolean{
    if (numColunas < 0 || numColunas >= tabuleiro[0].size) {
        return false
    }
    val baloesNaColuna = contaBaloesColuna(tabuleiro, numColunas)
    if (baloesNaColuna >= tabuleiro.size) {
        return false
    }
    for (coluna in 0 until tabuleiro.size) {
        if (tabuleiro[coluna][numColunas] == null) {
            tabuleiro[coluna][numColunas] = if (humano) balaoVermelho else balaoAzul
            return true
        }
    }
    return false
}
fun jogadaNormalComputador(tabuleiro: Array<Array<String?>>): Int{
    for (linha in 0 until tabuleiro.size) {
        for (coluna in 0 until tabuleiro[linha].size) {
            if (tabuleiro[linha][coluna].isNullOrEmpty()) {
                tabuleiro[linha][coluna] = balaoAzul
                return coluna
            }
        }
    }
    return -1
}
fun eVitoriaHorizontal(tabuleiro: Array<Array<String?>>): Boolean {
    var countVermelho = 0
    var countAzul = 0

    for (linha in tabuleiro) {
        for (coluna in linha) {
            if (coluna == balaoVermelho && countVermelho<4) {
                countVermelho++
                countAzul = 0
            } else if (coluna == balaoAzul && countAzul<4) {
                countAzul++
                countVermelho = 0
            } else {
                countVermelho = 0
                countAzul = 0
            }
            when{
                (countAzul==4 || countVermelho==4)-> return true
            }
        }
    }
    return false
}
fun eVitoriaVertical(tabuleiro: Array<Array<String?>>): Boolean {

    for (coluna in tabuleiro[0].indices) {
        var countVermelho = 0
        var countAzul = 0

        for (linha in tabuleiro.indices) {

            if (tabuleiro[linha][coluna] == balaoVermelho) {
                countVermelho++
                countAzul = 0
            } else if (tabuleiro[linha][coluna] == balaoAzul) {
                countAzul++
                countVermelho = 0
            } else {
                countVermelho = 0
                countAzul = 0
            }

            if (countVermelho == 4 || countAzul == 4) {
                return true
            }
        }
    }
    return false
}
fun eVitoriaDiagonal(tabuleiro: Array<Array<String?>>): Boolean {
    val linhas = tabuleiro.size
    val colunas = tabuleiro[0].size

    for (linha in 0 until linhas - 3) {
        for (coluna in 0 until colunas - 3) {
            val peca = tabuleiro[linha][coluna]
            if (peca != null &&
                peca == tabuleiro[linha + 1][coluna + 1] &&
                peca == tabuleiro[linha + 2][coluna + 2] &&
                peca == tabuleiro[linha + 3][coluna + 3]
            ) {
                return true
            }
        }
    }

    for (linha in 0 until linhas - 3) {
        for (coluna in 3 until colunas) {
            val peca = tabuleiro[linha][coluna]
            if (peca != null &&
                peca == tabuleiro[linha + 1][coluna - 1] &&
                peca == tabuleiro[linha + 2][coluna - 2] &&
                peca == tabuleiro[linha + 3][coluna - 3]
            ) {
                return true
            }
        }
    }

    return false
}
fun ganhouJogo(tabuleiro: Array<Array<String?>>):Boolean{
    if (eVitoriaHorizontal(tabuleiro)){
        return true
    }
    if (eVitoriaVertical(tabuleiro)){
        return true
    }
    if (eVitoriaDiagonal(tabuleiro)){
        return true
    }
    return false
}
fun eEmpate(tabuleiro: Array<Array<String?>>): Boolean {
    for (linha in tabuleiro) {
        for (posicao in linha) {
            if (posicao != balaoVermelho && posicao != balaoAzul) {
                return false
            }
        }
    }
    return true
}
fun explodeBalao(tabuleiro: Array<Array<String?>>, posicaoBalao: Pair<Int, Int>): Boolean {
    val (linha, coluna) = posicaoBalao

    if (linha !in tabuleiro.indices || coluna !in tabuleiro[0].indices || tabuleiro[linha][coluna] == null) {
        return false
    }
    tabuleiro[linha][coluna] = null

    for (posicaoL in linha until tabuleiro.size - 1) {
        tabuleiro[posicaoL][coluna] = tabuleiro[posicaoL + 1][coluna]
    }

    tabuleiro[tabuleiro.size - 1][coluna] = null

    return true
}
fun jogadaExplodirComputador(tabuleiro: Array<Array<String?>>): Pair<Int,Int>{
    val linhas = tabuleiro.size
    val colunas = tabuleiro[0].size

    for (linha in 0 until linhas) {
        for (coluna in 0 until colunas - 3) {
            if (tabuleiro[linha][coluna] != null && tabuleiro[linha][coluna] == tabuleiro[linha][coluna + 1] &&
                tabuleiro[linha][coluna] == tabuleiro[linha][coluna + 2]) {

                return Pair(linha, coluna)
            }
        }
    }
    for (coluna in 0 until colunas) {
        for (linha in 0 until linhas - 3) {
            if (tabuleiro[linha][coluna] != null && tabuleiro[linha][coluna] == tabuleiro[linha + 1][coluna] &&
                tabuleiro[linha][coluna] == tabuleiro[linha + 2][coluna]) {

                return Pair(linha, coluna)
            }
        }
    }
    for (linha in 0 until linhas - 3) {
        for (coluna in 0 until colunas - 3) {
            if (tabuleiro[linha][coluna] != null && tabuleiro[linha][coluna] == tabuleiro[linha + 1][coluna + 1] &&
                tabuleiro[linha][coluna] == tabuleiro[linha + 2][coluna + 2]) {

                return Pair(linha, coluna)
            }
        }
    }
    for (linha in 3 until linhas) {
        for (coluna in 0 until colunas - 3) {
            if (tabuleiro[linha][coluna] != null && tabuleiro[linha][coluna] == tabuleiro[linha - 1][coluna + 1] &&
                tabuleiro[linha][coluna] == tabuleiro[linha - 2][coluna + 2]) {
                return Pair(linha, coluna)
            }
        }
    }
    for (linha in 0 until linhas) {
        for (coluna in 0 until colunas) {
            if (tabuleiro[linha][coluna] != null) {
                return Pair(linha, coluna)
            }
        }
    }
    return Pair(linhas,colunas)
}
fun leJogo(nomeFicheiro: String): Pair<String,Array<Array<String?>>>{
    val ficheiro = File(nomeFicheiro).readLines()
    val nomeJogador = ficheiro[0]
    val linhasTabuleiro = ficheiro.size - 1
    val colunasTabuleiro = ficheiro[1].split(",").size
    val tabuleiro = Array(linhasTabuleiro) { Array<String?>(colunasTabuleiro) { null } }

    for (linha in 1 until ficheiro.size) {
        val partes = ficheiro[linha].split(",")
        for (coluna in partes.indices) {
            tabuleiro[linha - 1][coluna] = when {
                partes[coluna]== "H" -> balaoVermelho
                partes[coluna] == "C" -> balaoAzul
                else -> null
            }
        }
    }
    return Pair(nomeJogador, tabuleiro)
}
fun gravaJogo(nomeFicheiro: String, tabuleiro: Array<Array<String?>>, nomeJogador: String): Pair<String, Array<Array<String?>>> {
    val balaoVermelho = "\u001b[31m\u03D9\u001b[0m"
    val balaoAzul = "\u001b[34m\u03D9\u001b[0m"
    var resultado = ""

    val filePrinter = File(nomeFicheiro).printWriter()
    filePrinter.println(nomeJogador)

    for (linha in tabuleiro.indices) {
        for (coluna in tabuleiro[linha].indices) {
            resultado += when (tabuleiro[linha][coluna]) {
                balaoVermelho -> "H"
                balaoAzul -> "C"
                else -> ""
            }
            if (coluna < tabuleiro[linha].size - 1) {
                resultado += ","
            }
        }
        resultado += "\n"
    }
    filePrinter.print(resultado)
    filePrinter.close()
    return Pair(nomeJogador,tabuleiro)
}
fun menu(){
    println("""

1. Novo Jogo
2. Gravar Jogo
3. Ler Jogo
0. Sair

""".trimIndent()
    )
}
fun menuSelecao(numero: Int?){
    val intervalo: IntRange = 0..3
    when(numero) {
        null -> opcaoInvalida()
        !in intervalo -> opcaoInvalida()
        2 -> println("Funcionalidade Gravar nao esta disponivel")
        3 -> nomeFIcheiro()
        0 -> println("A sair...")
    }
}
fun menuSelecaoSair(numero: Int?){
    val intervalo: IntRange = 0..3
    when(numero) {
        null -> opcaoInvalida()
        !in intervalo -> opcaoInvalida()
        2 -> nomeFIcheiro()
        3 -> nomeFIcheiro()
        0 -> println("A sair...")
    }
}
fun descricaoJogadaHumano(numLinhas: Int,numColunas: Int,nomeJogador1: String){
    println()
    println("$nomeJogador1: $balaoVermelho")
    println("Tabuleiro ${numLinhas}X${numColunas}")
    println("Coluna? (A..${'A' + numColunas - 1}):")
}
fun nomeDoFicheiroTxt(){
    println("Introduza o nome do ficheiro (ex: jogo.txt)")
}
fun gravarJogoSeValido(tabuleiro: Array<Array<String?>>, numLinhas: Int, numColunas: Int, nomeJogador1: String) {
    var totalBaloes = 0
    for (linha in tabuleiro.indices) {
        totalBaloes += contaBaloesLinha(tabuleiro, linha)
    }
    if (totalBaloes > 1) {
        nomeDoFicheiroTxt()
        val nomeFicheiro = readLine() ?: "jogo.txt"
        gravaJogo(nomeFicheiro, tabuleiro, nomeJogador1)
        println("Tabuleiro ${numLinhas}x$numColunas gravado com sucesso")
    } else {
        println("O tabuleiro está vazio. Nao é possível gravar o jogo.")
    }
}
fun eQuaseVitoriaVertical(tabuleiro: Array<Array<String?>>, linhaInicial: Int, coluna: Int): Boolean {
    if (coluna < 0 || coluna >= tabuleiro[0].size) {
        return true
    }
    if (linhaInicial + 3 >= tabuleiro.size) {
        return true
    }
    val cor = tabuleiro[linhaInicial][coluna]
    if (cor != null && tabuleiro[linhaInicial + 1][coluna] == cor && tabuleiro[linhaInicial + 2][coluna].isNullOrEmpty() ){
        return true
    }
    if (cor != null &&
        tabuleiro[linhaInicial + 1][coluna] == cor &&
        tabuleiro[linhaInicial + 2][coluna] == cor &&
        tabuleiro[linhaInicial + 3][coluna] == null) {
        return false
    }
    return false
}
fun eQuaseVitoriaHorizontal(tabuleiro: Array<Array<String?>>, linha: Int, colunaInicial: Int): Int? {
    if (colunaInicial < 0 || colunaInicial + 3 >= tabuleiro[0].size) {
        return null
    }
    if (linha < 0 || linha >= tabuleiro.size) {
        return null
    }
    val cor = tabuleiro[linha][colunaInicial]
    if (cor != null &&
        tabuleiro[linha][colunaInicial + 1] == cor &&
        tabuleiro[linha][colunaInicial + 2] == cor &&
        tabuleiro[linha][colunaInicial + 3] == null) {
        return colunaInicial + 3
    }
    return null
}
fun calculaEstatisticas(tabuleiro: Array<Array<String?>>): Array<Int> {
    var countBaloes = 0
    var count = 0
    var countBaloesAzuis = 0
    var countBaloesVermelhos = 0
    var countQuaseVitorias = 0
    val numLinhas= tabuleiro.size
    val numColunas = tabuleiro[0].size
    if (numLinhas == numColunas){
        return arrayOf(0,0,0,0)

    }
    for (linha in tabuleiro) {
        for (espaco in linha) {
            if (espaco != null) {
                countBaloes++
                if (espaco == balaoAzul) {
                    countBaloesAzuis++
                } else if (espaco== balaoVermelho) {
                    countBaloesVermelhos++
                }
            }
        }
    }
    for (linha in tabuleiro.indices) {
        for (coluna in tabuleiro[0].indices) {
            if (eQuaseVitoriaVertical(tabuleiro, linha, coluna)) {
                countQuaseVitorias++
            }
            val colunaHorizontal = eQuaseVitoriaHorizontal(tabuleiro, linha, coluna)
            if (colunaHorizontal != null) {
                countQuaseVitorias++
            }
        }
    }
    return arrayOf(countBaloes, countBaloesAzuis, countBaloesVermelhos, countQuaseVitorias)
}
fun sugestaoJogadaNormalHumano(tabuleiro: Array<Array<String?>>): Int? {
    for (coluna in tabuleiro[0].indices) {
        for (linha in 0 until tabuleiro.size - 3) {
            if (eQuaseVitoriaVertical(tabuleiro, linha, coluna)) {
                return coluna
            }
        }
    }
    for (linha in tabuleiro.indices) {
        for (coluna in 0 until tabuleiro[0].size - 3) {
            val colunaSugestao = eQuaseVitoriaHorizontal(tabuleiro, linha, coluna)
            if (colunaSugestao != null) {
                return colunaSugestao
            }
        }
    }
    return null
}
fun sugestaoJogada(tabuleiro: Array<Array<String?>>): Char? {
    val numLinhas = tabuleiro.size
    val numColunas = tabuleiro[0].size
    for (coluna in 0 until numColunas) {
        for (linha in 0 until numLinhas - 3) {
            if (eQuaseVitoriaVertical(tabuleiro, linha, coluna)) {
                return 'A' + coluna
            }
        }
    }
    for (linha in 0 until numLinhas) {
        for (coluna in 0 until numColunas - 3) {
            val sugestaoColuna = eQuaseVitoriaHorizontal(tabuleiro, linha, coluna)
            if (sugestaoColuna != null) {
                return 'A' + sugestaoColuna
            }
        }
    }
    return null
}
fun sugetaoMain(tabuleiro: Array<Array<String?>>,numColunas: Int){
    val sugestao = sugestaoJogada(tabuleiro)
    if (sugestao != null) {
        println("Sugestao de jogada na coluna: $sugestao")
    } else {
        println("Nao existe uma sugestao de jogada")
    }
    println("Coluna? (A..${'A' + numColunas - 1}):")
}
fun solicitarDimensoesTabuleiro(): Pair<Int, Int> {
    var numLinhas: Int
    var numColunas: Int
    do {
        println("Numero de linhas:")
        val input = readln()
        numLinhas = input.toIntOrNull() ?: -1
        if (numLinhas !in 5..7) {
            numeroInvalido()
        }
    } while (numLinhas !in 5..7)

    do {
        println("Numero de colunas:")
        val input = readln()
        numColunas = input.toIntOrNull() ?: -1
        if (numColunas !in 0..26) {
            numeroInvalido()
        }
    } while (numColunas !in 0..26)

    if (!validaTabuleiro(numLinhas, numColunas)) {
        println("Tamanho do tabuleiro invalido")
        return solicitarDimensoesTabuleiro()
    }

    return Pair(numLinhas, numColunas)
}
fun solicitarNomeJogador(): String {
    var nomeJogador: String

    do {
        println("Nome do jogador 1:")
        nomeJogador = readln()
        if (!nomeValido(nomeJogador)) {
            println("Nome de jogador invalido")
        }
    } while (!nomeValido(nomeJogador))

    return nomeJogador
}
fun lejogoMenu() {
    nomeDoFicheiroTxt()
    val nomeFicheiro = readln().trim()

    if (nomeFicheiro.isEmpty()) {
        println("Nome do ficheiro inválido. A leitura não foi realizada.")
    }
    val (jogador, tabuleiro) = leJogo(nomeFicheiro)
    println("Tabuleiro ${tabuleiro.size}x${tabuleiro[0].size} lido com sucesso!")
    println(criaTabuleiro(tabuleiro))

    val tamanhoLinhas = tabuleiro.size
    val tamanhoColunas = tabuleiro[0].size

    jogoAndamentoLeJogoSair(tabuleiro, tamanhoLinhas, tamanhoColunas, jogador)
}
fun descricaoComputador(tabuleiro: Array<Array<String?>>,numLinhas: Int,numColunas: Int,coluna: Int){
    println("Tabuleiro ${numLinhas}X${numColunas}")
    println("Coluna escolhida: ${'A' + coluna}")
    println(criaTabuleiro(tabuleiro))
}
fun numeroInvalido(){
    println("Numero invalido")
}
fun opcaoInvalida(){
    println("Opcao invalida. Por favor, tente novamente.")
}
fun nomeFIcheiro(){
    println("Introduza o nome do ficheiro (ex: jogo.txt)")

}
fun jogoTerminouEmpate(){
    println("O jogo terminou em empate!")
}
fun colunaCheiaouInvalida(numColunas: Int){
    println("Coluna invalida ou cheia\nColuna? (A..${'A' + numColunas - 1}):")
}
fun colunaCheia(numColunas: Int){
    println("Coluna invalida\nColuna? (A..${'A' + numColunas - 1}):")
}
fun balaoComputador(){
    println("\nComputador: $balaoAzul")
}
fun perdeuGanhouoComputador(){
    println("\nPerdeu! Ganhou o Computador.")
}
fun empateEscritaMain(){
    println("Empate!")
}
fun ganhouJogador1(nomeJogador1: String){
    println("\nParabens $nomeJogador1! Ganhou!")
}
fun errojogadasComputador(){
    println("Erro: Não há jogadas disponíveis para o computador.")
}
fun escritaColunaEscolhida(colunaEscolhida:String?){
    println("Coluna escolhida: $colunaEscolhida")
}
fun jogoAndamentoLeJogoSair(tabuleiro: Array<Array<String?>>, numLinhas: Int, numColunas: Int, nomeJogador1: String){
    var jogoEmAndamento = true
    var humano = true
    while (jogoEmAndamento) {
        if (humano) {
            val colunaEscolhida = readLine()
            if (colunaEscolhida == sair) {
                return sair()
            } else if (colunaEscolhida == gravar) {
                gravarJogoSeValido(tabuleiro, numLinhas, numColunas, nomeJogador1)
                return sair()
            } else if (colunaEscolhida == sugestao) {
                sugetaoMain(tabuleiro, numColunas)
            } else {
                val coluna = processaColuna(numColunas, colunaEscolhida)
                if (coluna != null && coluna in 0 until numColunas) {
                    if (colocaBalao(tabuleiro, coluna, true)) {
                        escritaColunaEscolhida(colunaEscolhida)
                        println(criaTabuleiro(tabuleiro))
                        if (ganhouJogo(tabuleiro)) {
                            ganhouJogador1(nomeJogador1)
                            jogoEmAndamento = false
                        } else if (eEmpate(tabuleiro)) {
                            jogoTerminouEmpate()
                            jogoEmAndamento = false
                        }
                        humano = false
                    } else {
                        colunaCheiaouInvalida(numColunas)
                    }
                } else {
                    colunaCheia(numColunas)
                }
            }
        } else {
            balaoComputador()
            val coluna = jogadaNormalComputador(tabuleiro)
            if (coluna != -1) {
                descricaoComputador(tabuleiro, numLinhas, numColunas, coluna)
                descricaoJogadaHumano(numLinhas,numColunas,nomeJogador1)
                if (ganhouJogo(tabuleiro)) {
                    perdeuGanhouoComputador()
                    jogoEmAndamento = false
                } else if (eEmpate(tabuleiro)) {
                    empateEscritaMain()
                    jogoEmAndamento = false
                } else {
                    humano = true
                }
            } else {
                errojogadasComputador()
                jogoEmAndamento = false
            }
        }
    }
    return sair()
}
fun reduzirMain1(){
    println("\nBem-vindo ao jogo \"4 Baloes em Linha\"!")
    menu()
}
fun reduzirMain2(tabuleiro: Array<Array<String?>>, numLinhas: Int,numColunas: Int,nomeJogador1: String){
    println(criaTabuleiro(tabuleiro))
    descricaoJogadaHumano(numLinhas, numColunas, nomeJogador1)
}
fun reduzirMain3(tabuleiro: Array<Array<String?>>, colunaEscolhida: String?){
    escritaColunaEscolhida(colunaEscolhida)
    println(criaTabuleiro(tabuleiro))
}
fun reduzirMain4(tabuleiro: Array<Array<String?>>, numLinhas: Int, numColunas: Int, nomeJogador1: String,coluna: Int){
    descricaoComputador(tabuleiro, numLinhas, numColunas, coluna)
    descricaoJogadaHumano(numLinhas,numColunas,nomeJogador1)
}
fun gravaJogoMenu(){
    val tabu: Array<Array<String?>> = criaTabuleiroVazio(0, 0)
    println("Introduza o nome do ficheiro (ex: jogo.txt):")
    val ficheiro = readln().trim()

    if (ficheiro.isEmpty() || tabu.isEmpty() || tabu[0].isEmpty()) {
        println("Nome do ficheiro inválido. A gravação não foi realizada.")
    } else {
        gravaJogo(ficheiro, tabu, nome)
        println("Tabuleiro ${tabu.size}x${tabu[0].size} gravado com sucesso em \"$ficheiro\".")
    }
}
fun sair() {
    menu()
    do {
        val num = readln().toIntOrNull()
        menuSelecaoSair(num)
        if (num == 2) {
            gravaJogoMenu()
        }
        if (num == 3) {
            lejogoMenu()
        }
        if (num == 1) {
            val (numL, numCol) = solicitarDimensoesTabuleiro()
            val nomeJ = solicitarNomeJogador()
            val tabuleiro = criaTabuleiroVazio(numL, numCol)
            println(criaTabuleiro(tabuleiro))
            descricaoJogadaHumano(numL, numCol, nomeJ)
            jogoAndamentoLeJogoSair(tabuleiro,numL,numCol,nomeJ)
        }
    } while (num != 0)
}
fun main() {
    var jogoEmAndamento = true
    var humano = true
    reduzirMain1()
    do {
        val numero = readln().toIntOrNull()
        menuSelecao(numero)
        when (numero) {
            1 -> {
                val (numLinhas, numColunas) = solicitarDimensoesTabuleiro()
                val nomeJogador1 = solicitarNomeJogador()
                val tabuleiro = criaTabuleiroVazio(numLinhas, numColunas)
                reduzirMain2(tabuleiro, numLinhas, numColunas, nomeJogador1)
                while (jogoEmAndamento) {
                    if (humano) {
                        val colunaEscolhida = readLine()
                        if (colunaEscolhida == sair) {
                            return sair()
                        } else if (colunaEscolhida == gravar) {
                            gravarJogoSeValido(tabuleiro, numLinhas, numColunas, nomeJogador1)
                            return sair()
                        } else if (colunaEscolhida == sugestao) {
                            sugetaoMain(tabuleiro, numColunas)
                        } else {
                            val coluna = processaColuna(numColunas, colunaEscolhida)
                            if (coluna != null && coluna in 0 until numColunas) {
                                if (colocaBalao(tabuleiro, coluna, true)) {
                                    reduzirMain3(tabuleiro,colunaEscolhida)
                                    if (ganhouJogo(tabuleiro)) {
                                        ganhouJogador1(nomeJogador1)
                                        jogoEmAndamento = false
                                    } else if (eEmpate(tabuleiro)) {
                                        jogoTerminouEmpate()
                                        jogoEmAndamento = false
                                    }
                                    humano = false
                                } else {
                                    colunaCheiaouInvalida(numColunas)
                                }
                            } else {
                                colunaCheia(numColunas)
                            }
                        }
                    } else {
                        balaoComputador()
                        val coluna = jogadaNormalComputador(tabuleiro)
                        if (coluna != -1) {
                            reduzirMain4(tabuleiro, numLinhas, numColunas, nomeJogador1, coluna)
                            if (ganhouJogo(tabuleiro)) {
                                perdeuGanhouoComputador()
                                jogoEmAndamento = false
                            } else if (eEmpate(tabuleiro)) {
                                empateEscritaMain()
                                jogoEmAndamento = false
                            } else {
                                humano = true
                            }
                        } else {
                            errojogadasComputador()
                            jogoEmAndamento = false
                        }
                    }
                }
                return sair()
            }
            3 -> lejogoMenu()
        }
    } while (numero != 0)
}
