package edu.oregonstate.mist.dropwizardtest.core

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Column

import org.hibernate.validator.constraints.NotEmpty
import org.hibernate.validator.constraints.Email

@Entity
@Table(name='PYVPASE')
public class Employee {
    @Id
    @Column(name='PYVPASE_PIDM')
    final Long id

    @Column(name='PYVPASE_ID')
    @NotEmpty
    String osuId

    @Column(name='PYVPASE_LAST_NAME')
    @NotEmpty
    String lastName

    @Column(name='PYVPASE_FIRST_NAME')
    String firstName

    @Column(name='PYVPASE_MI')
    String middleInitial

    @Column(name='PYVPASE_ONID_LOGIN')
    String onidLoginId

    @Column(name='PYVPASE_EMAIL')
    @Email
    String emailAddress

    @Column(name='PYVPASE_EMPL_STATUS')
    @NotEmpty
    String employeeStatus

    /**
     * Override equals
     *
     * @param obj
     *            object to compare
     * @return true if object has same type and id, otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        return ((obj instanceof Employee) && (((Employee) obj).id == id))
    }

    /**
     * Override hashCode
     *
     * @return employee id
     */
    @Override
    public int hashCode() {
        return id
    }
}