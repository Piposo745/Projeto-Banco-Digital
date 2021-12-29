import java.util.Random;
import java.util.Scanner;

public abstract class Conta implements IConta {
    protected int agencia;
    protected float numeroConta;
    protected float saldo;
    protected float calculoTaxa;
    protected static final int AGENCIA_PADRAO = 001;
    protected int escolha;
    protected int investimentoVariavel;
    protected int investBolsa;
    protected int subiuOuDesceu;
    protected int switchEscolha;
    protected float precoPetrobras;
    protected float precoNubank;
    protected float precoJBS;
    protected float quantAcoes;
    protected int vende;
    protected float calculoGanhos;
    protected int rodando;
    protected String qualquerTecla;
    protected float valorInvestido;
    Scanner digite = new Scanner(System.in);
    Random investimento = new Random();
    Random bolsa = new Random();
    Random preco = new Random();


    public void investimentos(){
        System.out.println("Digite 1 para renda fixa ou 2 para renda variavel");
        escolha = digite.nextInt();
        investimentoVariavel = investimento.nextInt(3);

        if (escolha == 1){
            this.saldo *= 1.03f;
        } else {
            if (investimentoVariavel == 0){
                this.saldo *= 1.05f;
                System.out.println("+ 5% na carteira");
            } else if (investimentoVariavel == 1){
                this.saldo *= 1.15f;
                System.out.println("+ 15% na carteira");
            } else {
                this.saldo *= 0.90f;
                System.out.println("Você vendeu na baixa :((");
            }

        }
    }

    @Override
    public void taxaDepositar(){
        calculoTaxa *= 0.2f;
        this.saldo -= calculoTaxa;

    }

    @Override
    public void taxaSacar(){
        calculoTaxa *= 0.4f;
        this.saldo -= calculoTaxa;

    }

    @Override
    public void sacar(float valor) {
        if (this.saldo > 0){
            this.saldo = saldo - valor;
            taxaSacar();
            System.out.println("Saque efetuado com sucesso");
        }


    }

    @Override
    public void depositar(float valor) {
        this.saldo = saldo + valor;
        System.out.println("Deposito efetuado com sucesso");
        taxaDepositar();
    }


    @Override
    public void extrato(){
        System.out.println("Agência: "+this.agencia);
        System.out.println("Saldo R$"+this.saldo);
        System.out.println("Conta: "+this.numeroConta);

    }


    public void investirBolsa(){
        System.out.println("Você acha que a ação vai subir ou descer? [0 - Subiu ou 1 - Desceu]");
        subiuOuDesceu = digite.nextInt();
        investBolsa = bolsa.nextInt(2);

        if (subiuOuDesceu == 0 && investBolsa == 0){
            this.saldo *= 1.33f;
            System.out.println("Você acertou a ação subiu, isso te rendeu 33% de lucro UAU% :))");
        } else if (subiuOuDesceu == 1 && investBolsa == 1){
            this.saldo *= 1.09f;
            System.out.println("Você acertou a ação desceu e isso te rendeu 9% de lucro");
        } else {
            this.saldo *= 0.82;
            System.out.println("Que desastre você errou :(, descanse e tente mais tarde ou " +
                    "peça um emprestimo conosco :)");
        }
    }




    public void investirAcoes(){

        System.out.println("###### Ações #######");

        System.out.println("Qual ação deseja comprar?\n1 - Petrobras\n2 - Nubank\n3 - JBS\n4 - Sair");
        switchEscolha = digite.nextInt();

        switch (switchEscolha){

            case 1: {
                System.out.println("Ação petrobras");
                System.out.println("Deseja comprar quantas ações? Preço R$"+this.precoPetrobras);
                this.quantAcoes = digite.nextInt();
                System.out.println("Quantos ciclos queres esperar para vender as ações?");
                this.rodando = digite.nextInt() - 1;
                this.valorInvestido = this.precoPetrobras * quantAcoes;
                System.out.println("Valor investido = > "+this.valorInvestido);

                if (this.saldo > this.quantAcoes * this.precoPetrobras){
                    System.out.println("Compra executada com sucesso");
                    this.saldo -= this.valorInvestido;
                    System.out.println("----------------------------------------------------------");
                   
                    lacoForPetrobras();
                }
                else {
                    System.out.println("Saldo insuficiente");
                }

                break;
            }

            case 2: {
                System.out.println("Ações Nubank");
                System.out.println("Deseja comprar quantas ações? Preço R$"+this.precoNubank);
                this.quantAcoes = digite.nextInt();
                System.out.println("Quantos ciclos queres esperar esperar para vender as ações?");
                this.rodando = digite.nextInt() - 1;
                this.valorInvestido = this.precoNubank * quantAcoes;
                System.out.println("Valor investido = > "+this.valorInvestido);

                if (this.saldo > this.quantAcoes * this.precoNubank){
                    System.out.println("Compra executada com sucesso");
                    this.saldo -= this.valorInvestido;
                    System.out.println("-----------------------------------------------------");
                    
                    lacoForNubank();


                }
                else {
                    System.out.println("Saldo insuficiente");
                }

                break;
            }

            case 3: {
                System.out.println("Ações JBS");
                System.out.println("Deseja comprar quantas ações? Preço R$"+this.precoJBS);
                this.quantAcoes = digite.nextInt();
                System.out.println("Quantos ciclos queres esperar esperar para vender as ações?");
                this.rodando = digite.nextInt() - 1;
                this.valorInvestido = this.precoJBS * quantAcoes;
                System.out.println("Valor investido = > "+this.valorInvestido);

                if (this.saldo > this.quantAcoes * this.precoJBS){
                    System.out.println("Compra executada com sucesso");
                    this.saldo -= this.valorInvestido;
                    System.out.println("-----------------------------------------------------");
                   
                    lacoForJBS();

                }
                else {
                    System.out.println("Saldo insuficiente");
                }
                break;

            }

            case 4: {
                System.out.println("Saindo do sistema.....");
                break;
            }
        }
    }

    public void lacoForPetrobras() {
        for (this.vende = 0; this.vende <= this.rodando; this.vende++){
            this.precoPetrobras = preco.nextFloat() * 80;
            System.out.println("Esse é o preço atual delas: "+this.precoPetrobras);

            if (this.vende == this.rodando){
                this.calculoGanhos = this.precoPetrobras * this.quantAcoes;
                if (valorInvestido < calculoGanhos){
                    this.saldo += this.calculoGanhos;
                    this.calculoGanhos -= this.valorInvestido;
                    System.out.println("Seu lucro foi de R$:"+this.calculoGanhos);
                    System.out.println("=========================================================");
                } else {
                    this.saldo -= this.calculoGanhos;
                    this.calculoGanhos -= this.valorInvestido;
                    System.out.println("Seu prejuizo foi de R$:"+this.calculoGanhos);
                    System.out.println("=========================================================");
                }
            } else {
                System.out.println("Ciclo ainda não encerrado!");
                System.out.println("---------------------------------------------");
                System.out.println("Digite a tecla {S} para continuar");
                qualquerTecla = digite.next().toUpperCase();
                if (qualquerTecla.equals("S")){
                    continue;
                }
            }
        }
    }

    public void lacoForNubank(){
        for (this.vende = 0; this.vende <= this.rodando; this.vende++){
            this.precoNubank = preco.nextFloat() * 180;
            System.out.println("Esse é o preço atual dela R$:"+this.precoNubank);

            if (this.vende == this.rodando){
                this.calculoGanhos = this.precoNubank * this.quantAcoes;
                if (valorInvestido < calculoGanhos){
                    this.saldo += this.calculoGanhos;
                    this.calculoGanhos -= this.valorInvestido;
                    System.out.println("Seu lucro foi de R$:"+this.calculoGanhos);
                    System.out.println("=========================================================");
                } else {
                    this.saldo -= this.calculoGanhos;
                    this.calculoGanhos -= this.valorInvestido;
                    System.out.println("Seu prejuizo foi de R$:"+this.calculoGanhos);
                    System.out.println("=========================================================");
                }
            } else {
                System.out.println("Ciclo ainda não encerrado!");
                System.out.println("---------------------------------------------");
                System.out.println("Digite a tecla {S} para continuar");
                qualquerTecla = digite.next().toUpperCase();
                if (qualquerTecla.equals("S")){
                    continue;
                }
            }
        }
    }


    public void lacoForJBS(){
        for (this.vende = 0; this.vende <= this.rodando; this.vende++){
            this.precoJBS = preco.nextFloat() * 60;
            System.out.println("Esse é o preço atual dela R$:"+this.precoJBS);

            if (this.vende == this.rodando){
                this.calculoGanhos = this.precoJBS * this.quantAcoes;
                if (valorInvestido < calculoGanhos){
                    this.saldo += this.calculoGanhos;
                    this.calculoGanhos -= this.valorInvestido;
                    System.out.println("Seu lucro foi de R$:"+this.calculoGanhos);
                    System.out.println("=========================================================");
                } else {
                    this.saldo -= this.calculoGanhos;
                    this.calculoGanhos -= this.valorInvestido;
                    System.out.println("Seu prejuizo foi de R$:"+this.calculoGanhos);
                    System.out.println("=========================================================");
                }
            } else {
                System.out.println("Ciclo ainda não encerrado!");
                System.out.println("---------------------------------------------");
                System.out.println("Digite a tecla {S} para continuar");
                qualquerTecla = digite.next().toUpperCase();
                if (qualquerTecla.equals("S")){
                    continue;
                }
            }
        }
    }



    public int getAgencia() {
        return agencia;
    }

    public float getNumeroConta() {
        return numeroConta;
    }

    public float getSaldo() {
        return saldo;
    }


    public float getCalculoTaxa() {
        return calculoTaxa;
    }
}
