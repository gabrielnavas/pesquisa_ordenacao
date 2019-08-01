package algoritmos;

import Utils.Processo;

public class Pilha {

    private Processo processoAtual;
    private Pilha proximaPilha;

    public Pilha() {
        this.processoAtual = null;
        this.proximaPilha = null;
    }

    public void inserirpilha(Processo processoNovo) {
        if (this.processoAtual == null) {
            this.processoAtual = processoNovo;
            this.proximaPilha = new Pilha();
        } else {
            this.proximaPilha.inserirpilha(processoNovo);
        }
    }

    public Processo removerPilha() {
        if (this.proximaPilha.proximaPilha == null) {
            Processo removido = this.processoAtual;
            this.processoAtual = null;
            this.proximaPilha = null;
            return removido;
        } else {
            return this.proximaPilha.removerPilha();
        }
    }

    public String checkpilha(String vazia) {
        if (this.proximaPilha.proximaPilha == null) {
            vazia = " " + this.processoAtual.getPrioridade() + " " + this.processoAtual.getTempo() + vazia;
        } else {
            vazia = this.proximaPilha.checkpilha(vazia) + " && " + this.processoAtual.getPrioridade() + " " + this.processoAtual.getTempo() + " " + vazia;
        }
        return vazia;
    }

    public boolean vaziaPilha() {
        if (this.proximaPilha == null) {
            return true;
        }
        return false;
    }

//	public int tempoTrabalhado(int tempo) {
//            return this.processoAtual.trabalhado(tempo);
//	}
}
