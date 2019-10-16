package br.com.invillia;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.swing.*;
import java.util.List;

@Entity
@Table(name= "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cpf")
    private String CPF;

    public Person() {
    }

    public Person(String name, String CPF) {
        this.name = name;
        this.CPF = validarCPF(CPF);
    }

    public String getName() {
        return name;
    }

    public String getCPF() {
        return CPF;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String validarCPF(String cpf) {

        CPFValidator validar = new CPFValidator();
        List<ValidationMessage> erros = validar.invalidMessagesFor(cpf);

        if(!erros.isEmpty()){
            JOptionPane.showMessageDialog(null,"CPF inválido\nDigite novamente","ERRO",JOptionPane.ERROR_MESSAGE);
            cpf = JOptionPane.showInputDialog(null,"CPF");
            validarCPF(cpf);
        }

        return cpf;
    }

    @Override
    public String toString() {
        return " ID "+ id+ ", Name " + name + ", CPF " + CPF;
    }
}
