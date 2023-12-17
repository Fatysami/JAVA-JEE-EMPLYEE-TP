package com.myapp.model;

public class Employee  extends Person implements Manageable {
	private String department;
    private Employee manager;

    public Employee(int id, String firstName, String lastName, int age, String department) {
        super(id, firstName, lastName, age);
        this.department = department;
        this.manager = null;
    }

    @Override
    public void assignManager(Employee manager) {
        this.manager = manager;
    }

    // Getters et Setters
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

	public String getFirstName() {
		// TODO Auto-generated method stub
		return super.getFirstName();
	}
}
