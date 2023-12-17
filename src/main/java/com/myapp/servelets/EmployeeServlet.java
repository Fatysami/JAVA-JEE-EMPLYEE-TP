package com.myapp.servelets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.model.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	  private List<Employee> employeeList;
	  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init() {
        // Initialisation de la liste des employés
        employeeList = new ArrayList<>();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html;charset=UTF-8");

		    try (PrintWriter out = response.getWriter()) {
		        // Début du document HTML
		        out.println("<!DOCTYPE html>");
		        out.println("<html>");
		        out.println("<head>");
		        out.println("<title>Liste des Employés</title>");            
		        out.println("</head>");
		        out.println("<body>");
		        out.println("<h1>Liste des Employés</h1>");

		        // Affichage de la liste des employés
		        if (employeeList.isEmpty()) {
		            out.println("<p>Aucun employé enregistré.</p>");
		        } else {
		            out.println("<ul>");
		            for (Employee employee : employeeList) {
		                out.println("<li>" + employee.getId() + " - " + employee.getFirstName() + " " + employee.getLastName() + " - " + employee.getAge() + " ans - Département : " + employee.getDepartment() + "</li>");
		            }
		            out.println("</ul>");
		        }

		        // Lien pour retourner à la page d'accueil
		        out.println("<a href='index.html'>Retour à l'accueil</a>");

		        out.println("</body>");
		        out.println("</html>");
		    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupérez les données du formulaire et ajoutez un nouvel employé à la liste
        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int age = Integer.parseInt(request.getParameter("age"));
        String department = request.getParameter("department");

        Employee newEmployee = new Employee(id, firstName, lastName, age, department);
        employeeList.add(newEmployee);
	}

}
