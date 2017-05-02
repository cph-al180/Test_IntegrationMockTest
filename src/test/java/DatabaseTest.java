/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Database.Connector;
import Database.DBFacade;
import Entity.UserEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Andreas
 */
public class DatabaseTest {
    
    Connector con;
    EntityManager em;
    EntityTransaction et;
    DBFacade dbf;
    List<UserEntity> users;
    
    @Before
    public void SetUp(){
        con = mock(Connector.class);
        em = mock(EntityManager.class);
        et = mock(EntityTransaction.class);
        dbf = new DBFacade(con);
        users = new ArrayList();
        UserEntity user1 = new UserEntity();
        UserEntity user2 = new UserEntity();
        long id1 = 1;
        long id2 = 2;
        user1.setId(id1);
        user1.setId(id2);
        user1.setUserName("User1");
        user1.setUserName("User2");
        user1.setPassword("User1pwd");
        user1.setPassword("User2pwd");
        users.add(user1);
        users.add(user2);
    }
    
    @Test
    public void TestGetUser(){
        UserEntity user = users.get(0);
        long id = 1;
        when(con.GetEM()).thenReturn(em);
        when(em.find(UserEntity.class, id)).thenReturn(user);
        when(em.getTransaction()).thenReturn(et);
        UserEntity testUser = dbf.FindUser(id);
        assertEquals(user, testUser);
        verify(con).GetEM();
        verify(em).find(UserEntity.class, id);
    }
    
    @Test
    public void TestGetAllUsers(){
        when(con.GetEM()).thenReturn(em);
        //Not Working properly
        when(em.createNamedQuery("UserEntity.findAll").getResultList()).thenReturn(users);
        when(em.getTransaction()).thenReturn(et);
        List<UserEntity> allUsers = dbf.GetAllUsers();
        assertEquals(users, allUsers);
        verify(con).GetEM();
        verify(em).createNamedQuery("UserEntity.findAll").getResultList();
    }

}
