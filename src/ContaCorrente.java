public class ContaCorrente extends Conta {

    private static int SEQUENCIAL = 1;
    public ContaCorrente() {
        super.agencia = AGENCIA_PADRAO;
        super.numeroConta = SEQUENCIAL++;
        super.saldo = 0.0f;

    }


    public void extrato(){
        System.out.println("##### Imprimindo extrato Conta corrente #####");
        super.extrato();
    }

    public void taxaConta(){
        saldo -= 2.5f;
    }
}
