package edu.oregonstate.mist.dropwizardtest.mapper

import edu.oregonstate.mist.dropwizardtest.core.Job

import org.skife.jdbi.v2.StatementContext
import org.skife.jdbi.v2.tweak.ResultSetMapper

import java.sql.ResultSet
import java.sql.SQLException

public class JobMapper implements ResultSetMapper<Job> {
    public Job map(int i, ResultSet rs, StatementContext sc) throws SQLException {
        Job job = new Job()

        job.id              = rs.getLong   'PYVPASJ_PIDM'
        job.positionNumber  = rs.getString 'PYVPASJ_POSN'
        job.suffix          = rs.getString 'PYVPASJ_SUFF'
        job.status          = rs.getString 'PYVPASJ_STATUS'
        job.jobTitle        = rs.getString 'PYVPASJ_DESC'
        job.eclsCode        = rs.getString 'PYVPASJ_ECLS_CODE'
        job.appointmentType = rs.getString 'PYVPASJ_APPOINTMENT_TYPE'
        job.beginDate       = rs.getDate   'PYVPASJ_BEGIN_DATE'
        job.endDate         = rs.getDate   'PYVPASJ_END_DATE'
        job.pclsCode        = rs.getString 'PYVPASJ_PCLS_CODE'
        job.salGrade        = rs.getString 'PYVPASJ_SAL_GRADE'
        job.salStep         = rs.getLong   'PYVPASJ_SAL_STEP'
        job.orgnCodeTs      = rs.getString 'PYVPASJ_ORGN_CODE_TS'
        job.orgnDesc        = rs.getString 'PYVPASJ_ORGN_DESC'
        job.bctrTitle       = rs.getString 'PYVPASJ_BCTR_TITLE'
        job.supervisorPidm  = rs.getLong   'PYVPASJ_SUPERVISOR_PIDM'
        job.supervisorPosn  = rs.getString 'PYVPASJ_SUPERVISOR_POSN'
        job.supervisorSuff  = rs.getString 'PYVPASJ_SUPERVISOR_SUFF'
        job.trialInd        = rs.getLong   'PYVPASJ_TRIAL_IND'
        job.annualInd       = rs.getLong   'PYVPASJ_ANNUAL_IND'
        job.evalDate        = rs.getDate   'PYVPASJ_EVAL_DATE'
        job.low             = rs.getLong   'PYVPASJ_LOW'
        job.midpoint        = rs.getLong   'PYVPASJ_MIDPOINT'
        job.high            = rs.getLong   'PYVPASJ_HIGH'
        job.salary          = rs.getLong   'PYVPASJ_SALARY'
        job.sgrpCode        = rs.getString 'PYVPASJ_SGRP_CODE'

        return job
    }
}
