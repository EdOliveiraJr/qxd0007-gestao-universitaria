package aluno.base;

import cliente.IRHService.Tipo;

public class STA extends Funcionario {
    private String cpf;
    private String nome; 
    private int nivel;
    private double salario;
    private int diaria;
    private static int diariaMax = 1;
    private double participacao;
    private boolean valido = true;
    
    public void setValido(int nivel) {
        if(nivel < 1 || nivel > 30){
            this.valido = false; 
        }
    }
    public STA(String cpf, String nome, int nivel) {
        this.cpf = cpf;
        this.nome = nome;
        this.nivel = nivel;
        setValido(nivel);
    }

    public String toString(){
        return cpf + nome + nivel + valido;
    }

    @Override
    public Tipo getTipo() {
        return Tipo.STA;
    }
    
    @Override
    public String getCpf() {
        return cpf;
    }

    @Override
    public String getNome() {
        return nome;
    }
    
    public int getNivel() {
        return nivel;
    }
    
    @Override
    public double getSalario() {
        return salario;
    }

    public int getDiaria() {
        return diaria;
    }

    public int getDiariaMax() {
        return diariaMax;
    }

    @Override
    public boolean addDiaria() {
        if(diaria >= diariaMax){
            return false;
        }else{
            diaria++;
            return true;
        }
    }

    public void iniciarMes() {
        this.diaria = 0;
        this.participacao = 0;
    }

    public double getParticipacao() {
        return participacao;
    }

    @Override
    public void setParticipacao(double valor) {
        this.participacao += valor;
    }

    @Override
    public boolean getValido(){
        return valido;
    }

    @Override
    public double calculaSalario(){
        salario = 1000 + (100*nivel) + (diaria*100) + participacao;
        return getSalario();
    }

}
