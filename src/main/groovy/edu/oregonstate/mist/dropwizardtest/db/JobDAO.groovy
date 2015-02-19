package edu.oregonstate.mist.dropwizardtest.db

import edu.oregonstate.mist.dropwizardtest.core.Job
import edu.oregonstate.mist.dropwizardtest.mapper.JobMapper

import org.skife.jdbi.v2.sqlobject.SqlQuery
import org.skife.jdbi.v2.sqlobject.Bind
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper

@RegisterMapper(JobMapper)
public interface JobDAO extends Closeable {
    @SqlQuery("""
              SELECT *
              FROM PYVPASJ
              WHERE PYVPASJ_PIDM = :pidm
              """)
    List<Job> findByPidm(@Bind('pidm') Long id)

    void close()
}
