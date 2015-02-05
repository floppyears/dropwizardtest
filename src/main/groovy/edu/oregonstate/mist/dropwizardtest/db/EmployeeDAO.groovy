package edu.oregonstate.mist.dropwizardtest.db

import com.google.common.base.Optional
import edu.oregonstate.mist.dropwizardtest.core.Employee
import io.dropwizard.hibernate.AbstractDAO
import org.hibernate.SessionFactory

class EmployeeDAO extends AbstractDAO<Employee> {
    public EmployeeDAO(SessionFactory factory) {
        super(factory)
    }

    public Optional<Employee> findById(Long id) {
        return Optional.fromNullable(get(id))
    }

    public Employee set(Employee employee) {
        return persist(employee)
    }

    public List<Employee> findAll() {
        return list(namedQuery('edu.oregonstate.mist.core.Employee.findAll'))
    }
}
