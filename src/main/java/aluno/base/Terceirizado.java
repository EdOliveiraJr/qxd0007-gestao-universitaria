package aluno.base;

import cliente.IRHService.Tipo;
import jdk.nashorn.internal.ir.ReturnNode;

public class Terceirizado extends Funcionario{
    private String cpf;
    private String nome; 
    private boolean insalubre;
    private double salario;
    private double participacao;

    public Terceirizado(String cpf, String nome, boolean insalubre) {
        this.cpf = cpf;
        this.nome = nome;
        this.insalubre = insalubre;
    }

    public String toString(){
        return cpf + nome + insalubre;
    }

    @Override
    public Tipo getTipo(){
        return Tipo.TERC;
    }

    @Override
    public String getCpf() {
        return cpf;
    }

    @Override
    public String getNome() {
        return nome;
    }
    
    public boolean getInsalubre() {
        return insalubre;
    }
    
    @Override
    public double getSalario() {
        return salario;
    }

    @Override
    public boolean addDiaria() {
        return false;
    }

    @Override
    public void iniciarMes() {
        this.participacao = 0;
    } 

    @Override
    public void setParticipacao(double valor) {
        this.participacao = valor;
    }

    @Override
    public boolean getValido(){
        return true;
    }

    @Override
    public double calculaSalario(){
        if(insalubre){
            salario = 1500 + participacao;
            return salario;
        }
        salario = 1000 + participacao;
        return salario;
    }

}
