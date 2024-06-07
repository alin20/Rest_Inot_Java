package rest.repository;

import rest.domain.Distanta;
import rest.domain.Probe;
import rest.domain.Stil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import rest.repository.IDBProbe;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class DBProbe implements IDBProbe {
    private JdbcUtils dbUtils;


    private static final Logger logger= LogManager.getLogger();

    public DBProbe(Properties props) {
        logger.info("Initializing CarsDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }
    @Override
    public Optional<Probe> findOne(Long aLong) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        Probe p = new Probe();

        try(PreparedStatement preSmt = con.prepareStatement("select  * from probe where id==?")){
            preSmt.setLong(1,aLong);
            ResultSet result = preSmt.executeQuery();
            Long id = result.getLong("id");
            String d = result.getString("distanta");
            String s = result.getString("stil");

            p.setId(id);
            p.setDst(Distanta.valueOf(d));
            p.setSt(Stil.valueOf(s));

        } catch (SQLException e) {

            logger.error(e);
            System.err.println("Error db "+e);
        }
        logger.traceExit(p);
        return  Optional.ofNullable(p) ;
    }

    @Override
    public Iterable<Probe> findAll() {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Probe> org  = new ArrayList<>();

        try(PreparedStatement preSmt = con.prepareStatement("select  * from probe")){
            try (ResultSet result = preSmt.executeQuery()){
                while (result.next()) {
                    Long id = result.getLong("id");
                    String dest = result.getString("distanta");
                    String stil = result.getString("stil");
                    Probe p = new Probe(id,Distanta.valueOf(dest),Stil.valueOf(stil));
                    org.add(p);
                }
            }
        } catch (SQLException e) {

            logger.error(e);
            System.err.println("Error db "+e);
        }
        logger.traceExit(org);
        return  org  ;
    }

    @Override
    public Optional<Probe> save(Probe entity) {

        logger.traceEntry();
        Connection con = dbUtils.getConnection();

        try(PreparedStatement preSmt = con.prepareStatement("INSERT INTO probe(distanta,stil) VALUES (?,?)")){
            preSmt.setString(1,entity.getDst().toString());
            preSmt.setString(2,entity.getSt().toString());
            int result = preSmt.executeUpdate();
            logger.trace("Saved {} instances",result);


        } catch (SQLException e) {

            logger.error(e);
            System.err.println("Error db "+e);
        }
        logger.traceExit();
        return Optional.ofNullable(entity);


    }

    @Override
    public Optional<Probe> delete(Long aLong) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();

        try(PreparedStatement preSmt = con.prepareStatement("DELETE FROM probe WHERE id = ?")){
            preSmt.setLong(1,aLong);
            int result = preSmt.executeUpdate();
            logger.trace("Delete {} instances",result);


        } catch (SQLException e) {

            logger.error(e);
            System.err.println("Error db "+e);
        }
        logger.traceExit();
        Probe p = new Probe();
        p.setId(aLong);
        return Optional.ofNullable(p);
    }

    @Override
    public Optional<Probe> update(Probe entity) {

        logger.traceEntry();
        Connection con = dbUtils.getConnection();

        try(PreparedStatement preSmt = con.prepareStatement("UPDATE probe SET distanta = ?, stil = ? WHERE id = ?;")){
            preSmt.setString(1,entity.getDst().toString());
            preSmt.setString(2,entity.getSt().toString());
            preSmt.setLong(3,entity.getId());
            int result = preSmt.executeUpdate();
            logger.trace("Updated {} instances",result);


        } catch (SQLException e) {

            logger.error(e);
            System.err.println("Error db "+e);
        }
        logger.traceExit();
        return Optional.ofNullable(entity);
    }

}
