package com.jelena.tests;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAImpl {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA-EclipseLink");
        System.out.println("Is opened connection :: "+ factory.createEntityManager().isOpen());
	}

}
