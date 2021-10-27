/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inheritanceorm;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

/**
 *
 * @author eiwte
 */
public class InheritanceORM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        int n;
        String name;
        double salary;
        int workHours;

        while(run){
            System.out.println("Choose Function: 1.add 2.update 3.delete 4.Exit");
            n = sc.nextInt();
            
            if(n == 1)
            {   
                System.out.println("Choose worker types: 1)Fulltime 2)Parttime");
                int t = sc.nextInt();
                 
                if(t == 1)
                {
                    FulltimeEmployee FE = new FulltimeEmployee();
                    System.out.print("Name = ");
                    name = sc.next();
                    System.out.print("Salary = ");
                    salary = sc.nextDouble();
            
                    FE.setName(name);
                    FE.setSalary(salary);
                    add(FE);
                }
                else if(t == 2)
                {
                    ParttimeEmployee PE = new ParttimeEmployee();
                    System.out.print("Name = ");
                    name = sc.next();
                    System.out.print("Work Hours = ");
                    workHours = sc.nextInt();
            
                    PE.setName(name);
                    PE.setHoursWork(workHours);
                    add(PE);
                }
                else
                {
                    System.out.println("Invalid number!!");
                }
            
            }
            else if(n == 2)
            {
                System.out.println("Choose worker types: 1)Fulltime 2)Parttime");
                int t = sc.nextInt();

                if(t == 1)
                {
                    System.out.println("Update Fulltime Employee >>>");
                    System.out.print("Enter change id :");
                    long id = sc.nextLong();
                    FulltimeEmployee FE = findFulltimeEmployeeById(id);

                    System.out.print("Name = ");
                    name = sc.next();
                    System.out.print("Salary = ");
                    salary = sc.nextDouble();
            
                    FE.setName(name);
                    FE.setSalary(salary);
                    update(FE);
                }
                else if(t == 2)
                {
                    System.out.println("Update Parttime Employee >>>");
                    System.out.print("Enter change id :");
                    long id = sc.nextLong();
                    ParttimeEmployee PE = findParttimeEmployeeById(id);

                    System.out.print("Name = ");
                    name = sc.next();
                    System.out.print("Work Hours = ");
                    workHours = sc.nextInt();
            
                    PE.setName(name);
                    PE.setHoursWork(workHours);
                    update(PE);
                }
                else
                {
                    System.out.println("Invalid number!!");
                }
            }
            else if(n == 3)
            {
                System.out.println("Choose worker types: 1)Fulltime 2)Parttime");
                int t = sc.nextInt();

                if(t == 1)
                {
                    System.out.println("Remove Fulltime Employee >>>");
                    System.out.print("Enter remove id :");
                    long id = sc.nextLong();
                    FulltimeEmployee FE = findFulltimeEmployeeById(id);

                    remove(FE);
                }
                else if(t == 2)
                {
                    System.out.println("Remove Parttime Employee >>>");
                    System.out.print("Enter remove id :");
                    long id = sc.nextLong();
                    ParttimeEmployee PE = findParttimeEmployeeById(id);

                    remove(PE);
                }
                else
                {
                    System.out.println("Invalid number!!");
                }
            }
            else if(n == 4)
            {
                System.out.println("<<Exit>>");
                run = false;
            }
            else
            {
                System.out.println("Invalid input !!");
            }
        }
    }
    

    public static void add(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InheritanceORMPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
     
    public static void update(FulltimeEmployee fulltimeEm) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InheritanceORMPU");
        EntityManager em = emf.createEntityManager();
        FulltimeEmployee emp = em.find(FulltimeEmployee.class, fulltimeEm.getId());
        emp.setName(fulltimeEm.getName());
        emp.setSalary(fulltimeEm.getSalary());
        em.getTransaction().begin();
        try {
            em.persist(emp);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public static void update(ParttimeEmployee parttimeEm) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InheritanceORMPU");
        EntityManager em = emf.createEntityManager();
        ParttimeEmployee emp = em.find(ParttimeEmployee.class, parttimeEm.getId());
        emp.setName(parttimeEm.getName());
        emp.setHoursWork(parttimeEm.getHoursWork());
        em.getTransaction().begin();
        try {
            em.persist(emp);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public static FulltimeEmployee findFulltimeEmployeeById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InheritanceORMPU");
        EntityManager em = emf.createEntityManager();
        FulltimeEmployee emp = em.find(FulltimeEmployee.class, id);
        em.close();
        return emp;
    }

    public static ParttimeEmployee findParttimeEmployeeById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InheritanceORMPU");
        EntityManager em = emf.createEntityManager();
        ParttimeEmployee emp = em.find(ParttimeEmployee.class, id);
        em.close();
        return emp;
    }
    
    public static void remove(FulltimeEmployee emp) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InheritanceORMPU");
        EntityManager em = emf.createEntityManager();
        FulltimeEmployee fromDb = em.find(FulltimeEmployee.class, emp.getId());
        em.getTransaction().begin();
        try {
            em.remove(fromDb);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public static void remove(ParttimeEmployee emp) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InheritanceORMPU");
        EntityManager em = emf.createEntityManager();
        ParttimeEmployee fromDb = em.find(ParttimeEmployee.class, emp.getId());
        em.getTransaction().begin();
        try {
            em.remove(fromDb);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
