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
    protected boolean vender;
    Scanner digite = new Scanner(System.in);
    Random investimento = new Random();
    Random bolsa = new Random();


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
            System.out.println("Que desastre você errou :(, descanse e tente mais tarde ou peça um emprestimo conosco :)");
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
