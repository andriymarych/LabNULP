package com.marych.insuranceApp.DiiaGov;

import java.util.LinkedHashMap;
import java.util.Map;

public class DiiaGovList {

    private Map<Integer, DiiaGov> diiaGovList;

    public DiiaGovList() {
        diiaGovList = new LinkedHashMap<>();

        /*diiaGovList.put(1111,new DiiaGov(1111,1111)
                .setFirstName("Tony").setLastName("Montana")
                .setBirthDate("2022-05-25").setITN(5421));*/
    }


    public DiiaGov getDocument(int documentNo) {
        DiiaGov document = diiaGovList.getOrDefault(documentNo, null);
        if (document != null) {
            if (document.verify()) {
                return document;
            }
        }
        System.out.println("Вашого документу не існує у Дії.");
        return null;
    }
    public void addIdDocument(DiiaGov idDocument){
        diiaGovList.put(idDocument.getDocumentNo(),idDocument);
    }
}
