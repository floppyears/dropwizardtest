package edu.oregonstate.mist.dropwizardtest.core;

public class Employee {
    private Integer id;
    private String osuId;
    private String lastName;
    private String firstName;
    private String middleInitial;
    private String onidLoginId;
    private String emailAddress;
    private String employeeStatus;

    /**
     * Employee Constructor
     *
     * Instantiate an Employee object with given id and initialize its
     * properties to null.
     *
     * @param id
     *            unique identifier
     * @return new Employee object
     */
    public Employee(Integer id) {
        this.id = id;
    }

    /* getters */
    public Integer getId() { return id; }
    public String getOsuId() { return osuId; }
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public String getMiddleInitial() { return middleInitial; }
    public String getOnidLoginId() { return onidLoginId; }
    public String getEmailAddress() { return emailAddress; }
    public String getEmployeeStatus() { return employeeStatus; }

    /* setters */
    public void setOsuId(String osuId) { this.osuId = osuId; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setMiddleInitial(String middleInitial) { this.middleInitial = middleInitial; }
    public void setOnidLoginId(String onidLoginId) { this.onidLoginId = onidLoginId; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
    public void setEmployeeStatus(String employeeStatus) { this.employeeStatus = employeeStatus; }

    /**
     * Override equals
     *
     * @param obj
     *            object to compare
     * @return true if object has same type and id, otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        return ((obj instanceof Employee) && (((Employee) obj).getId() == id));
    }

    /**
     * Override hashCode
     *
     * @return employee id
     */
    @Override
    public int hashCode() {
        return id;
    }
}