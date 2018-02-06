package com.sml.mgoode.dao;

import com.sml.mgoode.entity.LookupCondition;
import com.sml.mgoode.entity.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by michaelgoode on 06/02/2018.
 */
@Repository
public class LookupConditionDAO {

    @Autowired
    @Qualifier("genericJdbcTemplate")
    private JdbcTemplate genericJdbcTemplate;

    public ArrayList<LookupCondition> getLookupConditions( long id ){
        ArrayList<LookupCondition> lookupConditions = new ArrayList<>();
        final String sql = "select * from edi_lookup_condition where FK_fileConfig_fileConfigID = ? order by Sequence";

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = genericJdbcTemplate.getDataSource().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getResults(resultSet);
        } catch (SQLException ex) {
            ex.getMessage();
            System.out.println(ex.getMessage());
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.getMessage();
            }
        }
        return lookupConditions;
    }


    private ArrayList<LookupCondition> getResults( ResultSet resultSet ) {
        ArrayList<LookupCondition> lookupConditions = new ArrayList<>();
        try {
            while (resultSet.next()) {
                LookupCondition lookupCondition = new LookupCondition();
                lookupCondition.setSequence(resultSet.getInt("Sequence"));
                lookupCondition.setConditionField1(resultSet.getString("ConditionField1"));
                lookupCondition.setConditionField2(resultSet.getString("ConditionField2"));
                lookupCondition.setConditionField3(resultSet.getString("ConditionField3"));
                lookupCondition.setConditionField4(resultSet.getString("ConditionField4"));
                lookupCondition.setConditionField5(resultSet.getString("ConditionField5"));
                lookupCondition.setConditionField6(resultSet.getString("ConditionField6"));
                lookupCondition.setUpdateField1(resultSet.getString("UpdateField1"));
                lookupCondition.setUpdateField2(resultSet.getString("UpdateField2"));
                lookupCondition.setUpdateField3(resultSet.getString("UpdateField3"));
                lookupCondition.setUpdateField4(resultSet.getString("UpdateField4"));
                lookupCondition.setUpdateField5(resultSet.getString("UpdateField5"));
                lookupCondition.setUpdateField6(resultSet.getString("UpdateField6"));

                lookupConditions.add(lookupCondition);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return lookupConditions;
    }


}
