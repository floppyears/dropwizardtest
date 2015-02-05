package edu.oregonstate.mist.dropwizardtest.db

import edu.oregonstate.mist.dropwizardtest.core.Employee
import io.dropwizard.hibernate.AbstractDAO
import org.hibernate.SessionFactory

class EmployeeDAO extends AbstractDAO<Employee> {
    public EmployeeDAO(SessionFactory factory) {
        super(factory)
    }

    public Employee findById(Long id) {
        return get(id)
    }

    public long create(Employee employee) {
        return persist(employee).id
    }

    public List<Employee> findAll() {
        return list(namedQuery('edu.oregonstate.mist.core.Employee.findAll'))
    }
}
