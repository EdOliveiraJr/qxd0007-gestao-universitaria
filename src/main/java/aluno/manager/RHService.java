package aluno.manager;

import aluno.base.Funcionario;
import aluno.base.Professor;
import cliente.IRHService;
import jdk.internal.net.http.common.SequentialScheduler.DeferredCompleter;
import jdk.vm.ci.amd64.AMD64.CPUFeature;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListCellRenderer;

import org.graalvm.compiler.hotspot.nodes.profiling.ProfileBranchNode;

import java.util.HashMap;

public class RHService implements IRHService {
    
    private final Map <String, Funcionario> listaCadastrados;
    private final List <Funcionario> folhaTotal;
    private final List <Funcionario> folhaProfessores;
    private final List <Funcionario> folhaSTA;
    private final List <Funcionario> folhaTerceirizados;

    public RHService(){
        listaCadastrados = new HashMap<>();
        folhaTotal = new ArrayList<>();
        folhaProfessores = new ArrayList<>();
        folhaSTA = new ArrayList<>();
        folhaTerceirizados = new ArrayList<>();
    }

    @Override
    public boolean cadastrar(Funcionario funcionario) {

        if(listaCadastrados.containsKey(funcionario.getCpf()) || folhaTotal.contains(funcionario) || !funcionario.getValido()){
            return false;
        }else{
            listaCadastrados.put(funcionario.getCpf(),funcionario);
            
            switch (funcionario.getTipo()) {
                case PROF:
                    folhaProfessores.add(funcionario);
                    folhaTotal.add(funcionario);
                    return true;
                case STA:
                    folhaTotal.add(funcionario);
                    folhaSTA.add(funcionario);
                    return true;
                case TERC:
                    folhaTotal.add(funcionario);
                    folhaTerceirizados.add(funcionario);
                    return true;
                case FUN:
                    return false;
            }
        
        }
        return false;
    }

    @Override
    public boolean remover(String cpf) {
        if(!listaCadastrados.containsKey(cpf)){
            return false;    
        }else{
            Funcionario funcionario = listaCadastrados.get(cpf);
            
            switch (funcionario.getTipo()) {
                case PROF:
                    folhaProfessores.remove(funcionario);
                    break;
                case STA:
                    folhaSTA.remove(funcionario);
                    break;
                case TERC:
                    folhaTerceirizados.remove(funcionario);
                    break;
                case FUN:
                    break;
            }

            folhaTotal.remove(funcionario);
            
            listaCadastrados.remove(cpf);

            return true;
        }

    }

    @Override
    public Funcionario obterFuncionario(String cpf) {
        if(!listaCadastrados.containsKey(cpf)){
            return null;
        }else{
            return listaCadastrados.get(cpf);
        }
    }

    @Override
    public List<Funcionario> getFuncionarios() {
        folhaTotal.sort(Comparator.comparing(Funcionario::getNome));
        return folhaTotal;
    }

    @Override
    public List<Funcionario> getFuncionariosPorCategoria(Tipo tipo) {
       if(Tipo.PROF == tipo) {
            folhaProfessores.sort(Comparator.comparing(Funcionario::getNome));
            return folhaProfessores;
       }else if(Tipo.STA == tipo) {
            folhaSTA.sort(Comparator.comparing(Funcionario::getNome));
            return folhaSTA;
       }else{
            folhaTerceirizados.sort(Comparator.comparing(Funcionario::getNome));
            return folhaTerceirizados;
       }
        
    }

    @Override
    public int getTotalFuncionarios() {
        return folhaTotal.size();
    }

    @Override
    public boolean solicitarDiaria(String cpf) {
        Funcionario funcionario = listaCadastrados.get(cpf);
        return funcionario.addDiaria();
    }

    @Override
    public boolean partilharLucros(double valor) {
        if(listaCadastrados.isEmpty()){
            return false;
        }else{
            double part = valor / folhaTotal.size();
            for (Funcionario funcionario : folhaTotal) {
                funcionario.setParticipacao(part);
            }
            return true;
        }
    }

    @Override
    public void iniciarMes() {  
        for (Funcionario funcionario : folhaTotal) {
            funcionario.iniciarMes();
        }
    }

    @Override
    public Double calcularSalarioDoFuncionario(String cpf) {
        if(!listaCadastrados.containsKey(cpf)){
            return null;
        }
        Funcionario funcionario = listaCadastrados.get(cpf);
        return funcionario.calculaSalario();
        
    }

    @Override
    public double calcularFolhaDePagamento() {
        double soma = 0;

        for (Funcionario funcionario : folhaTotal) {
            soma += funcionario.calculaSalario();
        }
        return soma;
    }
}