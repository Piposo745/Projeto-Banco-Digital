import java.util.Random;

public class ContaCorrente extends Conta {

    Random preco = new Random();

    private static int SEQUENCIAL = 1;
    public ContaCorrente() {
        super.agencia = AGENCIA_PADRAO;
        super.numeroConta = SEQUENCIAL++;
        super.saldo = 0.0f;
        super.precoPetrobras = preco.nextFloat() * 40;
        super.precoJBS = preco.nextFloat() * 30;
        super.precoNubank = preco.nextFloat() * 80;

    }




    public void extrato(){
        System.out.println("##### Imprimindo extrato Conta corrente #####");
        super.extrato();
    }

    public void taxaConta(){
        saldo -= 2.5f;
    }
}
