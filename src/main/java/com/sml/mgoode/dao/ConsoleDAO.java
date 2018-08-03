package com.sml.mgoode.dao;

import com.sml.mgoode.entity.Favourite;
import com.sml.mgoode.entity.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by michaelgoode on 17/01/2018.
 */
@Repository
public class ConsoleDAO {

    @Autowired
    FavouriteDAO favouriteDAO;


    @Autowired
    @Qualifier("genericJdbcTemplate")
    private JdbcTemplate genericJdbcTemplate;

    @Autowired
    @Qualifier("msJdbcTemplate")
    private JdbcTemplate msJdbcTemplate;

    public List<Program> getPrograms( boolean locked ) {
        final String sql = "select * from edi_fileconfig c, edi_programs p where Enabled=1 and ProcessLock = ? and c.FK_programs_programID = p.PK_programID order by p.Description";
        List<Program> list = new ArrayList<Program>();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = genericJdbcTemplate.getDataSource().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, locked ? 1 : 0);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getResults(resultSet, null);
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
        return list;
    }

    public List<Program> getPrograms() {
        final String sql = "select * from edi_fileconfig c, edi_programs p where c.FK_programs_programID = p.PK_programID and Enabled=1 order by p.Description";
        List<Program> list = new ArrayList<Program>();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = genericJdbcTemplate.getDataSource().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getResults(resultSet, null);
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
        return list;
    }

    public List<Program> getData( JdbcTemplate jdbcTemplate, List<Favourite> favourites ) {
        String sql = "select * from edi_fileconfig c, edi_programs p where Enabled=1 and p.ProgramID in %s and p.SubProgramID in %s and c.FK_programs_programID = p.PK_programID order by p.Description";
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = jdbcTemplate.getDataSource().getConnection();

            String s1 = "";
            s1 = "(";
            for ( int i = 0; i < favourites.size()-1; i++ ) {
                s1 = s1 + "'" + favourites.get(i).getMainProgramID() + "',";
            }
            s1 = s1 + "'" + favourites.get(favourites.size()-1).getMainProgramID() + "')";

            String s2 = "";
            s2 = "(";
            for ( int i = 0; i < favourites.size()-1; i++ ) {
                s2 = s2 + "'" + favourites.get(i).getSubProgramID() + "',";
            }
            s2 = s2 + "'" + favourites.get(favourites.size()-1).getSubProgramID() + "')";

            sql = String.format(sql, s1, s2);
            System.out.println(sql);
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getResults(resultSet, jdbcTemplate.getDataSource().getConnection().getMetaData().getURL());
        } catch (SQLException ex) {
            ex.getMessage();
            System.out.println(ex.getMessage());
            return null;
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.getMessage();
            }

        }
    }

    public List<Program> getFavourites() {

        List<Program> list = new ArrayList<>();
        list.addAll(getData(genericJdbcTemplate, favouriteDAO.getFavourites()));
        //list.addAll(getData(msJdbcTemplate, favourites));


//        PreparedStatement preparedStatement = null;
//        Connection connection = null;
//        try {
//            connection = genericJdbcTemplate.getDataSource().getConnection();
//
//            String s1 = "";
//            s1 = "(";
//            for ( int i = 0; i < favourites.size()-1; i++ ) {
//                s1 = s1 + "'" + favourites.get(i).getMainProgramID() + "',";
//            }
//            s1 = s1 + "'" + favourites.get(favourites.size()-1).getMainProgramID() + "')";
//
//            String s2 = "";
//            s2 = "(";
//            for ( int i = 0; i < favourites.size()-1; i++ ) {
//                s2 = s2 + "'" + favourites.get(i).getSubProgramID() + "',";
//            }
//            s2 = s2 + "'" + favourites.get(favourites.size()-1).getSubProgramID() + "')";
//
//            sql = String.format(sql, s1, s2);
//
//            preparedStatement = connection.prepareStatement(sql);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            return getResults(resultSet);
//        } catch (SQLException ex) {
//            ex.getMessage();
//            System.out.println(ex.getMessage());
//        } finally {
//            try {
//                preparedStatement.close();
//                connection.close();
//            } catch (SQLException ex) {
//                ex.getMessage();
//            }
//
//        }
        return list;
    }

    private ArrayList<Program> getResults( ResultSet resultSet, String catalog ) {
        ArrayList<Program> programs = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Program program = new Program();
                program.setProgramId(resultSet.getLong("PK_fileconfigID"));
                program.setProgramName(resultSet.getString("Description"));
                program.setEnabled(resultSet.getBoolean("enabled"));
                program.setProcessLock(resultSet.getBoolean("ProcessLock"));
                program.setCreator(resultSet.getString("Creator"));
                program.setMainProgram(resultSet.getString("ProgramID"));
                program.setSubProgram(resultSet.getString("SubProgramID"));
                program.setServer(catalog);
                if (!resultSet.getBoolean("ProcessLock")) {
                    program.setStatus("Active");
                } else {
                    program.setStatus("Disabled");
                }
                programs.add(program);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return programs;
    }





}
