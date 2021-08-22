package aluno.base;

import cliente.IRHService;
import cliente.IRHService.Tipo;
import jdk.tools.jlink.internal.SymLinkResourcePoolEntry;

public class Professor extends Funcionario {
    private String cpf;
    private String nome; 
    private char classe;
    private double salario;
    private int diaria;
    private static int diariaMax = 3;
    private double participacao;
    private boolean valido = true;

    
    public void setSalario(){
        switch (classe) {
            case 'A':
                this.salario = 3000;
                break;
            case 'B':
                this.salario = 5000;
                break;
            case 'C':
                this.salario = 7000;
                break;
            case 'D':
                this.salario = 9000;
                break;
            case 'E':
                this.salario = 11000;
                break;
            default:
                break; 
        }
    }

    public void setValido(char classe){
        if(classe != 'A' && classe != 'B' && classe != 'C' && classe != 'D' && classe != 'E'){
            this.valido = false;
        }
    }

    public Professor(String cpf, String nome, char classe){
        this.cpf = cpf;
        this.nome = nome;
        this.classe = classe;
        setSalario();
        setValido(classe);

    }

    public String toString(){
        return cpf + nome + classe + valido;
    }

    @Override
    public Tipo getTipo(){
        return Tipo.PROF;
    }

    @Override
    public String getCpf() {
        return cpf;
    }

    @Override
    public String getNome() {
        return nome;
    }
    
    public char getClasse() {
        return classe;
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
    public double calculaSalario(){
        salario = salario + (diaria*100) + participacao;
        return getSalario(); 
    }

    @Override
    public boolean getValido(){
        return valido;
     }
}