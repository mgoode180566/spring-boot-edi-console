package com.sml.mgoode.dao;

import com.sml.mgoode.entity.Lookup;
import com.sml.mgoode.entity.LookupCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by michaelgoode on 06/02/2018.
 */
@Repository
public class LookupDAO {

    @Autowired
    @Qualifier("genericJdbcTemplate")
    private JdbcTemplate genericJdbcTemplate;

    public ArrayList<Lookup> getLookup( long id ){
        ArrayList<Lookup> lookups = new ArrayList<>();
        final String sql = "select * from edi_lookup where FK_fileconfig_fileconfigID = ? order by Sequence";

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
        return lookups;
    }


    private ArrayList<Lookup> getResults( ResultSet resultSet ) {
        ArrayList<Lookup> lookups = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Lookup lookup = new Lookup();
                lookup.setSequence(resultSet.getInt("Sequence"));
                lookup.setCommand(resultSet.getString("command"));
                lookup.setLookupDesc(resultSet.getString("LookupDesc"));
                lookups.add(lookup);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return lookups;
    }

}
