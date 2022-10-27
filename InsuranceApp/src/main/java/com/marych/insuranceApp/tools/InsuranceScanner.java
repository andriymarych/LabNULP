package com.marych.insuranceApp.tools;

import com.marych.insuranceApp.User.Customer;
import com.marych.insuranceApp.User.InsuranceSpecialist;
import com.marych.insuranceApp.insurance.InsuranceCompany;

import com.marych.insuranceApp.insurance.InsuranceCompanyList;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;

import java.util.Scanner;

import static com.marych.insuranceApp.Main.insuranceCompanyList;
import static com.marych.insuranceApp.Main.userInfo;

public class InsuranceScanner {
    public static Scanner in = new Scanner(System.in);
    private Customer customer;
    private InsuranceSpecialist insuranceSpecialist;
    private int policyNo;
    private InsuranceCompany insuranceCompany;
    private boolean compulsory;

    public int getPolicyNo() {
        return policyNo;
    }

    public InsuranceCompany getInsuranceCompany() {
        return insuranceCompany;
    }


    public InsuranceSpecialist getInsuranceSpecialist() {
        return insuranceSpecialist;
    }

    public InsurancePolicy createInsurancePolicy(Customer customer) {
        this.customer = customer;
        policyNo = selectPolicyNo();
        insuranceCompany = selectCompany();
        if(!selectPolicyInfo()){
            return null;
        }
        insuranceSpecialist = selectInsurer(insuranceCompany);
        if (insuranceSpecialist == null) {
            return null;
        }
        return new InsurancePolicy(policyNo, customer, insuranceSpecialist)
                .setCompulsory(compulsory)
                .setInsuranceCompany(insuranceCompany);

    }

    public InsurancePolicy createInsurancePolicy(InsuranceSpecialist insuranceSpecialist) {
        this.insuranceSpecialist = insuranceSpecialist;
        policyNo = selectPolicyNo();
        insuranceCompany = insuranceCompanyList.asList().get(insuranceSpecialist.getInsuranceCompanyId());
        customer = selectCustomer();
        if (insuranceCompany == null || customer == null ) {
            return null;
        }
        if(!selectPolicyInfo()){
            return null;
        }
        return new InsurancePolicy(policyNo, customer, insuranceSpecialist)
                .setCompulsory(compulsory)
                .setInsuranceCompany(insuranceCompany);
    }
    private boolean selectPolicyInfo(){
        int compulsoryInt = selectCompulsory();
        if (compulsoryInt == 0) {
            compulsory = false;
        } else if (compulsoryInt == 1) {
            compulsory = true;
        } else {
            return false;
        }
        return true;
    }

    public static Customer selectCustomer() {
        Scanner in = new Scanner(System.in);
        for (int i = 3; i > 0; i--) {
            System.out.println("Введіть ID-код страхувальника : ");
            int Id = in.nextInt();
            if (userInfo.getUserIdList().containsKey(Id)) {
                if (userInfo.getUserIdList().get(Id) instanceof Customer customer) {
                    return customer;
                } else {
                    System.out.println("Даний користувач є страховим спеціалістом");
                }
            } else {
                System.out.println("Даного користувача не існує");
            }
            if (i != 1) {
                if (i == 3) {
                    System.out.println("" +
                            "У вас залишилось " + (i - 1) + " Cпроби");
                } else {
                    System.out.println(" У вас залишилось " + (i - 1) + " Спроба");
                }
            } else {
                System.out.println("Ви вичерпали ліміт введення ID-код страхового спеціаліста");
            }
        }
        return null;

    }

    private static int selectPolicyNo() {
        return InsuranceCompanyList.getNextPolicyNumber();
    }


    public static InsuranceCompany selectCompany() {
        Scanner in = new Scanner(System.in);
        InsuranceCompany insuranceCompany;
        int insuranceCompanyStr;
        System.out.println("Оберіть страхову компанію (Введіть ID-код компанії) :");
        insuranceCompanyList.print();
        for (int i = 3; i > 0; i--) {
            insuranceCompanyStr = in.nextInt();
            insuranceCompany = insuranceCompanyList.asList().getOrDefault(insuranceCompanyStr, null);
            if (insuranceCompany != null) {
                return insuranceCompany;
            }
            if (i != 1) {
                if (i == 3) {
                    System.out.println("Ви ввели невірну назву страхової компанії." +
                            "\nУ вас залишилось " + (i - 1) + " Cпроби");
                } else {
                    System.out.println("Ви ввели невірну назву страхової компанії." +
                            "\nУ вас залишилось " + (i - 1) + " Спроба");
                }
            } else {
                System.out.println("Ви вичерпали ліміт введення назви страхової компанії");


            }
        }
        return null;
    }

    private static int selectCompulsory() {
        Scanner in = new Scanner(System.in);
        int compulsory;
        System.out.println("Оберіть форму страхування(оберіть номер):");
        System.out.println("0.Необовʼязкова");
        System.out.println("1.Обовʼязкова");
        for (int i = 3; i > 0; i--) {
            compulsory = in.nextInt();
            if (compulsory == 0) {
                return 0;
            } else if (compulsory == 1) {
                return 1;
            }
            if (i != 1) {
                if (i == 3) {
                    System.out.println("Ви обрали невірну форму страхування." +
                            "\nУ вас залишилось " + (i - 1) + " Cпроби");
                } else {
                    System.out.println("Ви обрали невірну форму страхування." +
                            "\nУ вас залишилось " + (i - 1) + " Спроба");
                }
            } else {
                System.out.println("Ви вичерпали ліміт введення форми страхування");
            }
        }
        return 2;
    }

    public static InsuranceSpecialist selectInsurer(InsuranceCompany insuranceCompany) {
        Scanner in = new Scanner(System.in);
        insuranceCompany.printSpecialists();
        System.out.println("Оберіть ID-код страхового спеціаліста :");
        for (int i = 3; i > 0; i--) {
            int Id = in.nextInt();
            InsuranceSpecialist insuranceSpecialist = insuranceCompany.getSpecialistsList().getOrDefault(Id, null);
            if (insuranceSpecialist != null) {
                return insuranceSpecialist;
            }
            if (i != 1) {
                if (i == 3) {
                    System.out.println("Ви обрали невірний ID-код страхового спеціаліста" +
                            "\nУ вас залишилось " + (i - 1) + " Cпроби");
                } else {
                    System.out.println("Ви обрали невірний ID-код страхового спеціаліста" +
                            "\nУ вас залишилось " + (i - 1) + " Спроба");
                }
            } else {
                System.out.println("Ви вичерпали ліміт введення ID-код страхового спеціаліста");
            }
        }
        return null;
    }

}
