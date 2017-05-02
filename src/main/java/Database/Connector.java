/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Andreas
 */
public class Connector {
    private static Connector connector;
    private EntityManagerFactory emf;
    
    private Connector(){
        connector = new Connector();
        emf = Persistence.createEntityManagerFactory("IntegrationTest_PU");
    }
    
    public Connector GetCon(){
        return connector;
    }
    
    public EntityManager GetEM(){
        return emf.createEntityManager();
    }
    
}
