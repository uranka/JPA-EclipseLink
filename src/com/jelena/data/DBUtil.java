package com.jelena.data;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("JPA-EclipseLink");
    
    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
}