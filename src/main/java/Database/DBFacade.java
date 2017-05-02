/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import javax.persistence.EntityManager;
import Entity.UserEntity;
import java.util.List;

/**
 *
 * @author Andreas
 */
public class DBFacade {
    
    private Connector connector;
    
    public DBFacade(Connector con){
        this.connector = con;
    }
    
    public UserEntity CreateUser(String userName, String password){
        EntityManager em = connector.GetEM();
        UserEntity user = new UserEntity();
        user.setUserName(userName);
        user.setPassword(password);
        try{
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return user;
    }
    
    public List<UserEntity> GetAllUsers(){
        EntityManager em = connector.GetEM();
        List<UserEntity> users;
        try{
            em.getTransaction().begin();
            users = em.createNamedQuery("User.findAll").getResultList();
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return users;
    }
    
    public UserEntity FindUser(long id){
        EntityManager em = connector.GetEM();
        UserEntity user;
        try{
            em.getTransaction().begin();
            user = em.find(UserEntity.class, id);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return user;
    }
}
