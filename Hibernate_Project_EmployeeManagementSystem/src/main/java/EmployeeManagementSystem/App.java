package EmployeeManagementSystem;

import dao.EmployeeDAO;
import entity.Department;
import entity.Employee;
import util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class App 
{
    public static void main(String[] args) {
	        EmployeeDAO dao = new EmployeeDAO();

	        // Create Department
	        Department dept = new Department("IT");
	        saveDepartment(dept);

	        // Add Employees
//	        dao.saveEmployee(new Employee("Alice", "alice@example.com", dept));
//	        dao.saveEmployee(new Employee("Bob", "bob@example.com", dept));
	        

	        // Read
	        System.out.println("\nAll Employees:");
	        dao.getAllEmployees().forEach(System.out::println);

	        // Update
	        Employee emp = dao.getEmployee(1);
	        emp.setEmail("alice@newmail.com");
	        dao.saveEmployee(emp);

	        // Delete
	        dao.deleteEmployee(2);

	        System.out.println("\nAfter Update and Delete:");
	        dao.getAllEmployees().forEach(System.out::println);

	        HibernateUtil.shutdown();
	    }
	    private static void saveDepartment(Department dept) {
	        Transaction tx = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            tx = session.beginTransaction();
	            session.save(dept);
	            tx.commit();
	        } catch (Exception e) {
	            if (tx != null) tx.rollback();
	            e.printStackTrace();
	        }
	    }
	}
