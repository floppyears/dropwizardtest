package edu.oregonstate.mist.dropwizardtest.db

import com.google.common.base.Optional
import edu.oregonstate.mist.dropwizardtest.core.Employee
import io.dropwizard.hibernate.AbstractDAO
import org.hibernate.SessionFactory

/* Hibernate Data Access Object representing an Employee */
class EmployeeDAO extends AbstractDAO<Employee> {

    /* Create */
    public EmployeeDAO(SessionFactory factory) {
        super(factory)
    }

    /* Read */
    public Optional<Employee> findById(Long id) {
        return Optional.fromNullable(get(id))
    }

    public List<Employee> findAll() {
        return list(namedQuery('edu.oregonstate.mist.core.Employee.findAll'))
    }

    /* Update */
    public Employee set(Employee employee) {
        return persist(employee)
    }
}
