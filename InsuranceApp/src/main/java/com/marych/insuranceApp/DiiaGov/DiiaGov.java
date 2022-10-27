package com.marych.insuranceApp.DiiaGov;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Scanner;

public class DiiaGov {
@JsonProperty("documentNo")
    private int documentNo;
    @JsonProperty("diiaSign")
    private int diiaSign;
    @JsonProperty("firstname")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("birthDate")
    private LocalDate birthDate;

    public void setDocumentNo(int documentNo) {
        this.documentNo = documentNo;
    }

    public void setDiiaSign(int diiaSign) {
        this.diiaSign = diiaSign;
    }

    public void setBirthDate(String birthDateStr) {
        this.birthDate = LocalDate.parse(birthDateStr);
    }

    @JsonProperty("ITN")
    int ITN;

    DiiaGov(int documentNo, int diiaSign) {
        this.documentNo = documentNo;
        this.diiaSign = diiaSign;
    }

    public DiiaGov() {
    }

    public DiiaGov setFirstName(String firstName) {
        if(this.firstName == null){
            this.firstName = firstName;
        }
        return this;
    }

    public int getDocumentNo() {
        return documentNo;
    }

    public int getDiiaSign() {
        return diiaSign;
    }

    public DiiaGov setLastName(String lastName) {
        if(this.lastName == null) {
            this.lastName = lastName;
        }
        return this;
    }

    public DiiaGov setITN(int ITN) {
        if(this.ITN == 0) {
            this.ITN = ITN;
        }
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getITN() {
        return ITN;
    }

    public boolean verify() {
        int diiaSign;
        Scanner in = new Scanner(System.in);
        for (int i = 3; i > 0; i--) {
            System.out.println("Введіть код Дія.Підпис :");
            diiaSign = in.nextInt();
            if (this.diiaSign == diiaSign) {
                System.out.println("Вітаємо " + firstName + ".Вашу особистість підтверджено.\n");
                return true;
            }
            if (i != 1) {
                if (i == 3) {
                    System.out.println("Ви ввели невірний код.У вас залишилось " + (i-1) + " Cпроби");
                } else {
                    System.out.println("Ви ввели невірний код.У вас залишилась " + (i-1) + " Спроба");
                }
            }
        }
        System.out.println("Ви вичерпали ліміт введення коду Дія.Підпис.");
        return false;
    }

}
