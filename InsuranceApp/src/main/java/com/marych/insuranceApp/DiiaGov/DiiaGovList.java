package com.marych.insuranceApp.diiaGov;

import java.util.LinkedHashMap;
import java.util.Map;

public class DiiaGovList {

    private Map<Integer, DiiaGov> diiaGovList;

    public DiiaGovList() {
        diiaGovList = new LinkedHashMap<>();


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
