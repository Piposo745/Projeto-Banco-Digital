public interface IConta {
    abstract void sacar(float valor);
    abstract void depositar(float valor);
    abstract void extrato();
    abstract void taxaSacar(float valor);
    abstract void taxaDepositar();
    abstract void taxaOperacoes();
}
